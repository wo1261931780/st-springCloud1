package wo1261931780.consumerServer;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ConsumerServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerServerApplication.class, args);
	}

	/**
	 * 配置消息转换器
	 * @return 消息转换器
	 */
	@Bean
	public MessageConverter messageConverter() {
		return new Jackson2JsonMessageConverter();
	}
}
