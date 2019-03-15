package spring.service;

import java.util.List;

import entity.Passenger;

/**
 * 乘客类型的服务层接口
 * @author 李元浩
 */
public interface IPassengerService {
    
	/**
	 * 根据乘客编号获得乘客信息
	 * @param id 乘客编号
	 * @return
	 */
	public List<Passenger> getPassengersById(Integer[] ids);
	
	/**
	 * 根据乘客编号获得乘客信息
	 * @return
	 */
	public Passenger getPassengerById(Integer id);
}
