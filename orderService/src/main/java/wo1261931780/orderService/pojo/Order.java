package wo1261931780.orderService.pojo;

import lombok.Data;
import wo1261931780.userService.pojo.User;

@Data
public class Order {
    private Long id;
    private Long price;
    private String name;
    private Integer num;
    private Long userId;
    private User user;
}
