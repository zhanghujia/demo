package com.example.web;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class WebApplicationTests {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    void contextLoads() {

//        Message 需要自己构造 定义消息体内容和消息头
//        rabbitTemplate.send(exchange,routingKey,message);
//        只需要传入要发送的对象,自动序列化转发给rabbitmq
//        rabbitTemplate.convertAndSend(exchange,routingKey,object);

        Map<String, String> map = new HashMap<>();
        map.put("code", "200");
        map.put("msg", "服务请求成功");
        map.put("data", "value");
        rabbitTemplate.convertAndSend("exchange.direct", "jia.news", map);

    }

    @Test
    void receive() {
        Object o = rabbitTemplate.receiveAndConvert("jia.news");
        System.out.println(o.getClass());
        System.out.println(o);
    }

}
