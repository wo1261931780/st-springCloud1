package wo1261931780.consumerServer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Intellij IDEA.
 * Project:st-springCloud1
 * Package:wo1261931780.consumerServer.config
 *
 * @author liujiajun_junw
 * @Date 2023-10-15-13  星期二
 * @Description
 */
@Configuration
public class FanoutConfig {
	// 这里我们是通过配置类的方式来声明队列、交换机、绑定关系
	// 声明完毕以后，所有的绑定关系都会自动的在RabbitMQ中创建

	/**
	 * 声明一个fanout类型的交换机
	 *
	 * @return FanoutExchange
	 */
	@Bean
	public FanoutExchange fanoutExchange() {
		return new FanoutExchange("fanout-test001.exchange");
	}

	/**
	 * 声明两个队列
	 *
	 * @return Queue
	 */
	@Bean
	public Queue fanoutQueue1() {
		return new Queue("fanout-test001.queue1");
	}

	/**
	 * 将队列绑定到交换机上
	 *
	 * @param fanoutExchange fanoutExchange队列1
	 * @param fanoutQueue1   fanoutQueue1交换机
	 * @return Binding
	 */
	@Bean
	public Binding fanoutBinding1(FanoutExchange fanoutExchange, Queue fanoutQueue1) {
		// 将两个队列绑定到一个交换机上

		return BindingBuilder.bind(fanoutQueue1).to(fanoutExchange);
	}

	/**
	 * 声明两个队列
	 *
	 * @return Queue
	 */
	@Bean
	public Queue fanoutQueue2() {
		return new Queue("fanout-test001.queue2");
	}

	/**
	 * 将队列绑定到交换机上
	 *
	 * @param fanoutExchange fanoutExchange
	 * @param fanoutQueue2   fanoutQueue2
	 * @return Binding
	 */
	@Bean
	public Binding fanoutBinding2(FanoutExchange fanoutExchange, Queue fanoutQueue2) {
		return BindingBuilder.bind(fanoutQueue2).to(fanoutExchange);
	}

}
