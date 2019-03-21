package spring.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import entity.Manager;
import spring.mapper.ManagerMapper;
import spring.service.IManagerService;

/**
 * 管理层服务实现类
 * @author 李元浩
 */
@Service
public class IManagerServiceImpl implements IManagerService {
	
	@Autowired
    private ManagerMapper managerMapper;

	/**
	 * 通过账号获取管理员对象
	 * @param phone
	 * @return
	 */
	public Manager getManagerById(String phone) {
		Manager manager = managerMapper.getManagerById(phone);
		return manager;
	}
	
	/**
	 * 判断密码是否正确
	 * @return
	 */
	public boolean confirmPassword(String passowrd,String cpassword,String salt){
		String pass = getMd5Password(passowrd, salt);
		if(pass.equals(cpassword)) {
			return true;
		} else {
			return false;
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

	/**
	 * 创建一个管理员
	 */
	public void createManager() {
        String salt = getSalt();
        String password = getMd5Password("123456", salt);
        managerMapper.createManager("10001", password, salt);
	}
}
