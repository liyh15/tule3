package spring.mapper;

import org.apache.ibatis.annotations.Param;

import entity.User;
public interface UserMapper {
	
	/**
	 * 获取用户手机号
	 * @param phone
	 * @return
	 */
    public User getUserByPhone(@Param("phone") String phone);
    
    /**
     * 修改用户密码
     * @param phone
     * @param newPass
     * @return
     */
    public Integer changeUserPass(@Param("phone")String phone,@Param("newPass") String newPass);
    
    /**
     * 通过用户编号获取用户头像路径
     * @param id 用户编号
     * @return
     */
    public String getUserImageUrlByUserId(@Param("id") Integer id);
    
    /**
	 * 上传头像路径到数据库
	 * @param id
	 * @param imageUrl
	 * @return
	 */
	public Integer putHeadImage(@Param("id") Integer id, @Param("image") String imageUrl);
	
	/**
	 * 用户付款操作
	 * @param money 付款额度
	 * @param id 用户id
	 * @return
	 */
	public Integer payForMoney(@Param("money") Integer money,@Param("id") Integer id);
    
}
