package spring.mapper;

import java.util.List;

import entity.City;

/**
 * 与静态常量数据对应的myBatis Mapper接口
 * @author 李元浩
 *
 */
public interface StaticDataMapper {
    
	/**
	 * 获取所有的城市
	 * @return 返回城市的集合
	 */
	List<City> getAllCity(); 
}
