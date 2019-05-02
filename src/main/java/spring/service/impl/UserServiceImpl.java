package spring.service.impl;
import java.io.File;
import java.util.UUID;

import javax.sql.rowset.serial.SerialException;

import org.apache.ibatis.annotations.One;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import entity.User;
import spring.mapper.CitizenMapper;
import spring.mapper.UserMapper;
import spring.service.IUserService;
import spring.service.ex.PassErrorException;
import spring.service.ex.ServiceException;
import spring.service.ex.SystemException;
@Service("userService")
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private CitizenMapper citizenMapper;
	
	private final static Integer ONE = 1;
	 
	@Value("#{user.headImage}")
	public String userImageUrl;
	/**
	 * �û��޸�����
	 * @param oldPass ������
	 * @param newPass ������
	 * @exception ����������쳣
	 */
	public void changeUserPass(String phone,String oldPass, String newPass) throws PassErrorException {
	     User user=userMapper.getUserByPhone(phone);
	     String pass=getMd5Password(oldPass, user.getSalt());
	     if(!user.getPassword().equals(pass))
	     {
	    	 throw new PassErrorException("ԭ�������");
	     }
	     else {
	    	 userMapper.changeUserPass(phone, getMd5Password(newPass, user.getSalt()));
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
	
	/**
	 * ͨ���û�id��ȡ�û���ͷ���ļ�
	 * @param id �û�id
	 * @return
	 */
	public File getUserImageUrlByUserId(Integer id){
		
		String imageUrl = userMapper.getUserImageUrlByUserId(id);
		if(null == imageUrl){
			// �����ڷ���ϵͳĬ�ϵ�ͷ��
			return new File(userImageUrl);
		}
		File file = new File(imageUrl);
		if(file.exists()){
			// ���ڷ����û����Զ����ͷ��
			return file;
		} else {
			// �����ڷ���ϵͳĬ�ϵ�ͷ��
			return new File(userImageUrl);
		}
	}
	
	/**
	 * �ϴ�ͷ��·�������ݿ�
	 * @param id
	 * @param imageUrl
	 * @return
	 */
	public Integer putHeadImage(Integer id, String imageUrl) {
		Integer state = userMapper.putHeadImage(id, imageUrl);
		return state;
	}
	
	/**
	 * ���Ӵ�û���д����Ϣ�Ƿ���ȷ
	 * @param name �û�����
	 * @param type �û�֤������
	 * @param userCode �û�֤������
	 * @return
	 */
	public void checkPersonalCode(String name,String type,String userCode) throws ServiceException {
		
		Integer count = citizenMapper.queryIsExist(name, type, userCode);		
		if(count < ONE) {
			throw new SystemException("�û�������");
		}
	}
}
