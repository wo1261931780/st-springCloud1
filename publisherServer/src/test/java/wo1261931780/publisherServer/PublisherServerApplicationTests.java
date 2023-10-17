package wo1261931780.publisherServer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@SpringBootTest
@Slf4j
class PublisherServerApplicationTests {

	// @Test
	// void contextLoads() {
	// }

	@Test
	public void testSendMessages() throws IOException, TimeoutException {
		ConnectionFactory connectionFactory = new ConnectionFactory(); // 创建连接工厂
		connectionFactory.setHost("localhost"); // 连接主机
		connectionFactory.setPort(5672); // 连接端口
		connectionFactory.setVirtualHost("/"); // 虚拟主机
		connectionFactory.setUsername("root"); // 用户名
		connectionFactory.setPassword("root"); // 密码
		Connection connection = connectionFactory.newConnection(); // 创建连接
		Channel channel = connection.createChannel(); // 创建通道
		// 队列名称：
		String queueName = "test001.queue";
		channel.queueDeclare(queueName, true, false, false, null); // 声明队列
		String message = "junw555";
		channel.basicPublish("", queueName, null, message.getBytes()); // 发送消息
		log.info("消息发送成功：" + message);
		// String exchangeName = "test_fanout_exchange"; // 交换机名称
		// String routingKey = "test.fanout"; // 路由键
		channel.close(); // 关闭通道
		connection.close(); // 关闭连接

	}

}
