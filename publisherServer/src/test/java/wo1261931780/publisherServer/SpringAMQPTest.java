package wo1261931780.publisherServer;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Created by Intellij IDEA.
 * Project:st-springCloud1
 * Package:wo1261931780.publisherServer
 *
 * @author liujiajun_junw
 * @Date 2023-10-14-03  星期二
 * @Description
 */
@SpringBootTest
public class SpringAMQPTest {
	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Test
	public void testSendMessage2Queue() {
		// rabbitTemplate.convertAndSend("hello-queue", "hello spring boot amqp");
		// 实际上只有两个参数，队列的名称，队列的内容
		String queueName = "test001.queue"; // 队列的名称
		String messageData = "junw555"; // 任意的二进制数据
		rabbitTemplate.convertAndSend(queueName, messageData); // 发送消息
	}

}
