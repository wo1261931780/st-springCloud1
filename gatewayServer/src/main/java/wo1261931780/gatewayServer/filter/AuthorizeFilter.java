package wo1261931780.gatewayServer.filter;

//
// @Component
// @Order(0)
// public class AuthorizeFilter implements GlobalFilter, Ordered {
// // 这里的目的是实现一个全局过滤器，用来拦截所有的请求，
// // 判断是否有token，如果没有token，就拦截，如果有token，就放行。
//
// // @Order(0)注解，用来定义过滤器的执行顺序，
// // 值越小，优先级越高
//
// 	@Override
// 	Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
// 		// 1.获取request对象
// 		ServerHttpRequest request = exchange.getRequest();
// 		// 2.获取请求参数
// 		// MultiValueMap<String,String> demo= request.getQueryParams()
// 		// 这里是一个map的结构
// 		// key是参数名，value是参数值，都是字符串
// 		String token = request.getQueryParams().getFirst("token");
// 		// 3.判断token是否为空
// 		if (StringUtils.isEmpty(token)) {
// 			// 4.如果为空，拦截
// 			ServerHttpResponse response = exchange.getResponse();
// 			// 5.设置响应状态码，方便前端判断
// 			// 401就是未登录，
// 			response.setStatusCode(HttpStatus.UNAUTHORIZED);
// 			// 6.设置响应信息
// 			return response.setComplete();
// 		}
// 		// 7.如果不为空，放行
// 		return chain.filter(exchange);
// 		// chain.filter(exchange)
// 		// 实际上是从过滤器链中获取下一个过滤器，然后执行下一个过滤器的filter方法
// 	}
//
// 	/**
// 	 * 注解和实现接口都可以定义顺序
// 	 */
// 	@Override
// 	int getOrder() {
// 		// 这里是定义过滤器的执行顺序
// 		// 值越小，优先级越高
// 		return -1;
// 	}
//
// }
