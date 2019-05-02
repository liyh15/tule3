package spring.compoment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import dao.PassengerDao;
import service.UserService;

/**
 * user�������ļ���
 * @author ��Ԫ��
 *
 */
@Component
public class UserCompoment {
    
	/**
	 * ע��һ���˿�dao��
	 * @return
	 */
	@Bean
	public PassengerDao getPassgengerDao() {
		return new PassengerDao();
	}
	
	/**
	 * ע��һ��UserService����
	 * @return
	 */
	@Bean
	public UserService getUserService() {
		return new UserService();
	}
}
