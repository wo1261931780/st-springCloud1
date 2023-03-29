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
		//String getUrl = "http://localhost:8081/user/" + orderMapperById.getUserId();
		//上面还是硬编码
		//我们建议使用的是配置文件中的服务名
		String getUrl = "http://orderServer/user/" + orderMapperById.getUserId();
		User forObject = restTemplate.getForObject(getUrl, User.class);
		//如果不指定对象的类型，name默认得到的是一个json字符串
		//这里指定了以后，我们就可以得到需要的结果，然后进一步合并
		orderMapperById.setUser(forObject);
		return orderMapperById;
	}

    /*@Autowired
    private RestTemplate restTemplate;

    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);
        // 2.利用RestTemplate发起http请求，查询用户
        // 2.1.url路径
        String url = "http://userservice/user/" + order.getUserId();
        // 2.2.发送http请求，实现远程调用
        User user = restTemplate.getForObject(url, User.class);
        // 3.封装user到Order
        order.setUser(user);
        // 4.返回
        return order;
    }*/
}
