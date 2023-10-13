package wo1261931780.userService.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import wo1261931780.userService.config.PatternProperties;
import wo1261931780.userService.pojo.TbUser;
import wo1261931780.userService.service.UserService;

/**
 * @author wo1261931780
 */
@Slf4j
@RestController
@RequestMapping("/user")
@RefreshScope
public class UserController {

	@Autowired
	private UserService userService;

	// 这里是用来读取配置文件的，判断能否生效
	// @Value("${pattern.dateformat}")
	// private String dateformat;
	// 目前上面还没有实现热更新，
	// 添加了RefreshScope注解，就可以实现
	//========================================================
	// 下面就是通过注入实现的
	@Autowired
	private PatternProperties properties;

	// @GetMapping("now")
	// public String now() {
	// 	return LocalDateTime.now().format(DateTimeFormatter.ofPattern(dateformat, Locale.CHINA));
	// }

	// @GetMapping("prop")
	// public PatternProperties properties() {
	// 	return properties.getDateformat();
	// }

	//========================================================
	// 在nacos中，有多环境共享配置和环境特殊配置，共享配置是所有环境都可以使用的，环境特殊配置是只有当前环境可以使用的。
	// 一般会先读取userServer-dev.properties，然后再读取userServer.properties，如果有相同的配置，那么后面的会覆盖前面的。
	// 我们在userServer-dev.properties中添加公共配置，然后在userServer.properties中添加环境特殊配置
	// 就可以避免重复开发
	@GetMapping("prop2")
	public PatternProperties properties() {
		// 从这里就可以看到所有变量按照json格式输出
		return properties;
	}

	/**
	 * 路径： /user/110
	 *
	 * @param id 用户id
	 * @return 用户
	 */
	@GetMapping("/{id}")
	public TbUser queryById(@PathVariable("id") Long id,
	                        @RequestHeader(value = "Truth", required = false) String truth) {
		// RequestHeader注解，可以获取请求头中的信息，这就是在yml中配置的网关过滤器才有的
		// required = false，表示可以不传，如果不传，那么就是null
		System.out.println("truth: " + truth);
		return userService.queryById(id);
	}
}
