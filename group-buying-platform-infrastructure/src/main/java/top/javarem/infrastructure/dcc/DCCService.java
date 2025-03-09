package top.javarem.infrastructure.dcc;

import org.springframework.stereotype.Service;
import top.javarem.types.annotation.DCCValue;

/**
 * @Author: rem
 * @Date: 2025/03/09/14:56
 * @Description: dcc服务
 */
@Service
public class DCCService {

    @DCCValue("degradeSwitch:0")
    private String degradeSwitch;

    @DCCValue("cutRange:0")
    private String cutRange;

    public boolean isDowngradeSwitch() {
        return "1".equals(degradeSwitch);
    }

    public boolean isCutRange(String userId) {
        // 计算哈希码的绝对值
        int hashCode = Math.abs(userId.hashCode());

        // 获取最后两位
        int lastTwoDigits = hashCode % 100;

        // 判断是否在切量范围内
        if (lastTwoDigits <= Integer.parseInt(cutRange)) {
            return true;
        }

        return false;
    }

}
