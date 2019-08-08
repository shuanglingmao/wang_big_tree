package com.neo.utils;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @Description: 同时不为空
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/7/24 0024
 * @Author 毛双领 <shuangling.mao@ucarinc.com>
 */
@Target({ ElementType.TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = SyncNotNullValidator.class)
@Documented
public @interface SyncNotNull {
    String message() default "指定参数不能同时为空";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String[] fieldNames() default {};
}
