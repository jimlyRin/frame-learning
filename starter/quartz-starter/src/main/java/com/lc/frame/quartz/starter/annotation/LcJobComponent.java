package com.lc.frame.quartz.starter.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * LcJobComponent
 *
 * @author jimlyRin
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface LcJobComponent {
    String jobId() default "";

    boolean autoStart() default true;
}
