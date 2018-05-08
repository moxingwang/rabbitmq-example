package com.mo.test.rabbit;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Service;

/**
 * @author MoXingwang on 2018-05-08.
 */
@Service
public class MessageListener implements org.springframework.amqp.core.MessageListener {
    private static Logger logger = LoggerFactory.getLogger(MessageListener.class);

    @Override
    public void onMessage(Message message) {
        logger.info("接收到的消息",JSON.toJSONString(message));
    }
}
