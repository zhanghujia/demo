package com.example.web;

import com.example.business.entity.Users;
import com.example.business.mapper.UsersMapper;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class WebApplicationTests {

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private AmqpAdmin amqpAdmin;

    @Test
    void contextLoads() {
        Users users = Users.builder()
                .userId(1)
                .userName("root")
                .password("password")
                .build();
        System.out.println(users);

    }

    @Test
    void send() {

//        Message 需要自己构造 定义消息体内容和消息头
//        rabbitTemplate.send(exchange,routingKey,message);
//        只需要传入要发送的对象,自动序列化转发给rabbitmq
//        rabbitTemplate.convertAndSend(exchange,routingKey,object);

        Map<String, String> map = new HashMap<>();
        map.put("code", "200");
        map.put("msg", "服务请求成功");
        map.put("data", "value");
        rabbitTemplate.convertAndSend("exchange.direct", "jia.news", usersMapper.selectByPrimaryKey(1));
    }

    @Test
    void receive() {
        Object o = rabbitTemplate.receiveAndConvert("jia.news");
        assert o != null;
        System.out.println(o.getClass());
        System.out.println(o);
    }

    @Test
    void createExchange() {
        amqpAdmin.declareExchange(new DirectExchange("exchange.admin"));
        amqpAdmin.declareQueue(new Queue("admin", true));
        amqpAdmin.declareBinding(new Binding(
                "admin", Binding.DestinationType.QUEUE, "exchange.admin",
                "admin", null));
    }


}
