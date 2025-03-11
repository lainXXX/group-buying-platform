package top.javarem.test.types.design.framework.link.model1;

import org.springframework.stereotype.Service;
import top.javarem.types.design.framework.link.model1.AbstractLogicLink;

import javax.annotation.Resource;

/**
 * @Author: rem
 * @Date: 2025/03/10/14:54
 * @Description:
 */
@Service
public class LogicLink1 extends AbstractLogicLink {

    @Resource
    private LogicLink2 logicLink2;

    @Override
    public Object apply(Object requestParameter, Object dynamicContext) throws Exception {
        appendNext(logicLink2);
        return doNext(requestParameter, dynamicContext);
    }

}
