package io.renren.modules.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Administrator
 */
@Configuration
public class RabbitConfig {

    //声明交互器
    @Bean
    DirectExchange directExchange() {
        return new DirectExchange("directExchange");
    }
    //====================================================================
	//声明队列
    @Bean
    public Queue queue1() {
        return new Queue("hello.queue1", true); // true表示持久化该队列
    }
    
    @Bean
    public Queue queue2() {
        return new Queue("hello.queue2", true);
    }

    //供应商消息队列
    @Bean
    public Queue supplierQueue(){
        return new Queue("supplierQueue",true);
    }
    //====================================================================
    //绑定
    @Bean
    public Binding binding1() {
        return BindingBuilder.bind(queue1()).to(directExchange()).with("key.1");
    }
    @Bean
    public Binding binding2() {
        return BindingBuilder.bind(queue2()).to(directExchange()).with("key.#");
    }
    @Bean
    public Binding bindingSupplierQueue(){
        return BindingBuilder.bind(supplierQueue()).to(directExchange()).with("supplierQueue");
    }
    
}