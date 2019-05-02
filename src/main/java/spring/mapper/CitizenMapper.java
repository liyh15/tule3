package spring.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * 公民的数据库持久层mapper
 * @author 李元浩
 */
public interface CitizenMapper {
    
	/**
	 * 查询用户是否存在
	 * @param name 用户姓名
	 * @param type 用户类型
	 * @param code 证件号码
	 * @return
	 */
	public Integer queryIsExist(@Param("name") String name,
			@Param("type") String type,@Param("code") String code);
}
