package wo1261931780.feignApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author wo1261931780
 */
@SpringBootApplication
@EnableFeignClients(basePackages = "wo1261931780.feignApi.clients")
public class FeignApiApplication {
	// @EnableFeignClients(basePackages = "wo1261931780.feignApi.feign")
	// 指定扫描的包，否则会报错：No Feign Client for name: feignClient
	// 这里实际上是为了解决依赖注入的问题，
	// 因为feignClient是在feignApi中定义的，而feignApi是被其他项目依赖的，所以需要指定扫描的包
	public static void main(String[] args) {
		SpringApplication.run(FeignApiApplication.class, args);
	}

}
