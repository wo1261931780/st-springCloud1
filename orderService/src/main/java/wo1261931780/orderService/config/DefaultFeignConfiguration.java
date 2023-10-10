package wo1261931780.orderService.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;

/**
 * @author 64234
 */ /*
 * Author: junw 45444154+wo1261931780@users.noreply.github.com
 * Date: 2023-04-01 00:59:49
 * LastEditors: junw 45444154+wo1261931780@users.noreply.github.com *
 * LastEditTime: 2023-04-01 00:59:58
 * FilePath:
 * \st-springCloud\orderService\src\main\java\wo1261931780\orderService\config\
 * DefaultFeignConfiguration.java
 * Description: 1111
 *
 * Copyright (c) 2023 by ${git_name_email}, All Rights Reserved.
 */
public class DefaultFeignConfiguration {
	@Bean
	public Logger.Level logLevel() {
		// 通过配置文件来配置日志级别
		// 也可以在yaml中写，效果一样
		// 一般用basic，一般full还是会消耗性能的
		return Logger.Level.FULL;
	}
}
