package wo1261931780.consumerServer.component;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Created by Intellij IDEA.
 * Project:st-springCloud1
 * Package:wo1261931780.consumerServer
 *
 * @author liujiajun_junw
 * @Date 2023-10-14-15  星期二
 * @Description
 */
@Component
public class SpringRabbitMqListener {
	// 下面是基本队列的监听：
	// @RabbitListener(queues = "test001.queue")
	// // 队列可以指定多个，这里只指定了一个
	// public void receiveMessage(Object message) {
	// 	System.out.println("接收到消息：" + message);
	// }
	// 下面是工作队列的监听：

	/**
	 * 消费者1,只要监听到消息，就会自动确认
	 *
	 * @param message 消息
	 * @throws InterruptedException 异常
	 */
	@RabbitListener(queues = "test001.queue")
	public void receiveWorkQueueMessage1(Object message) throws InterruptedException {
		System.out.println("消费者1-接收到消息：" + message + LocalDateTime.now());
		Thread.sleep(20);
	}

	/**
	 * 消费者2
	 *
	 * @param message 消息
	 * @throws InterruptedException 异常
	 */
	@RabbitListener(queues = "test001.queue")
	public void receiveWorkQueueMessage2(Object message) throws InterruptedException {
		System.out.println("消费者2-接收到消息：" + message + LocalDateTime.now());
		Thread.sleep(200);
	}

	/**
	 * fanout模式，通过绑定的交换机，将消息发送到所有绑定的队列
	 *
	 * @param message 消息
	 * @throws InterruptedException 异常
	 */
	@RabbitListener(queues = "fanout-test001.queue1")
	public void receiveFanout1Message(Object message) throws InterruptedException {
		System.out.println("fanout-test001-queue1-接收到消息：" + message + LocalDateTime.now());
		Thread.sleep(200);
	}

	/**
	 * 消费者4,
	 * fanout.direct就是一个交换机，只不过这个交换机的类型是direct，所以可以通过key来进行绑定
	 * 然后我们通过key来进行发送消息，就可以发送到指定的队列
	 *
	 * @param message 消息
	 * @throws InterruptedException 异常
	 */
	@RabbitListener(queues = "fanout-test001.queue2")
	public void receiveFanout2Message(Object message) throws InterruptedException {
		System.out.println("fanout-test001-queue2-接收到消息：" + message + LocalDateTime.now());
		Thread.sleep(200);
	}

	// 上面的声明方法太多太复杂，
	// 我们可以使用注解的方式来简化，如下：
	// @RabbitListener(queuesToDeclare = @Queue("test001"))
	// public void receiveMessage(Object message) {
	// 	System.out.println("接收到消息：" + message);
	// }
	// 上面的代码中，我们使用了queuesToDeclare属性，
	// 这个属性是用来声明队列的，如果队列不存在则会自动创建。
	// 但是这种方式创建的队列是持久化的，而且是非独占的，
	// 也就是说，如果我们在不同的地方声明了同一个队列，
	// 那么这个队列就会变成共享队列，也就是说，
	// 多个消费者都可以监听这个队列，这种方式不是我们想要的，
	// 我们需要的是一个独占的队列，这样才能保证每个消费者都能接收到消息。
	// 所以我们需要使用如下方式来声明队列：
	// @RabbitListener(queuesToDeclare = @Queue(
	// 		value = "test001",
	// 		durable = "true",
	// 		exclusive = "false",
	// 		autoDelete = "false"
	// ))
	// public void receiveMessage(Object message) {
	// 	System.out.println("接收到消息：" + message);
	// }
	// 还有一种修改exchange类型的声明方法：

	/**
	 * 手动声明一个不同类型的交换机
	 *
	 * @param message 消息
	 */
	@RabbitListener(bindings = @QueueBinding( // 绑定队列
			value = @Queue(name = "direct1.queue1"), // 不指定名字，rabbitmq会随机生成一个名字
			exchange = @Exchange(name = "test001.direct", type = ExchangeTypes.DIRECT), // 指定交换机的名字和类型
			key = {"red", "blue"} // 指定路由key
	))
	public void listenDirectQueue1(String message) {
		System.out.println("消费者1-接收到消息：" + message + LocalDateTime.now());
	}

	@RabbitListener(bindings = @QueueBinding( // 绑定队列
			value = @Queue(name = "direct1.queue2"), // 不指定名字，rabbitmq会随机生成一个名字
			exchange = @Exchange(name = "test001.direct", type = ExchangeTypes.DIRECT), // 指定交换机的名字和类型
			key = {"red", "yellow"} // 指定路由key
	))
	public void listenDirectQueue2(String message) {
		System.out.println("消费者2-接收到消息：" + message + LocalDateTime.now());
	}


}
