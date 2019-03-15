package spring.mapper;

import org.apache.ibatis.annotations.Param;

import entity.User;
public interface UserMapper {
	
    public User getUserByPhone(@Param("phone") String phone);
    
    public Integer changeUserPass(@Param("phone")String phone,@Param("newPass") String newPass);
    
}
