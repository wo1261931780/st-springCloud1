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

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import wo1261931780.feignApi.clients.UserClient2;
import wo1261931780.feignApi.pojo.FeignOrder;
import wo1261931780.orderService.mapper.TbOrderMapper;
import wo1261931780.orderService.pojo.TbOrder;
import wo1261931780.userService.pojo.TbUser;

/**
 * @author wo1261931780
 */
@Service
public class OrderService {

	@Autowired
	private TbOrderMapper tbOrderMapper;

	@Autowired
	@LoadBalanced
	private RestTemplate restTemplate;
	// 注入以后发送请求
	@Autowired
	private UserClient2 userClient2;

	/**
	 * 根据id查询订单,使用restTemplate
	 *
	 * @param orderId 订单id
	 * @return 订单信息
	 */
	public TbOrder queryOrderById(Long orderId) {
		// 1.查询订单
		TbOrder order = tbOrderMapper.findById(orderId);
		// 2.利用RestTemplate发起http请求，查询用户
		// String getUrl = "http://localhost:8081/user/" + order.getUserId();
		// 上面还是硬编码
		// 我们建议使用的是eureka注册中心里面展示的服务名,所以使用下面的方式
		// 2.1.url路径
		String url = "http://userservice/user/" + order.getUserId();
		// 2.2.发送http请求，实现远程调用
		TbUser user = restTemplate.getForObject(url, TbUser.class);
		// 如果不指定对象的类型，name默认得到的是一个json字符串
		// 这里指定了以后，我们就可以得到需要的结果，然后进一步合并
		// 3.封装user到Order
		// order.setUser(user);
		// 4.返回
		return order;
	}
	// 对于上面的代码，存在着可读性差，url难以维护的问题


	/**
	 * 注入Feign的接口
	 * 这里就实现了简化代码的操作
	 *
	 * @param userId 用户id
	 * @return 订单信息
	 */
	public FeignOrder queryById2(Long userId) {
		TbOrder order = tbOrderMapper.findById(userId);
		// 使用Client完成查询操作
		TbUser demoUser = userClient2.findById2(order.getUserId());
		// order.setUser(demoUser);
		FeignOrder feignOrder = new FeignOrder();
		BeanUtils.copyProperties(order, feignOrder);
		feignOrder.setUser(demoUser);
		return feignOrder;
	}
}
