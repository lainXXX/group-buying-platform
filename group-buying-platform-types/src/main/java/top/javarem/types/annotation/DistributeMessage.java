package top.javarem.types.annotation;

import java.lang.annotation.*;

/**
 * @Author: rem
 * @Date: 2025/03/27/15:44
 * @Description:
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface DistributeMessage {

    String msgType();

    String deliveryType();

    int retryCount() default 5;

    int retryInterval() default 3;

    boolean async() default true;


}
