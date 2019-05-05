package spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.ReturnedType;
import org.springframework.stereotype.Service;

import entity.Passenger;
import spring.mapper.PassengerMapper;
import spring.service.IPassengerService;

/**
 * �˿ͷ���ʵ����
 * @author ��Ԫ��
 *
 */
@Service
public class PasengerServiceImpl implements IPassengerService{
   
	@Autowired
	private PassengerMapper passengerMapper;
	/**
	 * ���ݳ˿ͱ�Ż�ó˿���Ϣ
	 * @param id �˿ͱ��
	 * @return
	 */
	public List<Passenger> getPassengersById(Integer[] ids){
		
		return passengerMapper.getPassengersById(ids);
	}
	
	/**
	 * ���ݳ˿ͱ�Ż�ó˿���Ϣ
	 * @return
	 */
	public Passenger getPassengerById(Integer id){
		
		return passengerMapper.getPassengerById(id);
	}

	/**
	 * ͨ��֤�������ȡ�˿͵�������Ϣ
	 * @param type ֤������
	 * @param code ֤������
	 * @return
	 */
	public List<Passenger> getPassengerByCodeAndType(String type, String code) {	
		
		List<Passenger> passengers = passengerMapper.getPassengersByCodeAndType(type, code);
		return passengers;
	}

}
