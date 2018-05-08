package com.mo.test.rabbit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author MoXingwang on 2018-05-08.
 */
@Service
public class MessageProducerService {
    private static Logger logger = LoggerFactory.getLogger(MessageProducerService.class);

    @Autowired
    private RabbitTemplate amqpTemplate;

    public void sendMessage(Object message) {
        final int xdelay = 30;
        //发送延迟消息
        amqpTemplate.convertAndSend("messageDelayQueue", message,
                new MessagePostProcessor() {
                    @Override
                    public Message postProcessMessage(Message message)
                            throws AmqpException {
                        //设置消息持久化
                        message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                        //设置延迟时间（5分钟后执行）
                        message.getMessageProperties().setDelay(xdelay);
                        return message;
                    }
                });
    }


}
