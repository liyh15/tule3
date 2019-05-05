package spring.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import entity.MapperOrder;
import entity.Order;
/**
 * ������mabatis��mapper��
 * @author ��Ԫ��
 */
import entity.Passenger;
public interface OrderMapper {
	
	/**
	 * ͨ��������Ų�ѯָ������
	 * @param id �������
	 * @return
	 */
    public MapperOrder getOrderByOrderId(@Param("id") Integer id);
    
    /**
     * ͨ�����ȡ������
     * @param id �������
     * @return
     */
    public Integer cancelOrderById(@Param("id") Integer id);
    
    /**
     * ��ʱ������ʱ����
     * @return
     */
    public List<Integer> cancelOrder();
    
    /**
	 * ͨ�������ı��ɾ������
	 * @param id
	 * @return
	 */
	public Integer deleteOrderById(@Param("id") Integer id);
	
	/**
	 * �Զ������и���
	 * @param id �������
	 * @param status ����״̬
	 * @return
	 */
	public Integer payForOrder(@Param("id") Integer id,@Param("status") String status);
	
	/**
	 * ͨ���˿��������еĹ����Ķ���
	 * @param passengers �˿ͼ���
	 * @return ��������
	 */
	public List<MapperOrder> getOrderByPassenger(@Param("passengers") List<Passenger> passengers);
}
