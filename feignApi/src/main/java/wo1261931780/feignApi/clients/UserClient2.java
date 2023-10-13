package wo1261931780.feignApi.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import wo1261931780.userService.pojo.TbUser;

/**
 * @author wo1261931780
 */
@FeignClient("userService")
public interface UserClient2 {
	// FeignClient只能作为一个接口的注解而存在

	@GetMapping("/user/{id}")
	TbUser findById2(@PathVariable("id") Long id);

}
