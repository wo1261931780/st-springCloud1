package wo1261931780.userService.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * Created by Intellij IDEA.
 * Project:st-springCloud0
 * Package:wo1261931780.userService.pojo
 *
 * @author liujiajun_junw
 * @Date 2023-10-20-14  星期二
 * @Description
 */

@ApiModel(description = "tb_user")
@Schema
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "spring001.tb_user")
public class TbUser implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "主键")
	@Schema(description = "主键")
	private Long id;
	/**
	 * 收件人
	 */
	@ApiModelProperty(value = "收件人")
	@Schema(description = "收件人")
	private String username;
	/**
	 * 地址
	 */
	@ApiModelProperty(value = "地址")
	@Schema(description = "地址")
	private String address;
}
