package spring.compoment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import dao.PassengerDao;
import service.UserService;

/**
 * user的配置文件层
 * @author 李元浩
 *
 */
@Component
public class UserCompoment {
    
	/**
	 * 注入一个乘客dao层
	 * @return
	 */
	@Bean
	public PassengerDao getPassgengerDao() {
		return new PassengerDao();
	}
	
	/**
	 * 注入一个UserService对象
	 * @return
	 */
	@Bean
	public UserService getUserService() {
		return new UserService();
	}
}
