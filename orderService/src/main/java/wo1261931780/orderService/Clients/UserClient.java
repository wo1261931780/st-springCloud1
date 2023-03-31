import org.apache.catalina.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("userService")
public interface UserClient {
    // 里面封装的是所有对userService发起的远程调用

    @GetMapping("/user/{id}")
    User findById(@PathVariable("id") Long id);
    // 声明一个远程调用


}