package spring.mapper;

import org.apache.ibatis.annotations.Param;

import entity.Manager;

public interface ManagerMapper {
    
	/**
	 * 通过账号获取管理员对象
	 * @param phone
	 * @return
	 */
	public Manager getManagerById(@Param("phone") String phone);
	
	/**
	 * 创建一个管理员
	 * @return
	 */
	public Integer createManager(@Param("phone") String phone,@Param("password") String password,@Param("salt") String salt);
}
