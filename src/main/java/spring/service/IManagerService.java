package spring.service;

import entity.Manager;

/**
 * 管理层服务类接口
 * @author 李元浩
 *
 */
public interface IManagerService {
    
	/**
	 * 通过账号获取管理员对象
	 * @param phone
	 * @return
	 */
	public Manager getManagerById(String phone);
	
	/**
	 * 判断密码知否正确
	 * @param passowrd  输入的密码
	 * @param cpassword 密码
	 * @param salt 盐值
	 * @return
	 */
	public boolean confirmPassword(String passowrd,String cpassword,String salt);
	
	/**
	 * 创建一个管理员
	 */
	public void createManager();
}
