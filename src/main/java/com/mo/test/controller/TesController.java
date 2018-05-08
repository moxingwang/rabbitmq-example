package com.mo.test.controller;

import com.mo.test.rabbit.MessageProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by MoXingwang on 2017/6/14.
 */
@RestController
public class TesController {

    @Autowired
    private MessageProducerService messageProducerService;

    @RequestMapping(value = "send/{msg}", method = RequestMethod.GET)
    public String test(@PathVariable(value = "msg") String msg) {
        messageProducerService.sendMessage(msg);
        return "OK";
    }

}
