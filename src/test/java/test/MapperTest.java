package test;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sun.org.apache.bcel.internal.generic.NEW;

import entity.City;
import spring.mapper.StaticDataMapper;
import spring.service.impl.StaticDataServiceImpl;

public class MapperTest {
   public static void main(String[] args) {
	 ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("spring-mvc.xml");
	 StaticDataServiceImpl serviceImpl=context.getBean("staticDataService",StaticDataServiceImpl.class);
	 Long start=System.currentTimeMillis();
	 List<City> cities=serviceImpl.getAllCity();
	 System.out.println(cities);
	 Long end=System.currentTimeMillis();
	 System.out.println(end-start);
}
}
