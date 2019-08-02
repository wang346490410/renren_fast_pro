package io.renren.modules.rabbitmq.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 〈供应商Mq监听器〉
 *
 * @author wangjiaqi
 * @create 2019/8/1
 * @since 1.0.0
 */
@Component
public class SupplierInfoMqListener {

	public static final Logger LOGGER = LoggerFactory.getLogger(SupplierInfoMqListener.class);

	@RabbitListener(queues = "supplierQueue")
	public void  onMessage(String msg){
		LOGGER.info("=========消费供应商信息MQ开始,body={}", msg);
		LOGGER.info("=========消费供应商信息MQ开始,body={}", msg);

	}


}
