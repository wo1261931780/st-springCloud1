package wo1261931780.orderService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import wo1261931780.feignApi.clients.UserClient2;
import wo1261931780.feignApi.config.DefaultFeignConfiguration2;

/**
 * @author wo1261931780
 */
@SpringBootApplication
@EnableFeignClients(clients = UserClient2.class, defaultConfiguration = DefaultFeignConfiguration2.class)
@EnableDiscoveryClient
public class OrderServiceApplication {
	// EnableFeignClients加在启动类上，默认全局生效
	// clients = FeignClient.class指定扫描包，避免注入失败
	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}
// 一个服务，既可以是提供者，也可以是消费者
	// 如果是消费者，就需要调用其他服务的接口
	// 如果是提供者，就需要暴露接口
	// 消费者和提供者都是相对的概念
	// DefaultApplicationArguments.class找到配置的类

	/**
	 * 创建RestTemplate并注入Spring容器
	 * <p>
	 * 发送http请求的工具类
	 *
	 * @return RestTemplate
	 */
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		// 这个就是模拟前端，发送http请求的工具类
		// 使用这个工具类，来调用其他服务的接口
		// LoadBalanced注解，表示启动负载均衡
		// 通过这个发起的请求，要被ribbon拦截和处理
		return new RestTemplate();
	}

	// @Bean
	// public IRule iRules() {
	// 	// 这个是自定义的负载均衡算法
	// 	// 这个算法是随机的
	// 	// 针对的是orderService中的所有服务
	// 	return new RandomRule();
	// }

	// EnableFeignClients注解，表示开启Feign的功能
	// 首先要引入Feign的依赖
}
