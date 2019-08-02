package io.renren.modules.rabbitmq.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ReturnCallback;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.UUID;

/**
 * @author Administrator
 */
@Component
public class Sender implements RabbitTemplate.ConfirmCallback, ReturnCallback {

	@Autowired
    private RabbitTemplate rabbitTemplate;
	public static final Logger LOGGER = LoggerFactory.getLogger(Sender.class);
    
    @PostConstruct
    public void init() {
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {  
            LOGGER.info("消息发送成功:" + correlationData);
        } else {
            LOGGER.info("消息发送失败:" + correlationData);
        }
        
    }

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        LOGGER.info(message.getMessageProperties().getCorrelationIdString() + " 发送失败");
        
    }

    //发送消息，不需要实现任何接口，供外部调用。
    public void send(String routingKey,String msg){
        
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        
        LOGGER.info("开始发送消息 : " + msg.toLowerCase());
        rabbitTemplate.convertAndSend("directExchange", routingKey, msg,correlationId);
        LOGGER.info("结束发送消息 : " + msg.toLowerCase());
    }
}