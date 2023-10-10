package wo1261931780.orderService.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wo1261931780.orderService.pojo.Order;
import wo1261931780.orderService.service.OrderService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/order")
@RefreshScope
public class OrderController {
	// RefreshScope实现配置热更新

	@Autowired
	private OrderService orderService;

	@Value("${pattern.dateformat}")
	private String pattern;

	@GetMapping("now")
	public String now() {
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern));
	}


	@GetMapping("{orderId}")
	public Order queryOrderByUserId(@PathVariable("orderId") Long orderId) {
		// 根据id查询订单并返回
		// return orderService.queryOrderById(orderId);
		return orderService.queryById(orderId);
	}
}
