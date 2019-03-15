package spring.compoment;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import dao.TrainDao;

/**
 * 基础配置类
 * @author 李元浩
 *
 */
@Component
public class OrderCompoment {

	/**
	 * 自定servlet订单dao层
	 * @return
	 */
	@Bean
	public TrainDao returnTrainDao(){
		return new TrainDao();
	}
}
