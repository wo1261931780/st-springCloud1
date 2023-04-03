package wo1261931780.feignApi.Clients;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 64234
 */
@FeignClient("userService")
public class UserClient2 {

	@GetMapping("/user/{id}")
	User findById2(@PathVariable("id") Long id) {
		return null;
	}

}
