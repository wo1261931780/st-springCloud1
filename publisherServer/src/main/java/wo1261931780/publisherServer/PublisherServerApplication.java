package wo1261931780.publisherServer;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author 64234
 */
@SpringBootApplication
public class PublisherServerApplication {
	// 启动类也是配置类的一种
	// 所以我们可以直接在里面添加序列化注解
	public static void main(String[] args) {
		SpringApplication.run(PublisherServerApplication.class, args);
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
