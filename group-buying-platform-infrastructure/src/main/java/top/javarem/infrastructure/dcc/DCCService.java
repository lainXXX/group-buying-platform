package top.javarem.infrastructure.dcc;

import org.springframework.stereotype.Service;
import top.javarem.types.annotation.DCCValue;

import java.util.function.Supplier;

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

    @DCCValue("scBlacklist:s02c02")
    private String scBlacklist;

    @DCCValue("cacheSwitch:0")
    private String cacheOpenSwitch;

    /**
     * 缓存开启开关，0为开启，1为关闭
     */
    public boolean isCacheOpenSwitch(){
        return "0".equals(cacheOpenSwitch);
    }

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

    public boolean isScBlacklist(String source, String channel) {
        return scBlacklist.contains(source + channel);
    }

}
