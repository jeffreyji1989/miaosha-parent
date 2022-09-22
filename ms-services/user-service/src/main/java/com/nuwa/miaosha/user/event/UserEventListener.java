package com.nuwa.miaosha.user.event;

import com.nuwa.miaosha.user.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * 用户事件监听
 */
@Component
@Slf4j
public class UserEventListener {

    /**
     * 通过class类型判断走哪些方法
     * @param user
     */
    @EventListener(classes = {User.class})
    @Order(100)
    public void onApplicationEvent(User user){
        log.info("请求到普通的事件通知:{}",user);
    }

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT, classes = User.class)
    @Order(99)
    public void userRegisterHandel(User user) {
        log.info("数据库事务执行操作完成:{}", user);
        //kafkaTemplate.send(KafkaTopic.SAVE_USER_TOPIC, user.getId() + "");

    }
}
