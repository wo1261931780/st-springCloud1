package wo1261931780.orderService.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wo1261931780.feignApi.pojo.FeignOrder;
import wo1261931780.orderService.service.OrderService;

/**
 * @author wo1261931780
 */
@RestController
@RequestMapping("/order")
@RefreshScope
public class OrderController {
	// RefreshScope实现配置热更新，是在nacos中配置的

	@Autowired
	private OrderService orderService;

	// 这里是测试配置nacos热更新的代码=========================================
	// @Value("${pattern.dateformat}")
	// private String pattern;

	// @GetMapping("now")
	// public String now() {
	// 	return LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern));
	// }
	// 这里是测试配置nacos热更新的代码=========================================

	// /**
	//  * 根据id查询订单，使用restTemplate，内置了url
	//  *
	//  * @param orderId 订单id
	//  * @return 订单信息
	//  */
	// @GetMapping("{orderId}")
	// public TbOrder queryOrderByUserId(@PathVariable("orderId") Long orderId) {
	// 	// 根据id查询订单并返回
	// 	return orderService.queryOrderById(orderId);
	// }

	/**
	 * 下面是使用feign改造的接口调用
	 *
	 * @param orderId 订单id
	 * @return 订单信息
	 */
	@GetMapping("{orderId}")
	public FeignOrder queryOrderByUserId2(@PathVariable("orderId") Long orderId) {
		// 所有的逻辑还是在service中完成，但是组装的过程不一样了
		return orderService.queryById2(orderId);
	}
}
