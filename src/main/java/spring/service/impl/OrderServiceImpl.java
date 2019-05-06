package spring.service.impl;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.TrainDao;
import entity.MapperOrder;
import entity.Order;
import entity.Passenger;
import entity.TrainArrange;
import entity.TrainDateArrange;
import entity.TrainSeat;
import spring.mapper.OrderMapper;
import spring.mapper.TrainMapper;
import spring.mapper.UserMapper;
import spring.service.IOrderService;
import spring.service.ex.SystemException;
import utils.ConstantUtil;
import utils.DateUtil;
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
	 
	 @Autowired
	 private TrainMapper trainMapper;
	 
	 @Autowired
	 private UserMapper userMapper;
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

	/**
	 * ͨ��������Ž�����Ʊ����,��Ҫ���������Ϊ�ж�����ݱ�������û����ʽ��ֶκͶ�����״̬�ֶ�
	 * @param id �������
	 * @throws ParseException 
	 */
	@Transactional
	public void returnTicket(Integer id) throws ParseException {
		
		Order order = getOrderByOrderId(id);
		// ����Ʊ֮ǰ������Ҫ�жϵ�ǰ������״̬�Ƿ�֧����Ʊ,֧����Ʊ�Ķ�����״ֻ̬���ѳɽ�
		if(OrderUtil.DEAL.equals(order.getStatus())) {			
			// ������״̬Ϊ�ѳɽ�ʱ���ſ��Խ�����Ʊ����
            // �ڽ��в���֮ǰ����Ҫ�г����ڰ���ʱ���Ƿ�֧����Ʊ����
			System.out.println("�г����ڰ��ű��Ϊ��"+order.getTrafficDateArrangeId());
			TrainDateArrange trainDateArrangeOne = trainMapper.getTrainDateArrangeById(order.getTrafficDateArrangeId());
		    TrainArrange trainArrangeOne = trainMapper.getTrainArrangeById(trainDateArrangeOne.getTrainArrangeId());
		    // �𳵿���ʱ��
		    Long startTimeOne = DateUtil.getTimeByDate(trainDateArrangeOne.getDay()+" "+trainArrangeOne.getStartTime(), DateUtil.YYMMRRHHMMSS);
		    // ����ǰʮ������Ʊ����ȡ���ã�Ʊ��˳�վ����ʱ��ǰ48Сʱ���ϵİ�Ʊ��5%��
		    // 24Сʱ���ϡ�����48Сʱ�İ�Ʊ��10%�ƣ�����24Сʱ�İ�Ʊ��20%��
		    // �����ο���ʱ��������й�Ʊ
		    Date date = new Date();
		    Long nowTime = date.getTime(); // ��ȡ��ǰʱ��	    
			if(nowTime > startTimeOne) {
				throw new SystemException("�ó����Ѿ������������������Ʊ");
			}	
			String [] totlePrice = order.getTotlePrice();
			Integer money = 0;
			for(String price : totlePrice) {
				
				money += Integer.valueOf(price.split(":")[1]);			
			}
			if((nowTime + ConstantUtil.OutTime.FIFTEENDAY) > startTimeOne
					&& (nowTime + ConstantUtil.OutTime.FOUREIGHTHOUR) < startTimeOne) {
				// ��ʱ����ڷ���ʱ��15�죬С�ڷ���ʱ��48Сʱ
				money = (int) (money * 0.95);
			} else if((nowTime + ConstantUtil.OutTime.FOUREIGHTHOUR) >= startTimeOne
					&&(nowTime + ConstantUtil.OutTime.ONEDAY) < startTimeOne) {
				// ���ʱ����ڿ���ǰ48Сʱ��С��24Сʱ
				money = (int) (money * 0.90);
			} else if((nowTime + ConstantUtil.OutTime.ONEDAY) >= startTimeOne) {
				// ���ʱ��С�ڿ���ǰ24Сʱ
				money = (int) (money * 0.80);
			}
			// �˿����Ӧ���û�
			Integer countUser = userMapper.returnForMoney(money, order.getUserId());
			if(countUser < 1) {
				throw new SystemException("ϵͳ�����쳣�����Ժ�����");
			}
			Integer tdaId = order.getTrafficDateArrangeId();
			String [] explains = order.getExplain();
			for(String e : explains){
				String [] seats = e.split(",");
				Integer type = TrainSeat.seatMap.get(seats[0]);		
				// ���ν���ǰ����λ�����ݿ������
				Integer code = trainDao.returnTrainTicket(tdaId, seats[1], type);
				if(200 != code){
					throw new SystemException("ϵͳ�����쳣�����Ժ�����");
				}
			}		
			// ���˶�����״̬��Ϊ����Ʊ
			Integer count = orderMapper.returnTicket(id);
			if(count < 1) {
				throw new SystemException("ϵͳ�����쳣�����Ժ�����");
			}			
		} else {			
			// ����״̬�����������Ʊ
			throw new SystemException("��ǰ����״̬Ϊ:"+OrderUtil.getDisByName(order.getStatus())+"�����������Ʊ����");
		}
	}

	/**
	 * ��ǩ�и��¶����Ĳ���
	 * @param trafficDateId ��ͨ�������ڱ��
	 * @param totlePrice �ܼ۸�����
	 * @param explain ��λ��������
	 */
	@Transactional
	public void updateOrder(Integer trafficDateId, String totlePrice, String explain,Order order) {
		
		// ����Ҫ��֮ǰ������Ʊ���ȡ����
		Integer tdaId = order.getTrafficDateArrangeId();
		String [] explains = order.getExplain();
		for(String e : explains){
			String [] seats = e.split(",");
			Integer type = TrainSeat.seatMap.get(seats[0]);		
			// ���ν���ǰ����λ�����ݿ������
			Integer code = trainDao.returnTrainTicket(tdaId, seats[1], type);
			if(200 != code){
				throw new SystemException("ϵͳ�����쳣�����Ժ�����");
			}
		}
		
		Integer count = orderMapper.updateChangeTicketOrder(trafficDateId, totlePrice, explain, order.getId());
		if(count < 1) {
			throw new SystemException("ϵͳ�����쳣�����Ժ�����");
		}
	}
}




