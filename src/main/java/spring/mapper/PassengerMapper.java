package spring.mapper;



import java.util.List;

import org.apache.ibatis.annotations.Param;

import entity.Passenger;

/**
 * 乘客mybatis接口映射层
 * @author 李元浩
 */
public interface PassengerMapper {
    
	/**
	 * 根据乘客编号获得乘客信息
	 * @id 乘客编号
	 * @return 返回乘客信息
	 */
	public Passenger getPassengerById(@Param("id") Integer id);
	
	/**
	 * 根据乘客们的编号获取乘客们的信息
	 * @param id 乘客们的编号数组
	 * @return 返回乘客们信息
	 */
	public List<Passenger> getPassengersById(@Param("ids") Integer [] id);
}
