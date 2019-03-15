package spring.service.impl;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import entity.User;
import spring.mapper.UserMapper;
import spring.service.IUserService;
import spring.service.ex.PassErrorException;
@Service("userService")
public class UserServiceImpl implements IUserService {
	@Autowired
	private UserMapper userMapper;
	/**
	 * 用户修改密码
	 * @param oldPass 旧密码
	 * @param newPass 新密码
	 * @exception 旧密码错误异常
	 */
	public void changeUserPass(String phone,String oldPass, String newPass) throws PassErrorException {
	     User user=userMapper.getUserByPhone(phone);
	     String pass=getMd5Password(oldPass, user.getSalt());
	     if(!user.getPassword().equals(pass))
	     {
	    	 throw new PassErrorException("原密码错误");
	     }
	     else {
	    	 userMapper.changeUserPass(phone, getMd5Password(newPass, user.getSalt()));
		}	     
	}
	/**
	 * 获取随机的盐值
	 * @return
	 */
	private String getSalt()
	{
		return UUID.randomUUID().toString().toUpperCase();
	}
	/**
	 * 获取加密后的密码
	 * @param password 原密码
	 * @param salt 盐值
	 * @return
	 */
	private String getMd5Password(String password,String salt)
	{
		String one=md5(password);
		String two=md5(salt);
		String three=one+two;
		for(int i=0;i<5;i++)
		{
			three=md5(three);
		}
		return three;
	}
	
	/**
	 * 加字符串进行md5加密
	 * @param str
	 * @return
	 */
	private String md5(String str)
	{
		return DigestUtils.md5DigestAsHex(str.getBytes()).toUpperCase();
	}
}
