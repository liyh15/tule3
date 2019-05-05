package spring.mapper;



import java.util.List;

import org.apache.ibatis.annotations.Param;

import entity.Passenger;

/**
 * �˿�mybatis�ӿ�ӳ���
 * @author ��Ԫ��
 */
public interface PassengerMapper {
    
	/**
	 * ���ݳ˿ͱ�Ż�ó˿���Ϣ
	 * @id �˿ͱ��
	 * @return ���س˿���Ϣ
	 */
	public Passenger getPassengerById(@Param("id") Integer id);
	
	/**
	 * ���ݳ˿��ǵı�Ż�ȡ�˿��ǵ���Ϣ
	 * @param id �˿��ǵı������
	 * @return ���س˿�����Ϣ
	 */
	public List<Passenger> getPassengersById(@Param("ids") Integer [] id);
	
	/**
	 * ͨ��֤�������֤�����ͻ�ȡ���г˿͵ļ���
	 * @param type ֤������
	 * @param code ֤������
	 * @return
	 */
	public List<Passenger> getPassengersByCodeAndType(@Param("type") String type, @Param("code") String code);
}
