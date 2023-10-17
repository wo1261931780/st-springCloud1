package wo1261931780.consumerServer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

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
	@RabbitListener(queues = "test001.queue")
	// 队列可以指定多个，这里只指定了一个
	public void receiveMessage(Object message) {
		System.out.println("接收到消息：" + message);
	}

}
