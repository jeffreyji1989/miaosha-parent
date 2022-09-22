package com.nuwa.miaosha.common.web.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * 封装spring的event
 */
@Component
public class EventPublisher {
    // spring提供的事件通知接口类
    private static ApplicationEventPublisher eventPublisher;

    /**
     * set注入
     *
     * @param eventPublisher
     */
    @Autowired
    public void setEventPublisher(ApplicationEventPublisher eventPublisher) {
        EventPublisher.eventPublisher = eventPublisher;
    }

    /**
     * 发布任意对象
     * 注意这里的发布处理都是同步的
     * 可以处理
     * 1.方法中加入注解  @EventListener(classes = {User.class})
     * 2.方法中加入注解 @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT, classes = User.class)(用于事物提交成功后的处理 课通过@Async 异步处理)
     * @param obj
     */
    public static final void publishEvent(Object obj) {
        eventPublisher.publishEvent(obj);
    }
}
