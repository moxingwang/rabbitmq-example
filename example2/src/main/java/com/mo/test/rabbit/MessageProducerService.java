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

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author MoXingwang on 2018-05-08.
 */
@Service
public class MessageProducerService {
    private static Logger logger = LoggerFactory.getLogger(MessageProducerService.class);

    @Autowired
//    @Qualifier("messageDelayAmqpTemplate")
    private RabbitTemplate amqpTemplate;

    public void sendMessage(Object message) {
        logger.info("to send message:{}", message);
        final int xdelay = 300 * 1000;
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        //发送延迟消息
        amqpTemplate.convertAndSend("order.delay.notify", message,
                new MessagePostProcessor() {

                    @Override
                    public Message postProcessMessage(Message message)
                            throws AmqpException {
                        //设置消息持久化
                        message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                        //设置延迟时间（5分钟后执行）
                        message.getMessageProperties().setDelay(xdelay);
                        logger.info("----" + sf.format(new Date()) + " Delay sent.");

                        return message;
                    }
                });
    }


}
