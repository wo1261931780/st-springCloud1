package wo1261931780.consumerServer.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;

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
@Slf4j
public class SpringRabbitMqListener {
	// 下面是基本队列的监听：
	// @RabbitListener(queues = "test001.queue")
	// // 队列可以指定多个，这里只指定了一个
	// public void receiveMessage(Object message) {
	// 	log.info("接收到消息：" + message);
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
		log.info("消费者1-接收到消息：" + message + LocalDateTime.now());
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
		log.info("消费者2-接收到消息：" + message + LocalDateTime.now());
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
		log.info("fanout-test001-queue1-接收到消息：" + message + LocalDateTime.now());
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
		log.info("fanout-test001-queue2-接收到消息：" + message + LocalDateTime.now());
		Thread.sleep(200);
	}

	// 上面的声明方法太多太复杂，
	// 我们可以使用注解的方式来简化，如下：
	// @RabbitListener(queuesToDeclare = @Queue("test001"))
	// public void receiveMessage(Object message) {
	// 	log.info("接收到消息：" + message);
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
	// 	log.info("接收到消息：" + message);
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
		log.info("消费者1-接收到消息：" + message + LocalDateTime.now());
	}

	/**
	 * 手动声明一个不同类型的交换机
	 *
	 * @param message 消息
	 */
	@RabbitListener(bindings = @QueueBinding( // 绑定队列
			value = @Queue(name = "direct1.queue2"), // 不指定名字，rabbitmq会随机生成一个名字
			exchange = @Exchange(name = "test001.direct", type = ExchangeTypes.DIRECT), // 指定交换机的名字和类型
			key = {"red", "yellow"} // 指定路由key
	))
	public void listenDirectQueue2(String message) {
		log.info("消费者2-接收到消息：" + message + LocalDateTime.now());
	}

	/**
	 * 手动声明一个不同类型的交换机
	 *
	 * @param message 消息
	 */
	@RabbitListener(bindings = @QueueBinding(
			value = @Queue(name = "topic1.queue1"),
			exchange = @Exchange(name = "test001.topic", type = ExchangeTypes.TOPIC),
			key = {"china.#"} // 接受所有china有关的信息，如果写“#.news”，那就是接受所有news有关的信息
	))
	public void listenTopicQueue(String message) {
		log.info("消费者2-接收到消息：" + message + LocalDateTime.now());
	}

	/**
	 * 手动声明一个不同类型的交换机
	 *
	 * @param message 消息
	 */
	@RabbitListener(queues = "object.queue")
	public void listenObjectQueue(Map<String, Object> message) {
		log.info("接收到消息：" + message + LocalDateTime.now());
	}
	// 正向索引，做局部内容检索的时候效率比较差
	// 先找到文档，然后再找到文档中的内容是否包含关键字，
	// 倒排索引，做全局内容检索的时候效率比较高
	// 基于词条创建索引，然后通过词条找到文档，然后再找到文档中的内容是否包含关键字
	// 倒排索引更适合于基于内容的检索，
	// 正向索引更适合于基于属性的检索
	// 索引在MySQL中叫做库，但是在ElasticSearch中叫映射，mapping
	/*
	 docker run -d \
	 	--name es \
	     -e "ES_JAVA_OPTS=-Xms512m -Xmx512m" \ # 设置JVM内存
	     -e "discovery.type=single-node" \ # 单节点
	     -v es-data:/usr/share/elasticsearch/data \ # 挂载数据卷
	     -v es-plugins:/usr/share/elasticsearch/plugins \ # 挂载插件卷
	     --privileged \ # 允许容器内的程序使用特权模式
	     --network es-net \ # 指定网络，es容器加入网络
	     -p 9200:9200 \ # 暴露端口，9200是http端口，9300是tcp端口，tcp给节点互联
	     -p 9300:9300 \ # 暴露端口
	 elasticsearch:8.7.0
	 */

	// 我们使用的是ik分词器，需要安装ik分词器作为插件到docker
	// 然后，有分词类型，最小和最细
	// 最小分词，就是将一个词语分成一个一个的词汇，
	// 4个词到3个词到2个，以此类推
	// 如果4个有，那就不再继续分词
	// 程序员=程序员
	// 最细分词，就是将一个词语分成一个一个的词汇，然后再将这些词汇分成一个一个的字
	// 程序员=程序员，程序，员
	// ==============================
	// 其实分词器的底层，应该有字典，然后根据字典来进行文本分词
	// es中没有数组这个概念，只有集合（允许多个值存在）
	// ==============================

}
