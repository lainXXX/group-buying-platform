package top.javarem.test.types.design.framework.link.model2;

import org.springframework.stereotype.Service;
import top.javarem.types.design.framework.link.model2.handler.ILinkHandler;

/**
 * @Author: rem
 * @Date: 2025/03/10/20:22
 * @Description:
 */
@Service
public class LogicLink01 implements ILinkHandler<String, Factory.DynamicContext, XResponse> {

    @Override
    public XResponse apply(String requestParameter, Factory.DynamicContext dynamicContext) throws Exception {

        System.out.println("logicLink01 exec");
        return next(requestParameter, dynamicContext);

    }
}
