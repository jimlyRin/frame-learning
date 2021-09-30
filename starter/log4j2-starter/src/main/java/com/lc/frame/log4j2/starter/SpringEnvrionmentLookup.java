//package com.lc.frame.log.starter;
//
//import org.apache.logging.log4j.core.LogEvent;
//import org.apache.logging.log4j.core.config.plugins.Plugin;
//import org.apache.logging.log4j.core.lookup.AbstractLookup;
//import org.apache.logging.log4j.core.lookup.StrLookup;
//import org.springframework.context.ApplicationContextInitializer;
//import org.springframework.context.ConfigurableApplicationContext;
//
///**
// * 日志变量获取spring配置
// *
// */
//@Plugin(name = "spring", category = StrLookup.CATEGORY)
//public class SpringEnvrionmentLookup extends AbstractLookup implements ApplicationContextInitializer<ConfigurableApplicationContext> {
//
//    private static  ConfigurableApplicationContext context;
//
//    @Override
//    public String lookup(LogEvent logEvent, String s) {
//        if (context != null) {
//            return context.getEnvironment().getProperty(s);
//        }
//        return null;
//    }
//
//    @Override
//    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
//        context = configurableApplicationContext;
//    }
//}
