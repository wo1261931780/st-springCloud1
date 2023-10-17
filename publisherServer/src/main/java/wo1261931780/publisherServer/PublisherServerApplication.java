package wo1261931780.publisherServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

}
