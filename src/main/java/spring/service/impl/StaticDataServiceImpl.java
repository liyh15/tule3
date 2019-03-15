package spring.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import entity.City;
import spring.mapper.StaticDataMapper;
import spring.service.StaticDateService;
import spring.service.ex.SystemException;

/**
 * 关于静态常量数据的service接口实现类
 * @author 李元浩
 *
 */
@Service("staticDataService")
public class StaticDataServiceImpl implements StaticDateService {

	@Autowired
	private StaticDataMapper mapper;
	
	/**
	 * 查询所城市的集合
	 */
	public List<City> getAllCity() throws SystemException {
		
		List<City> cities=mapper.getAllCity();	
		if(cities==null)
		{
			throw new SystemException("系统出现异常，请联系管理员！");
		}
		return cities;
	}
}
