package io.renren;

import io.renren.modules.rabbitmq.config.Sender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(value=SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RenrenApplication.class)
public class RabbitTests {

	@Autowired
    private Sender sender;
    
    @Test
    public void sendTest() throws Exception {
//        while(true){
//            String msg = DateUtil.format(new Date(),"YYYY-MM-dd HH:mm:ss").toString();
//            sender.send(msg);
//            Thread.sleep(5000);
//        }
    }
}