package spring.service;

import java.util.List;

import entity.Passenger;

/**
 * �˿����͵ķ����ӿ�
 * @author ��Ԫ��
 */
public interface IPassengerService {
    
	/**
	 * ���ݳ˿ͱ�Ż�ó˿���Ϣ
	 * @param id �˿ͱ��
	 * @return
	 */
	public List<Passenger> getPassengersById(Integer[] ids);
	
	/**
	 * ���ݳ˿ͱ�Ż�ó˿���Ϣ
	 * @return
	 */
	public Passenger getPassengerById(Integer id);
	
	/**
	 * ͨ��֤�������ȡ�˿͵�������Ϣ
	 * @param type
	 * @param code
	 * @return
	 */
	public List<Passenger> getPassengerByCodeAndType(String type,String code);
}
