package spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.ReturnedType;
import org.springframework.stereotype.Service;

import entity.Passenger;
import spring.mapper.PassengerMapper;
import spring.service.IPassengerService;

/**
 * 乘客服务实现类
 * @author 李元浩
 *
 */
@Service
public class PasengerServiceImpl implements IPassengerService{
   
	@Autowired
	private PassengerMapper passengerMapper;
	/**
	 * 根据乘客编号获得乘客信息
	 * @param id 乘客编号
	 * @return
	 */
	public List<Passenger> getPassengersById(Integer[] ids){
		
		return passengerMapper.getPassengersById(ids);
	}
	
	/**
	 * 根据乘客编号获得乘客信息
	 * @return
	 */
	public Passenger getPassengerById(Integer id){
		
		return passengerMapper.getPassengerById(id);
	}

	/**
	 * 通过证件号码获取乘客的所有信息
	 * @param type 证件类型
	 * @param code 证件号码
	 * @return
	 */
	public List<Passenger> getPassengerByCodeAndType(String type, String code) {	
		
		List<Passenger> passengers = passengerMapper.getPassengersByCodeAndType(type, code);
		return passengers;
	}

}
