package com.lc.frame.common.uitls;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Security;

public class AesUtils {

    private static final String AES = "AES";
    private static final String ALGORITHM = "AES/CBC/PKCS7Padding";
    private static final String CHARSET = "UTF-8";
    private static final Logger logger = LoggerFactory.getLogger(AesUtils.class);
    private static Cipher cipher;

    /**
     * 使用PKCS7Padding填充必须添加一个支持PKCS7Padding的Provider
     * 类加载的时候就判断是否已经有支持256位的Provider,如果没有则添加进去
     */
    static {
        if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
            Security.addProvider(new BouncyCastleProvider());
        }
        Security.setProperty("crypto.policy", "unlimited");

        try {
            // "算法/加密/填充"
            AesUtils.cipher = Cipher.getInstance(ALGORITHM);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 加密
     *
     * @param content 需要加密的原内容
     * @param pkey    密匙
     * @param ivkey   iv
     * @return
     */
    public static byte[] encrypt(String content, String pkey, String ivkey) {
        try {
            SecretKeySpec skey = new SecretKeySpec(pkey.getBytes(), AES);
            IvParameterSpec iv = new IvParameterSpec(ivkey.getBytes());
            // 初始化加密器
            cipher.init(Cipher.ENCRYPT_MODE, skey, iv);
            byte[] encrypted = cipher.doFinal(content.getBytes(CHARSET));
            // 加密结果
            return encrypted;
        } catch (Exception e) {
            logger.error("encrypt() method error: {}", e.getMessage(), e);
        }
        return null;
    }

    /**
     * @param content 加密前原内容
     * @param pkey    密匙
     * @param ivkey   iv
     * @return base64EncodeStr   aes加密完成后内容
     */
    public static String encryptStr(String content, String pkey, String ivkey) {
        byte[] aesEncrypt = encrypt(content, pkey, ivkey);
        String base64EncodeStr = Base64.encodeBase64String(aesEncrypt);
        return base64EncodeStr;
    }

    /**
     * @param content base64处理过的字符串
     * @param pkey    密匙
     * @param ivkey   iv
     * @return String    返回类型
     */
    public static String decodeStr(String content, String pkey, String ivkey) throws Exception {
        try {
            byte[] base64DecodeStr = Base64.decodeBase64(content);
            byte[] aesDecode = decode(base64DecodeStr, pkey, ivkey);
            if (aesDecode == null) {
                return null;
            }
            String result;
            result = new String(aesDecode, CHARSET);
            return result;
        } catch (Exception e) {
            logger.error("decodeStr() method error: {}", e.getMessage(), e);
            throw new Exception("解密异常");
        }
    }

    /**
     * 解密
     *
     * @param content 解密前的byte数组
     * @param pkey    密匙
     * @param ivkey   iv
     * @return result  解密后的byte数组
     */
    public static byte[] decode(byte[] content, String pkey, String ivkey) throws Exception {
        SecretKeySpec skey = new SecretKeySpec(pkey.getBytes(), AES);
        IvParameterSpec iv = new IvParameterSpec(ivkey.getBytes(CHARSET));
        // 初始化解密器
        cipher.init(Cipher.DECRYPT_MODE, skey, iv);
        byte[] result = cipher.doFinal(content);
        // 解密结果
        return result;

    }

    public static void main(String[] args) throws Exception {
        //密匙
        String iv = "efa4775bddb96a55";
        // 32位
        String pkey = "3776d97bbdfb4cec8aac8d4fbecf381e";

        Long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            test(i, pkey, iv);
        }
        Long end = System.currentTimeMillis();
        System.out.println("总时间:" + (end - start) + "ms");
    }

    private static void test(int i, String pkey, String iv) throws Exception {
        //明文
        String content = "试试加入中文怎么样ABCDEFGabcdefg1380013800" + i;

//        System.out.println("待加密报文:" + content);

//        System.out.println("密匙:" + pkey);
//        Long start = System.currentTimeMillis();
        String encryptStr = encryptStr(content, pkey, iv);
//        Long end = System.currentTimeMillis();
//        System.out.println("加密报文:" + encryptStr);
//        System.out.println("加密时间:" + (end - start) + "ms");

//        start = System.currentTimeMillis();
        String decodeStr = decodeStr(encryptStr, pkey, iv);
//        end = System.currentTimeMillis();
//        System.out.println("解密报文:" + decodeStr);
//        System.out.println("解密时间:" + (end - start) + "ms");
//        System.out.println("加解密前后内容是否相等:" + decodeStr.equals(content));
    }
}