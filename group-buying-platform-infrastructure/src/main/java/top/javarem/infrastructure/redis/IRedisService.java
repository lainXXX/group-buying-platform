package top.javarem.infrastructure.redis;

import org.redisson.api.RBitSet;
import org.redisson.api.RTopic;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author: rem
 * @Date: 2025/03/03/16:27
 * @Description: 自定义Redisson服务接口
 */
public interface IRedisService {

    /**
     * 获取RBitSet
     *
     * @param key 键
     * @return
     */
    RBitSet getBitSet(String key);

    /**
     * 设置获取从用户Id
     *
     * @param userId 用户Id
     * @return
     */
    default int getIndexFromUserId(String userId) {

        try {
            //        1.获取MD5算法的实例
            MessageDigest md = MessageDigest.getInstance("MD5");
//        2.使用MD5算法处理这个字节序列 返回一个16字节的字节数组
            byte[] hashBytes = md.digest(userId.getBytes());
            /**
             * signum：这是一个整数，用于指定 BigInteger 的符号。1 表示正数
             * hashBytes：这是一个 byte 类型的数组，它包含了要转换成 BigInteger 的数据。
             */
            BigInteger bigInteger = new BigInteger(1, hashBytes);
//        mod 运算的结果是一个小于 Integer.MAX_VALUE 的 BigInteger 对象，
//        它表示 bigInteger 与 Integer.MAX_VALUE 的余数。
            return bigInteger.mod(BigInteger.valueOf(Integer.MAX_VALUE)).intValue();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 algorithm not found", e);
        }

    }
}
