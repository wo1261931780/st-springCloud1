package wo1261931780.eurekaServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author wo1261931780
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {
	// @EnableEurekaServer
	// 该注解表明这是一个EurekaServer，提供服务注册和服务发现功能
	//eureka
	public static void main(String[] args) {
		SpringApplication.run(EurekaServerApplication.class, args);
	}

}
