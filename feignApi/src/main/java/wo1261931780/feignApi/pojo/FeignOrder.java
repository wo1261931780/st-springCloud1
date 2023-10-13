package wo1261931780.feignApi.pojo;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import wo1261931780.userService.pojo.TbUser;

/**
 * @author wo1261931780
 */
@Data
public class FeignOrder {
	// 这里重新写为dto的形式，组装到一起
	/**
	 * 订单id
	 */
	@ApiModelProperty(value = "订单id")
	@Schema(description = "订单id")
	private Long id;
	/**
	 * 用户id
	 */
	@ApiModelProperty(value = "用户id")
	@Schema(description = "用户id")
	private Long userId;
	/**
	 * 商品名称
	 */
	@ApiModelProperty(value = "商品名称")
	@Schema(description = "商品名称")
	private String name;
	/**
	 * 商品价格
	 */
	@ApiModelProperty(value = "商品价格")
	@Schema(description = "商品价格")
	private Long price;
	/**
	 * 商品数量
	 */
	@ApiModelProperty(value = "商品数量")
	@Schema(description = "商品数量")
	private Integer num;

	/**
	 * 用户信息
	 */
	private TbUser user;
}
