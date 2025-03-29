package top.javarem.aspect;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import top.javarem.domain.message.adapter.repository.IMessageRepository;
import top.javarem.domain.message.model.entity.MessageContext;
import top.javarem.domain.message.model.entity.MessageEntity;
import top.javarem.domain.message.model.vo.MessageDeliveryTypeEnumsVO;
import top.javarem.domain.message.model.vo.MessageStatusEnumsVO;
import top.javarem.types.annotation.DistributeMessage;

import javax.annotation.Resource;

/**
 * @Author: rem
 * @Date: 2025/03/27/15:58
 * @Description: 消息处理切面aop
 */
@Component
@Aspect
@Slf4j
public class MessageProcessorAspect {

    @Resource
    private IMessageRepository messageRepository;

    @Pointcut("@annotation(top.javarem.types.annotation.DistributeMessage)")
    public void pointCut() {}

    @Around("pointCut() && @annotation(distributeMessage)")
    public Object around(ProceedingJoinPoint joinPoint, DistributeMessage distributeMessage) throws Throwable {

        MessageContext messageContext = (MessageContext) joinPoint.proceed();
//        如果事务不在活跃状态 则直接抛出异常
        if (!TransactionSynchronizationManager.isActualTransactionActive()) {
            throw new RuntimeException();
        }
        MessageEntity message = MessageEntity.builder()
                .msgId(RandomStringUtils.randomNumeric(11))
                .msgType(distributeMessage.msgType())
                .deliveryType(MessageDeliveryTypeEnumsVO.valueOf(distributeMessage.deliveryType()))
                .retryInterval(distributeMessage.retryInterval())
                .msgJson(messageContext.getMsgJson())
                .notifyUrl(messageContext.getNotifyUrl())
                .status(MessageStatusEnumsVO.CREATED)
                .build();
        messageRepository.distributeMessage(message, distributeMessage.async());
        return messageContext;

    }

}
