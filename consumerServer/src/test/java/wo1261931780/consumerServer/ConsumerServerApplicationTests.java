package wo1261931780.consumerServer;

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
class ConsumerServerApplicationTests {

	// @Test
	// void contextLoads() {
	// }

	/**
	 * 测试接收消息
	 * @throws IOException 异常
	 * @throws TimeoutException 异常
	 */
	@Test
	public void getMessages() throws IOException, TimeoutException {
		ConnectionFactory connectionFactory = new ConnectionFactory(); // 创建连接工厂
		connectionFactory.setHost("localhost"); // 连接主机
		connectionFactory.setPort(5672); // 连接端口
		connectionFactory.setVirtualHost("/"); // 虚拟主机
		connectionFactory.setUsername("root"); // 用户名
		connectionFactory.setPassword("root"); // 密码
		Connection connection = connectionFactory.newConnection(); // 创建连接
		Channel channel = connection.createChannel(); // 创建通道
		// 队列名称：
		// 因为我们不清楚生产者和消费者谁先启动，所以我们在消费者端也声明一次队列
		// 只要队列的名称、虚拟主机、是否持久化、是否排他、是否自动删除等参数一致，就认为是同一个队列
		String queueName = "test001.queue";
		channel.queueDeclare(queueName, true, false, false, null); // 声明队列
		// 4.消费消息，也叫接收消息
		// channel.basicConsume(demoQueue, true, new DefaultConsumer(channel) {
		// DefaultConsumer，默认消费者
		// 	@Override
		// 	public void handleDelivery(String consumerTag, Envelope envelope,
		// 	                           AMQP.BasicProperties properties, byte[] body) throws IOException {
		// handleDelivery，处理消息的代码
		// 5.处理消息
		// 		String message = new String(body);// 消息体用byte[]接收，需要转换成字符串
		// 		log.info("接收到消息：【" + message + "】");
		// 	}
		// });// 这里只是完成了绑定，消息还没有接收到
		// log.info("等待接收消息。。。。");// 这里的代码会先执行，因为消息接收是异步的
		// 接收到消息以后，消息会立即被删除
		// 实际上，如果每次接收都用这种类似springMVC的结构，会非常麻烦
		// 所以我们使用了spring-amqp，它是spring对rabbitmq的封装
		// spring-amqp也是基于rabbitmq的原生api实现的
		// ============================================================================================
		// 这里用了内部类的lambda表达式
		// 这里是回调函数的机制，当消费者接收到消息后，会自动调用这个回调函数
		// 只是将消费者处理的回调函数和队列进行了绑定，如果下面有新的代码，会执行新的代码，然后上面的不变
		channel.basicConsume(queueName, true, (consumerTag, message) -> {
			log.info("接收到的消息：" + new String(message.getBody()));
		}, consumerTag -> {
			log.info("接收消息发生异常：" + consumerTag);
		});
		// 消息被消费以后，阅后即焚，不会再次出现在队列中
		channel.close(); // 关闭通道
		connection.close(); // 关闭连接
	}

}
