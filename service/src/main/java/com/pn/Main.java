package com.pn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan("com.pn.mapper")
@EnableFeignClients
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
    //创建一个消息转换器，用于在消息发送和接收过程中将消息体与 JSON 格式的数据进行转换。
    @Bean
    public MessageConverter jsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }
}