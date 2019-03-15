package spring.controller;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.deser.Deserializers.Base;
import com.sun.org.apache.regexp.internal.recompile;
import entity.City;
import spring.service.StaticDateService;

/**
 * 关于静态数据的控制器
 * @author 李元浩
 *
 */
@Controller
@RequestMapping("/staticdata")
public class StaticDataController extends BaseController {
	 @Autowired
	 @Qualifier("staticDataService")
     private StaticDateService service;	 

	 @Autowired
	 @Qualifier("redisTemplate")
	 private RedisTemplate redisTemplate;
     /**
	 * 获取缓存中的城市数据
	 * @return 返回城市数据
	 */
	@RequestMapping("/getCity.do")
	@ResponseBody
	public ResultResponse<List<City>> getStaticDataCity()
	{			 	
		ResultResponse<List<City>> response=new ResultResponse<List<City>>();
		if(redisTemplate.opsForValue().get("tule_CITY")==null)
		{
			List<City> cities=service.getAllCity();
			redisTemplate.opsForValue().set("tule_CITY",cities);
			response.setParam(cities);
		}
		else
		{
			List<City> cities=(List<City>) redisTemplate.opsForValue().get("tule_CITY");
			response.setParam(cities);
		}	
		return response;
	}
}
