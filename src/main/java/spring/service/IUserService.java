package spring.service;

import java.io.File;

import spring.service.ex.PassErrorException;
import spring.service.ex.ServiceException;

public interface IUserService {
	
    public void changeUserPass(String phone,String oldPass,String newPass) throws PassErrorException;
    
    public File getUserImageUrlByUserId(Integer id);
    
	/**
	 * �ϴ�ͷ��·�������ݿ�
	 * @param id
	 * @param imageUrl
	 * @return
	 */
	public Integer putHeadImage(Integer id,String imageUrl);
	
	/**
	 * ���Ӵ�û���д����Ϣ�Ƿ���ȷ
	 * @param name �û�����
	 * @param type �û�֤������
	 * @param userCode �û�֤������
	 * @return
	 */
	public void checkPersonalCode(String name,String type,String userCode) throws ServiceException;
}
