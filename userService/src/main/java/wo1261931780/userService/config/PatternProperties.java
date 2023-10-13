package wo1261931780.userService.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author wo1261931780
 */
@Data
@Component
@ConfigurationProperties(prefix = "pattern")
public class PatternProperties {
	// 更新配置文件的第二种方式，使用@ConfigurationProperties注解，
	// 将配置文件中的值注入到bean中，然后在需要的地方使用@Autowired注解注入即可。

	private String dateformat;
	// 这里采用的是约定大于配置的方式，即配置文件中的key必须与bean中的属性名一致，否则无法注入。
	// 前缀名+变量名，如：pattern.dateformat
	// 就可以完成属性注入
	private String envSharedValue;
	// 上面就是共享的配置属性
	private String name;
}
