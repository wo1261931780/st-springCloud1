package wo1261931780.userService.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import wo1261931780.userService.pojo.TbUser;

import java.util.List;

/**
 * Created by Intellij IDEA.
 * Project:st-springCloud0
 * Package:wo1261931780.userService.pojo
 *
 * @author liujiajun_junw
 * @Date 2023-10-20-14  星期二
 * @Description
 */

@Mapper
public interface TbUserMapper extends BaseMapper<TbUser> {
	/**
	 * 根据id查询用户
	 *
	 * @param id 用户id
	 * @return 用户对象
	 */
	@Select("select * from tb_user where id = #{id}")
	TbUser findById(@Param("id") Long id);

	/**
	 * delete by primary key
	 *
	 * @param id primaryKey
	 * @return deleteCount
	 */
	int deleteByPrimaryKey(Long id);

	/**
	 * insert record to table
	 *
	 * @param record the record
	 * @return insert count
	 */
	int insert(TbUser record);

	int insertOrUpdate(TbUser record);

	int insertOrUpdateSelective(TbUser record);

	/**
	 * insert record to table selective
	 *
	 * @param record the record
	 * @return insert count
	 */
	int insertSelective(TbUser record);

	/**
	 * select by primary key
	 *
	 * @param id primary key
	 * @return object by primary key
	 */
	TbUser selectByPrimaryKey(Long id);

	/**
	 * update record selective
	 *
	 * @param record the updated record
	 * @return update count
	 */
	int updateByPrimaryKeySelective(TbUser record);

	/**
	 * update record
	 *
	 * @param record the updated record
	 * @return update count
	 */
	int updateByPrimaryKey(TbUser record);

	int updateBatch(List<TbUser> list);

	int updateBatchSelective(List<TbUser> list);

	int batchInsert(@Param("list") List<TbUser> list);
}
