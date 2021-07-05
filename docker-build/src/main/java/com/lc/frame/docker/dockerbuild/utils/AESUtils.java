package com.lc.frame.docker.dockerbuild.utils;


import org.apache.tomcat.util.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;

/**
 * @author ljl
 * @version 1.0
 * @date 2021/7/5 16:32
 */
public class AESUtils {

    private static final String IV = "efa4775bddb96a55";
    private static final String AES = "AES";
    private static final String ALGORITHM = "AES/CBC/PKCS7Padding";
    private static final String CHARSET = "UTF-8";
    private static final Logger logger = LoggerFactory.getLogger(AESUtils.class);

    /**
     * 使用PKCS7Padding填充必须添加一个支持PKCS7Padding的Provider
     * 类加载的时候就判断是否已经有支持256位的Provider,如果没有则添加进去
     */
    static {
        if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
            Security.addProvider(new BouncyCastleProvider());
        }
        Security.setProperty("crypto.policy", "unlimited");
    }

    /**
     * 加密 128位
     *
     * @param content 需要加密的原内容
     * @param pkey    密匙
     * @return
     */
    public static byte[] encrypt(String content, String pkey) {
        try {
            //SecretKey secretKey = generateKey(pkey);
            //byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec skey = new SecretKeySpec(pkey.getBytes(), AES);
            Cipher cipher = Cipher.getInstance(ALGORITHM);// "算法/加密/填充"
            IvParameterSpec iv = new IvParameterSpec(IV.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, skey, iv);//初始化加密器
            byte[] encrypted = cipher.doFinal(content.getBytes(CHARSET));
            return encrypted; // 加密
        } catch (Exception e) {
            logger.info("encrypt() method error:", e);
        }
        return null;
    }

    /**
     * 获得密钥
     *
     * @param secretKey
     * @return
     * @throws Exception
     */
    private static SecretKey generateKey(String secretKey) throws Exception {
        //防止linux下 随机生成key
        Provider p = Security.getProvider("SUN");
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG", p);
        secureRandom.setSeed(secretKey.getBytes());
        KeyGenerator kg = KeyGenerator.getInstance(AES);
        kg.init(secureRandom);
        // 生成密钥
        return kg.generateKey();
    }

    /**
     * @param content 加密前原内容
     * @param pkey    密匙
     * @return base64EncodeStr   aes加密完成后内容
     * @throws
     * @Title: encryptStr
     * @Description: aes对称加密
     */
    public static String encryptStr(String content, String pkey) {
        byte[] aesEncrypt = encrypt(content, pkey);
        String base64EncodeStr = Base64.encodeBase64String(aesEncrypt);
        return base64EncodeStr;
    }

    /**
     * @param content base64处理过的字符串
     * @param pkey    密匙
     * @return String    返回类型
     * @throws Exception
     * @throws
     * @Title: decodeStr
     * @Description: 解密 失败将返回NULL
     */
    public static String decodeStr(String content, String pkey) throws Exception {
        try {
            byte[] base64DecodeStr = Base64.decodeBase64(content);
            byte[] aesDecode = decode(base64DecodeStr, pkey);
            if (aesDecode == null) {
                return null;
            }
            String result;
            result = new String(aesDecode, CHARSET);
            return result;
        } catch (Exception e) {
            System.out.println("Exception:" + e.getMessage());
            throw new Exception("解密异常");
        }
    }

    /**
     * 解密 128位
     *
     * @param content 解密前的byte数组
     * @param pkey    密匙
     * @return result  解密后的byte数组
     * @throws Exception
     */
    public static byte[] decode(byte[] content, String pkey) throws Exception {

        //SecretKey secretKey = generateKey(pkey);
        //byte[] enCodeFormat = secretKey.getEncoded();
        SecretKeySpec skey = new SecretKeySpec(pkey.getBytes(), AES);
        IvParameterSpec iv = new IvParameterSpec(IV.getBytes(CHARSET));
        Cipher cipher = Cipher.getInstance(ALGORITHM);// 创建密码器
        cipher.init(Cipher.DECRYPT_MODE, skey, iv);// 初始化解密器
        byte[] result = cipher.doFinal(content);
        return result; // 解密

    }

    public static void main(String[] args) throws Exception {
        //明文
        String content = "13800138000";
        //密匙
        String pkey = "3776d97bbdfb4cec8aac8d4fbecf381e";
        System.out.println("待加密报文:" + content);
        System.out.println("密匙:" + pkey);
        String encryptStr = encryptStr(content, pkey);
        System.out.println("加密报文:" + encryptStr);
        String decodeStr = decodeStr(encryptStr, pkey);
        System.out.println("解密报文:" + decodeStr);
        System.out.println("加解密前后内容是否相等:" + decodeStr.equals(content));
    }
}
