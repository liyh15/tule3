package spring.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.TrainDao;
import entity.MapperOrder;
import entity.Order;
import entity.TrainSeat;
import spring.mapper.OrderMapper;
import spring.service.IOrderService;
import spring.service.ex.SystemException;
/**
 * 订单服务层实现类
 * @author 李元浩
 *
 */
@Service
public class OrderServiceImpl implements IOrderService {

	 @Autowired
	 private OrderMapper orderMapper;
	 @Autowired
	 private TrainDao trainDao;
    /**
     * 通过订单编号获得订单
     */
	public Order getOrderByOrderId(Integer id) {
		MapperOrder mapperOrder = orderMapper.getOrderByOrderId(id);
		Order order = changeMapperOrderToOrder(mapperOrder);		
		return order;
	}
	
	/**
	 * 将MapperOrder类转化为Order类
	 * @return
	 */
	private Order changeMapperOrderToOrder(MapperOrder mapperOrder){
		Order order = new Order();
		order.setId(mapperOrder.getId());
		order.setUserId(mapperOrder.getUserId());
		order.setStatus(mapperOrder.getStatus());
		order.setTrafficDateArrangeId(mapperOrder.getTrafficDateArrangeId());
		order.setTotlePrice(mapperOrder.getTotlePrice().split(","));
		order.setPassengerId(changeStringToInteger(mapperOrder.getPassengerId().split("&")));
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
	 * 将String类型数组转化为Integer类型数组
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
	 * 通过编号取消订单
	 */
	public Integer cancelOrderById(Integer id) {
		MapperOrder order = orderMapper.getOrderByOrderId(id);
		Integer tdaId = order.getTrafficDateArrangeId();
		String explain = order.getOexplain();
		String [] explains = explain.split("&");
		for(String e : explains){
			String [] seats = e.split(",");
			Integer type = TrainSeat.seatMap.get(seats[0]);		
			// 依次将当前的座位从数据库中清除
			Integer code = trainDao.returnTrainTicket(tdaId, seats[1], type);
			if(200 != code){
				throw new SystemException("系统出现异常，请稍后重试");
			}
		}		
		// 将次订单的状态置为已取消
		return orderMapper.cancelOrderById(id);
	}

	/**
	 * 定时检索超时订单
	 */
	public void cancelOrder() {
		orderMapper.cancelOrder();
	}

	/**
	 * 通过订单的编号删除订单
	 * @param id
	 * @return
	 */
	public Integer deleteOrderById(Integer id) {
		return orderMapper.deleteOrderById(id);
	}
}




