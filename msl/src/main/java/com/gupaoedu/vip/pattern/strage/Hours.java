package com.gupaoedu.vip.pattern.strage;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Hours {
    double max() default Double.MAX_VALUE;
    double min() default 0;
}
