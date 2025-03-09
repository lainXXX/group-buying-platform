package top.javarem.types.annotation;

import java.lang.annotation.*;

/**
 * @Author: rem
 * @Date: 2025/03/07/14:08
 * @Description: DCC dynamic client configuration 动态客户端配置注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface DCCValue {

    String value() default "";

}
