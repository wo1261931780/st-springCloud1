package wo1261931780.feignApi.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;


/**
 * @author wo1261931780
 */
public class DefaultFeignConfiguration2 {
	@Bean
	public Logger.Level logLevel() {
		return Logger.Level.BASIC;
	}
}
