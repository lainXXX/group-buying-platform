package top.javarem.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RBucket;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import top.javarem.types.annotation.DCCValue;
import top.javarem.types.common.Constants;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: rem
 * @Date: 2025/03/07/14:32
 * @Description: 基于redis发布订阅实现动态配置中心
 */
@Slf4j
@Component
public class DCCValueBeanFactory implements BeanPostProcessor {

    private final static String BASE_CONFIG_PATH = "group_buying_dcc_";

    private final static String TOPIC = "group_buying_dcc";

    private final RedissonClient redissonClient;

    private final Map<String, Object> dccObjGroup = new HashMap<>();

    public DCCValueBeanFactory(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    @Bean("dccTopic")
    public RTopic dccListener(RedissonClient redissonClient) {
//        1.获取topic对象
        RTopic topic = redissonClient.getTopic(TOPIC);
//        2.设置和添加监听器内容
        topic.addListener(String.class, (channel, msg) -> {
//            2.1 按设定规则分割msg 格式: attribute,value
            String[] split = msg.split(Constants.COMMA);
            if (split.length != 2) {
                throw new RuntimeException("message length error");
            }
//            获取属性
            String attribute = split[0];
//            获取值
            String value = split[1];
            String key = BASE_CONFIG_PATH + attribute;
            RBucket<String> bucket = redissonClient.getBucket(key);
            /**
             * 因为postProcessAfterInitialization方法执行在@bean注解标识的方法之后
             * 而且dccGroup和bucket的的内容封装是在postProcessAfterInitialization中执行的
             * 所以bucket不存在直接返回即可 因为不存在也说明dccGroup中没有bean 下面围绕bean执行的逻辑也没有必要执行了
             */
            if (!bucket.isExists()) return;
            bucket.set(value);
            Object objBean = dccObjGroup.get(key);
            if (objBean == null) return;
            Class<?> beanClass = objBean.getClass();
//            如果bean为代理对象 那么设置beanClass为目标对象 因为目标对象才是实际对象 才能真实改名属性
            if (AopUtils.isAopProxy(objBean)) {
                beanClass = AopUtils.getTargetClass(objBean);
            }
            try {
//            1. getDeclaredField 方法用于获取指定类中声明的所有字段，包括私有字段、受保护字段和公共字段。
//            2. getField 方法用于获取指定类中的公共字段，即只能获取到公共访问修饰符（public）的字段。
                Field field = beanClass.getDeclaredField(attribute);
                field.setAccessible(true);
                field.set(objBean, value);
                field.setAccessible(false);
                log.info("DCC节点监听 key : {}, value : {}", key, value);
            } catch (Exception e) {

                throw new RuntimeException(e);

            }

        });
        return topic;

    }

    /**
     * 在bean完全初始化之后的处理 可以更新所有属性设置和初始化方法的调用
     * postProcessAfterInitialization方法会对每个bean都调用一次
     * @param bean     bean对象
     * @param beanName bean名称
     * @return 修改后的bean对象
     * @throws BeansException 异常
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        Class<?> targetBeanClass = bean.getClass();
        Object targetBean = bean;
        if (AopUtils.isAopProxy(bean)) {
            targetBeanClass = AopUtils.getTargetClass(bean);
            targetBean = AopProxyUtils.getSingletonTarget(targetBean);
        }
        Field[] fields = targetBeanClass.getDeclaredFields();
        for (Field field : fields) {
            if (!field.isAnnotationPresent(DCCValue.class)) continue;
            DCCValue dccValue = field.getAnnotation(DCCValue.class);
            String value = dccValue.value();
            String[] split = value.split(Constants.COLON);
            String attribute = split[0];
            String key = BASE_CONFIG_PATH + attribute;
            String defaultValue = split.length == 2 ? split[1] : null;
            String setValue = defaultValue;

            try {
                if (StringUtils.isBlank(defaultValue)) {
                    throw new RuntimeException("dcc config error");
                }
                RBucket<String> bucket = redissonClient.getBucket(key);
                if (!bucket.isExists()) {
                    bucket.set(defaultValue);
                } else {
                    setValue = bucket.get();
                }
                field.setAccessible(true);
                field.set(bean, setValue);
                field.setAccessible(false);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            dccObjGroup.put(key, targetBean);

        }

        return bean;

    }
}
