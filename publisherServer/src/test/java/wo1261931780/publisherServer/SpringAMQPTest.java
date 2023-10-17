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

	/**
	 * 测试发送消息
	 */
	@Test
	public void testSendMessage2Queue() {
		// rabbitTemplate.convertAndSend("hello-queue", "hello spring boot amqp");
		// 实际上只有两个参数，队列的名称，队列的内容
		String queueName = "test001.queue"; // 队列的名称
		String messageData = "junw555"; // 任意的二进制数据
		rabbitTemplate.convertAndSend(queueName, messageData); // 发送消息

	}

	/**
	 * 测试发送消息
	 *
	 * @throws InterruptedException 异常
	 */
	@Test
	public void testSendMessage2WorkQueue() throws InterruptedException {
		String queueName = "test001.queue"; // 队列的名称
		String messageData = "junw555"; // 任意的二进制数据
		for (int i = 0; i < 50; i++) {
			rabbitTemplate.convertAndSend(queueName, messageData + i);
			Thread.sleep(20);// 20毫秒发送一次
		}
	}


	@Test
	void testSendFanoutExchange() {
		String exchangeName = "fanout-test001.exchange";
		// 这里必须写完整的交换机名称，否则会报错：org.springframework.amqp.AmqpException: No method found for class [B
		String messageData = "junw555"; // 任意的二进制数据
		rabbitTemplate.convertAndSend(exchangeName, "", messageData);
	}
	@Test
	void testSendDirectExchange() {
		String exchangeName = "test001.direct";
		// 这里必须写完整的交换机名称，否则会报错：org.springframework.amqp.AmqpException: No method found for class [B
		String messageData = "junw555"; // 任意的二进制数据
		rabbitTemplate.convertAndSend(exchangeName, "blue", messageData); // 发送消息，指定了routingKey
	}
}
