//package com.lc.frame.log.starter;
//
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.MDC;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationEvent;
//import org.springframework.context.event.GenericApplicationListener;
//import org.springframework.core.ResolvableType;
//import org.springframework.core.env.Environment;
//import org.springframework.stereotype.Component;
//
//@Component
//public class ApplicationStartedEventListener implements GenericApplicationListener {
//
//    @Autowired
//    private Environment env;
//
//    @Override
//    public boolean supportsEventType(ResolvableType resolvableType) {
//        return true;
//    }
//
//    @Override
//    public void onApplicationEvent(ApplicationEvent applicationEvent) {
//        String appName = env.getProperty("spring.application.name");
//        if (StringUtils.isNotBlank(appName)) {
//            MDC.put("appName", appName);
//        }
//
//        String logDir = env.getProperty("log4j2.logDir");
//        if (StringUtils.isNotBlank(logDir)) {
//            MDC.put("logDir", logDir);
//        }
//
//        String logIp = env.getProperty("log4j2.logIp");
//        if (StringUtils.isNotBlank(logIp)) {
//            MDC.put("logIp", logIp);
//        }
//    }
////
////    private String getIp(){
////        InetAddress ip;
////        try {
////            ip = InetAddress.getLocalHost();}catch (UnknownHostException e) {
////            e.printStackTrace();
////            return " ";
////        }
////        return ip.getHostAddress();
////    }
//}
