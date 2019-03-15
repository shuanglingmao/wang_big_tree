package com.msl.annotation;

import java.lang.annotation.*;

/**
 * Description: ${todo}
 *
 * @author shuangling.mao
 * @date 2019/3/15 10:47
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Msl {
    String value() default "";
}
