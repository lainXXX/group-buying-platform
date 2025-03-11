package top.javarem.test.types.design.framework.link.model1;

import org.springframework.stereotype.Service;
import top.javarem.types.design.framework.link.model1.AbstractLogicLink;

/**
 * @Author: rem
 * @Date: 2025/03/10/14:54
 * @Description:
 */
@Service
public class LogicLink2 extends AbstractLogicLink {

    @Override
    public Object apply(Object requestParameter, Object dynamicContext) throws Exception {
        return "link2 applied";
    }
}
