package top.javarem.test.types.design.framework.link.model2;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import top.javarem.types.design.framework.link.model2.BusinessLinkedList;
import top.javarem.types.design.framework.link.model2.ChainArmory;

import javax.annotation.Resource;

/**
 * @Author: rem
 * @Date: 2025/03/10/20:21
 * @Description:
 */
@Service
public class Factory {

    @Resource
    private LogicLink01 logicLink01;
    @Resource
    private LogicLink02 logicLink02;

    @Bean("chain01")
    public BusinessLinkedList<String, DynamicContext, XResponse> test_armory_01() {

        ChainArmory<String, DynamicContext, XResponse> chain01 = new ChainArmory<>("chain01", logicLink01, logicLink02);
        return chain01.getLogicLink();

    }

    @Bean("chain02")
    public BusinessLinkedList<String, DynamicContext, XResponse> test_armory_02() {

        ChainArmory<String, DynamicContext, XResponse> chain01 = new ChainArmory<>("chain01", logicLink01);
        return chain01.getLogicLink();

    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DynamicContext {

        String name;

    }

}
