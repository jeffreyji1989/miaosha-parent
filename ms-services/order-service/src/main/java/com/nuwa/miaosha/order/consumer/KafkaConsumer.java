package com.nuwa.miaosha.order.consumer;

import com.nuwa.miaosha.common.util.bean.BeanUtils;
import com.nuwa.miaosha.common.util.constant.CommonConstants;
import com.nuwa.miaosha.order.req.OrderCreateReq;
import com.nuwa.miaosha.order.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class KafkaConsumer {
    @Autowired
    private IOrderService orderService;

    @KafkaListener(topics = CommonConstants.MQ_TOPIC_CREATE_ORDER, groupId = CommonConstants.MQ_GROUP_ID)
    public void createOrder(ConsumerRecord<?, ?> record, Acknowledgment ack) {
        // 获取topic
        String topic = record.topic();
        log.info("topic：{}",topic);
        Optional message = Optional.ofNullable(record.value());
        if (message.isPresent()) {
            Object msg = message.get();
            log.info("{}消费了:Topic:{},Message:{}",CommonConstants.MQ_GROUP_ID,topic,msg);
            orderService.createOrder(BeanUtils.conversion(msg, OrderCreateReq.class));
            log.info("下单消费成功!");
            ack.acknowledge();
        }
    }
}
