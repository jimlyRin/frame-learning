package com.lc.frame.log4j2.starter;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.pattern.ConverterKeys;
import org.apache.logging.log4j.core.pattern.LogEventPatternConverter;
import org.apache.logging.log4j.core.pattern.PatternConverter;


/**
 * Plugin 表示的是这是一个插件，name是名称，category为PatternConverter.CATEGORY（目前插件只有这个选择）ConverterKeys表示的就是自定义的参数,可以多个
 */
@Plugin(name = "MyLogDataConverter", category = PatternConverter.CATEGORY)
@ConverterKeys({ "rep_msg" })
public class MyLogDataConverter extends LogEventPatternConverter {

    private static final MyLogDataConverter INSTANCE = new MyLogDataConverter();

    /**
     * 定义的这个类必须提供一个newInstance方法，参数是final String[] options，返回值为定义的类（对于是否是单例没有明确的要求）
     * @param options
     * @return
     */
    public static MyLogDataConverter newInstance(final String[] options) {
        return INSTANCE;
    }

    /**
     * 提供一个私有的构造函数，调用父类的构造函数，函数需要提供两个参数 第一个参数是转换器的名称，第二个是css样式
     */
    public MyLogDataConverter() {
        super("rep_msg", "rep_msg");
    }

    /**
     * 还有主要的工作format，这里有两个参数，LogEvent是系统已经存在的一些可选数据，StringBuilder 表示的是最终的输出字符流。一般都是将自定义的append进去
     *
     * @param event
     * @param toAppendTo
     */
    @Override
    public void format(LogEvent event, StringBuilder toAppendTo) {
        String message = event.getMessage().getFormattedMessage();

        if (message.contains("\"")) {
            message = message.replaceAll("\"", "\\\"");
        }
        if (message.contains("\r\n")) {
            message = message.replaceAll("\r\n", "\\\\r\\\\n");
        }
        if (message.contains("\n")) {
            message = message.replaceAll("\n", "\\\\n");
        }
        toAppendTo.append(message);
    }

}
