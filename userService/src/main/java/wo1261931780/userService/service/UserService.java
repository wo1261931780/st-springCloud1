package wo1261931780.userService.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wo1261931780.userService.mapper.TbUserMapper;
import wo1261931780.userService.pojo.TbUser;

/**
 * @author wo1261931780
 */
@Service
public class UserService extends ServiceImpl<TbUserMapper, TbUser> {

	@Autowired
	private TbUserMapper tbUserMapper;

	/**
	 * 根据id查询用户
	 *
	 * @param id 用户id
	 * @return 用户对象
	 */
	public TbUser queryById(Long id) {
		return tbUserMapper.findById(id);
		// return tbUserMapper.selectByPrimaryKey(id); // 也会因为没有主数据源出现报错
	}
}
