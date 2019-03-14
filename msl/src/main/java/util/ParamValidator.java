package util;

import lombok.Data;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.util.Set;

/**
 * description: 参数校验工具类
 * <p>校验实体中注解了javax.validation框架注解的属性</p>
 *
 * @author yongjun.ji
 * @date 2018/2/7.
 */
public class ParamValidator {
    private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    /**
     * 校验实体参数,返回第一条错误信息
     *
     * @param t
     * @param <T>
     * @return
     */
    public static <T> void validate(T t) {
        if (t == null){
            throw new RuntimeException("参数不能为空！");
        }
        Set<ConstraintViolation<T>> validationSet = validator.validate(t,Default.class);
        String message = null;
        if (validationSet != null && validationSet.size() > 0) {
            ConstraintViolation<T> violation = validationSet.iterator().next();
            message = violation.getMessage();
        }
        if (message != null) {
            throw new RuntimeException(message);
        }
    }
    /**
     * description: 校验对象是否为空
     * @author yongjun.ji
     * @date 2018/3/23
     * @param para 要校验的对象
     * @param paraName 对象名称
     * @throws RuntimeException 业务异常
     */
    public static void validateNotNull(Object para, String paraName) {
        if (para == null) {
            throw new RuntimeException(paraName + "不能为空！");
        }
        if (para instanceof String) {
            if (StringUtils.isEmpty((String) para)) {
                throw new RuntimeException(paraName + "不能为空！");
            }
        }
    }

    /**
     * 校验必选或必输
     * @param para 必选的字段
     * @param tips 提示语
     */
    public static void checkRequired(Object para, String tips) {
        if (para == null) {
            throw new RuntimeException(tips);
        }
        if (para instanceof String) {
            if (StringUtils.isEmpty((String) para)) {
                throw new RuntimeException(tips);
            }
        }
    }

    @Data
    static class Dog {
        @NotNull
        private String name;
        @Min(value = 0,message = "年龄不能为负数")
        private Integer age;
    }

    public static void main(String[] args) {
        Dog dog = new Dog();
        validate(dog);
    }
}
