
@FeignClient("userService")
public class UserClient2 {

    @GetMapping("/user/{id}")
    User findById2(@PathVariable("id") Long id);

}
