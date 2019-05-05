package spring.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.TrainDao;
import entity.MapperOrder;
import entity.Order;
import entity.Passenger;
import entity.TrainSeat;
import spring.mapper.OrderMapper;
import spring.service.IOrderService;
import spring.service.ex.SystemException;
import utils.OrderUtil;
/**
 * ���������ʵ����
 * @author ��Ԫ��
 *
 */
@Service
public class OrderServiceImpl implements IOrderService {

	 @Autowired
	 private OrderMapper orderMapper;
	 @Autowired
	 private TrainDao trainDao;
    /**
     * ͨ��������Ż�ö���
     */
	public Order getOrderByOrderId(Integer id) {
		MapperOrder mapperOrder = orderMapper.getOrderByOrderId(id);
		Order order = null;
		if(null != mapperOrder) {
			order = changeMapperOrderToOrder(mapperOrder);		
		}		
		return order;
	}
	
	/**
	 * ��MapperOrder��ת��ΪOrder��
	 * @return
	 */
	private Order changeMapperOrderToOrder(MapperOrder mapperOrder){
		Order order = new Order();
		order.setId(mapperOrder.getId());
		order.setUserId(mapperOrder.getUserId());
		order.setStatus(mapperOrder.getStatus());
		order.setTrafficDateArrangeId(mapperOrder.getTrafficDateArrangeId());
		order.setTotlePrice(mapperOrder.getTotlePrice().split("&"));
		order.setPassengerId(getArrayB(mapperOrder.getPassengerId()));
		order.setType(mapperOrder.getType());
		order.setExplain(mapperOrder.getOexplain().split("&"));
		order.setReservation(mapperOrder.getReservation());;
		order.setReturnPrice(mapperOrder.getReturnPrice());
		order.setdAddress(mapperOrder.getDistributionAddress());
		order.setContactPhone(mapperOrder.getContactPhone());
		order.setCreateTime(mapperOrder.getCreateTime());
		order.setTimeClose(mapperOrder.getTimeClose());
		
		return order;
	}
	
	/**
	 * ת�������˿ͱ��
	 * @param line
	 * @return
	 */
	 private Integer[] getArrayB(String line)
	 {
		 String [] lines=line.split("&");
		 String [] lines2 = new String[lines.length-1];
		 System.arraycopy(lines, 1, lines2, 0, lines2.length);
		 Integer[] a=new Integer[lines2.length];
		 for(int i=0;i<lines2.length;i++)
		 {
			 a[i]=Integer.parseInt(lines2[i]);
		 }
		 
		 return a;
	 }
	
	
	/**
	 * ��String��������ת��ΪInteger��������
	 * @return
	 */
	private Integer[] changeStringToInteger(String [] str){
		Integer [] integers = new Integer[str.length];
	    for(int i = 0;i < str.length;i++){
	    	integers[i] = Integer.parseInt(str[i]);
	    }
		return integers;
	}

	/**
	 * ͨ�����ȡ������
	 */
	public Integer cancelOrderById(Integer id) {
		MapperOrder order = orderMapper.getOrderByOrderId(id);
		Integer tdaId = order.getTrafficDateArrangeId();
		String explain = order.getOexplain();
		String [] explains = explain.split("&");
		for(String e : explains){
			String [] seats = e.split(",");
			Integer type = TrainSeat.seatMap.get(seats[0]);		
			// ���ν���ǰ����λ�����ݿ������
			Integer code = trainDao.returnTrainTicket(tdaId, seats[1], type);
			if(200 != code){
				throw new SystemException("ϵͳ�����쳣�����Ժ�����");
			}
		}		
		// ���˶�����״̬��Ϊ��ȡ��
		return orderMapper.cancelOrderById(id);
	}

	/**
	 * ��ʱ������ʱ����
	 */
	public void cancelOrder() {
		List<Integer> orderIds = orderMapper.cancelOrder();
		if(orderIds.size() > 0){
			for(Integer id : orderIds){
				cancelOrderById(id);
			}
		}		
	}

	/**
	 * ͨ�������ı��ɾ������
	 * @param id
	 * @return
	 */
	public Integer deleteOrderById(Integer id) {
		return orderMapper.deleteOrderById(id);
	}

	/**
	 * Ϊ��������
	 * @param id �������
	 */
	public void payForOrder(Integer id) {
		
	    Integer line = orderMapper.payForOrder(id, OrderUtil.DEAL);
	    if(line < 1) {
	    	throw new SystemException("��������ʧ�ܣ�������");
	    }
	}
	
	/**
	 * ͨ���˿ͻ�ȡ���еĶ�����Ϣ
	 * @param passengers �˿���Ϣ����
	 * @return
	 */
	public List<MapperOrder> getOrderByPassenger(List<Passenger> passengers) {
		
		List<MapperOrder> mapperOrders = orderMapper.getOrderByPassenger(passengers);
		return mapperOrders;
	}
}




