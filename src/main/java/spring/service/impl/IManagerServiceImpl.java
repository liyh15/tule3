package spring.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import entity.Manager;
import spring.mapper.ManagerMapper;
import spring.service.IManagerService;

/**
 * ��������ʵ����
 * @author ��Ԫ��
 */
@Service
public class IManagerServiceImpl implements IManagerService {
	
	@Autowired
    private ManagerMapper managerMapper;

	/**
	 * ͨ���˺Ż�ȡ����Ա����
	 * @param phone
	 * @return
	 */
	public Manager getManagerById(String phone) {
		Manager manager = managerMapper.getManagerById(phone);
		return manager;
	}
	
	/**
	 * �ж������Ƿ���ȷ
	 * @return
	 */
	public boolean confirmPassword(String passowrd,String cpassword,String salt){
		System.out.println(salt);
		String pass = getMd5Password(passowrd, salt);
		System.out.println(pass);
		if(pass.equals(cpassword)) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * ��ȡ�������ֵ
	 * @return
	 */
	private String getSalt()
	{
		return UUID.randomUUID().toString().toUpperCase();
	}
	/**
	 * ��ȡ���ܺ������
	 * @param password ԭ����
	 * @param salt ��ֵ
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
	 * ���ַ�������md5����
	 * @param str
	 * @return
	 */
	private String md5(String str)
	{
		return DigestUtils.md5DigestAsHex(str.getBytes()).toUpperCase();
	}

	public String  aaa() {		
		return getMd5Password("123","B6ED8BC0-41F7-4728-B21E-CB1D50FC9886");
	}
	
	/**
	 * ����һ������Ա
	 */
	public void createManager() {
        String salt = getSalt();
        String password = getMd5Password("123456", salt);
        managerMapper.createManager("10002", password, salt);
	}
}
