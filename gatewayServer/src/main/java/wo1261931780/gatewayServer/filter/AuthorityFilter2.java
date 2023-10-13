package wo1261931780.gatewayServer.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Created by Intellij IDEA.
 * Project:st-springCloud0
 * Package:wo1261931780.gatewayServer.Filter
 *
 * @author liujiajun_junw
 * @Date 2023-10-14-07  星期二
 * @Description
 */
@Component
@Order(0)
public class AuthorityFilter2 implements GlobalFilter, Ordered {
	// 这里的目的是实现一个全局过滤器，用来拦截所有的请求，
// 判断是否有token，如果没有token，就拦截，如果有token，就放行。
// @Order(0)注解，用来定义过滤器的执行顺序，
// 值越小，优先级越高
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		// 1.获取请求参数
		ServerHttpRequest request = exchange.getRequest();
		MultiValueMap<String, String> params = request.getQueryParams();
		// 2.获取参数中的 authorization 参数
		String auth = params.getFirst("authorization");
		// 3.判断参数值是否等于 admin
		if ("admin".equals(auth)) {
			// 4.是，放行
			return chain.filter(exchange);
		}
		// 5.否，拦截
		// 5.1.设置状态码
		exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
		// 5.2.拦截请求
		return exchange.getResponse().setComplete();
	}

	@Override
	public int getOrder() {
		return -1;
	}
}
