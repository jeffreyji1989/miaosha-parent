package com.nuwa.miaosha.ms.producer;

import com.alibaba.fastjson.JSONObject;
import com.nuwa.miaosha.common.util.constant.CommonConstants;
import com.nuwa.miaosha.order.req.OrderCreateReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * kafka生产
 */
@Component
@Slf4j
public class KafkaProducer {
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    /**
     * 发送创建订单消息
     *
     * @param req
     */
    public void sendCreateOrderMsg(OrderCreateReq req) {
        String obj2String = JSONObject.toJSONString(req);
        log.info("准备发送消息为：{}", obj2String);
        //发送消息
        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(CommonConstants.MQ_TOPIC_CREATE_ORDER, obj2String);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable throwable) {
                //发送失败的处理
                log.error(CommonConstants.MQ_TOPIC_CREATE_ORDER + " - 生产者 发送消息失败：" + throwable.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, Object> stringObjectSendResult) {
                //成功的处理
                log.info(CommonConstants.MQ_TOPIC_CREATE_ORDER + " - 生产者 发送消息成功：" + stringObjectSendResult.toString());
            }
        });
    }
}
