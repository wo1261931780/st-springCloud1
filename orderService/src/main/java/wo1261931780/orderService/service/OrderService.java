/*
 * Author: junw 45444154+wo1261931780@users.noreply.github.com
 * Date: 2023-03-29 09:25:07
 * LastEditors: junw 45444154+wo1261931780@users.noreply.github.com
 * LastEditTime: 2023-04-01 00:41:30
 * FilePath: \st-springCloud\orderService\src\main\java\wo1261931780\orderService\service\OrderService.java
 * Description: 1111
 *
 * Copyright (c) 2023 by ${git_name_email}, All Rights Reserved.
 */
package wo1261931780.orderService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import wo1261931780.orderService.mapper.OrderMapper;
import wo1261931780.orderService.pojo.Order;
import wo1261931780.userService.pojo.User;

@Service
public class OrderService {

	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private RestTemplate restTemplate;
	// 注入以后发送请求

	public Order queryById(Long orderId) {
		Order orderMapperById = orderMapper.findById(orderId);
		// String getUrl = "http://localhost:8081/user/" + orderMapperById.getUserId();
		// 上面还是硬编码
		// 我们建议使用的是配置文件中的服务名
		String getUrl = "http://orderServer/user/" + orderMapperById.getUserId();
		User forObject = restTemplate.getForObject(getUrl, User.class);
		// 如果不指定对象的类型，name默认得到的是一个json字符串
		// 这里指定了以后，我们就可以得到需要的结果，然后进一步合并
		orderMapperById.setUser(forObject);
		return orderMapperById;
	}
	// 对于上面的代码，存在着可读性差，url难以维护的问题

	@Autowired
	private UserClient userClient;

	// 注入Feign的接口
	public Order queryById2(Long userId) {
		Order order = orderMapper.findById(userId);
		// 使用Client完成查询操作
		User demoUser = userClient.findById(order.getUserId());
		order.setUser(demoUser);
		return order;
	}

}
