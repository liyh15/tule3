package spring.service;

import java.util.List;

import entity.City;
import spring.service.ex.SystemException;

/**
 * 关于静态长数据的service实现接口
 * @author 李元浩
 *
 */
public interface StaticDateService {
    
	/**
	 * 获取所有的城市集合
	 * @return 返回城市集合
	 * @exception 系统异常
	 */
     List<City> getAllCity() throws SystemException;
}
