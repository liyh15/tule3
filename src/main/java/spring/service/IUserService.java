package spring.service;

import java.io.File;

import spring.service.ex.PassErrorException;
import spring.service.ex.ServiceException;

public interface IUserService {
	
    public void changeUserPass(String phone,String oldPass,String newPass) throws PassErrorException;
    
    public File getUserImageUrlByUserId(Integer id);
    
	/**
	 * 上传头像路径到数据库
	 * @param id
	 * @param imageUrl
	 * @return
	 */
	public Integer putHeadImage(Integer id,String imageUrl);
	
	/**
	 * 检查哟用户填写的信息是否正确
	 * @param name 用户姓名
	 * @param type 用户证件类型
	 * @param userCode 用户证件号码
	 * @return
	 */
	public void checkPersonalCode(String name,String type,String userCode) throws ServiceException;
}
