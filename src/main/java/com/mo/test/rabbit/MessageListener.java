package com.mo.test.rabbit;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author MoXingwang on 2018-05-08.
 */
//@Service
public class MessageListener implements ChannelAwareMessageListener {
    private static Logger logger = LoggerFactory.getLogger(MessageListener.class);

    @Override
    public void onMessage(Message message, Channel channel) throws IOException {
        logger.info("接收到的消息{}", JSON.toJSONString(new String(message.getBody())));
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        logger.info("deliveryTag= " + deliveryTag);
        //手动确认
        channel.basicAck(deliveryTag, false);
    }
}
