package spring.service;

import java.text.ParseException;
import java.util.List;

import entity.MapperOrder;
import entity.Order;
import entity.Passenger;

/**
 * ���������ӿ�
 * @author ��Ԫ��
 *
 */
public interface IOrderService {
	
    /**
     * ͨ����Ż�ȡ����
     * @param id
     * @return
     */
	public Order getOrderByOrderId(Integer id);
	
	/**
	 * ͨ�����ȡ������
	 * @param id
	 * @return
	 */
	public Integer cancelOrderById(Integer id);
	
	/**
	 * ��ʱ������ʱ����
	 */
	public void cancelOrder();
	
	/**
	 * ͨ�������ı��ɾ������
	 * @param id
	 * @return
	 */
	public Integer deleteOrderById(Integer id);
	
	/**
	 * Ϊ��������
	 * @param id �������
	 */
	public void payForOrder(Integer id);
	
	/**
	 * ͨ���˿ͻ�ȡ���еĶ�����Ϣ
	 * @param passengers �˿���Ϣ����
	 * @return
	 */
	public List<MapperOrder> getOrderByPassenger(List<Passenger> passengers);
	
	/**
	 * ͨ��������Ž�����Ʊ����
	 * @param id �������
	 */
	public void returnTicket(Integer id) throws ParseException;
	
	/**
	 * ��ǩ�и��¶����Ĳ���
	 * @param trafficDateId ��ͨ�������ڱ��
	 * @param totlePrice �ܼ۸�����
	 * @param explain ��λ��������
	 * @param order ����ʵ��
	 */
	public void updateOrder(Integer trafficDateId,String totlePrice,String explain,Order order);
}
