import org.apache.catalina.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// @FeignClient("userService")
// public interface UserClient {
//     // 里面封装的是所有对userService发起的远程调用

//     @GetMapping("/user/{id}")
//     User findById(@PathVariable("id") Long id);
//     // 声明一个远程调用
//     // 这里的调用地址，必须和userService中的请求路径一致
//     // feign有可以直接抽取出来，作为一个独立的模块
//     // 好处是避免了在不同的微服务中重复定义client

// }