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
	 
	 @Autowired
	 private TrainMapper trainMapper;
	 
	 @Autowired
	 private UserMapper userMapper;
    /**
     * 通过订单编号获得订单
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
	 * 将MapperOrder类转化为Order类
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
	 * 转换订单乘客编号
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
		// 将此订单的状态置为已取消
		return orderMapper.cancelOrderById(id);
	}

	/**
	 * 定时检索超时订单
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
	 * 通过订单的编号删除订单
	 * @param id
	 * @return
	 */
	public Integer deleteOrderById(Integer id) {
		return orderMapper.deleteOrderById(id);
	}

	/**
	 * 为订单付款
	 * @param id 订单编号
	 */
	public void payForOrder(Integer id) {
		
	    Integer line = orderMapper.payForOrder(id, OrderUtil.DEAL);
	    if(line < 1) {
	    	throw new SystemException("订单付款失败，请重试");
	    }
	}
	
	/**
	 * 通过乘客获取所有的订单信息
	 * @param passengers 乘客信息集合
	 * @return
	 */
	public List<MapperOrder> getOrderByPassenger(List<Passenger> passengers) {
		
		List<MapperOrder> mapperOrders = orderMapper.getOrderByPassenger(passengers);
		return mapperOrders;
	}

	/**
	 * 通过订单编号进行退票操作,需要添加事务，因为有多个数据表操作，用户的资金字段和订单的状态字段
	 * @param id 订单编号
	 * @throws ParseException 
	 */
	@Transactional
	public void returnTicket(Integer id) throws ParseException {
		
		Order order = getOrderByOrderId(id);
		// 在退票之前首先需要判断当前订单的状态是否支持退票,支持退票的订单的状态只有已成交
		if(OrderUtil.DEAL.equals(order.getStatus())) {			
			// 当订单状态为已成交时，才可以进行退票操作
            // 在进行操作之前，需要行程日期安排时间是否支持退票操作
			System.out.println("行程日期安排编号为："+order.getTrafficDateArrangeId());
			TrainDateArrange trainDateArrangeOne = trainMapper.getTrainDateArrangeById(order.getTrafficDateArrangeId());
		    TrainArrange trainArrangeOne = trainMapper.getTrainArrangeById(trainDateArrangeOne.getTrainArrangeId());
		    // 火车开车时间
		    Long startTimeOne = DateUtil.getTimeByDate(trainDateArrangeOne.getDay()+" "+trainArrangeOne.getStartTime(), DateUtil.YYMMRRHHMMSS);
		    // 开车前十五天退票不收取费用，票面乘车站开车时间前48小时以上的按票价5%计
		    // 24小时以上、不足48小时的按票价10%计，不足24小时的按票价20%计
		    // 当车次开车时不允许进行购票
		    Date date = new Date();
		    Long nowTime = date.getTime(); // 获取当前时间	    
			if(nowTime > startTimeOne) {
				throw new SystemException("该车次已经发车，不允许进行退票");
			}	
			String [] totlePrice = order.getTotlePrice();
			Integer money = 0;
			for(String price : totlePrice) {
				
				money += Integer.valueOf(price.split(":")[1]);			
			}
			if((nowTime + ConstantUtil.OutTime.FIFTEENDAY) > startTimeOne
					&& (nowTime + ConstantUtil.OutTime.FOUREIGHTHOUR) < startTimeOne) {
				// 当时间大于发车时间15天，小于发车时间48小时
				money = (int) (money * 0.95);
			} else if((nowTime + ConstantUtil.OutTime.FOUREIGHTHOUR) >= startTimeOne
					&&(nowTime + ConstantUtil.OutTime.ONEDAY) < startTimeOne) {
				// 如果时间大于开车前48小时，小于24小时
				money = (int) (money * 0.90);
			} else if((nowTime + ConstantUtil.OutTime.ONEDAY) >= startTimeOne) {
				// 如果时间小于开车前24小时
				money = (int) (money * 0.80);
			}
			// 退款给响应的用户
			Integer countUser = userMapper.returnForMoney(money, order.getUserId());
			if(countUser < 1) {
				throw new SystemException("系统出现异常，请稍后重试");
			}
			Integer tdaId = order.getTrafficDateArrangeId();
			String [] explains = order.getExplain();
			for(String e : explains){
				String [] seats = e.split(",");
				Integer type = TrainSeat.seatMap.get(seats[0]);		
				// 依次将当前的座位从数据库中清除
				Integer code = trainDao.returnTrainTicket(tdaId, seats[1], type);
				if(200 != code){
					throw new SystemException("系统出现异常，请稍后重试");
				}
			}		
			// 将此订单的状态置为已退票
			Integer count = orderMapper.returnTicket(id);
			if(count < 1) {
				throw new SystemException("系统出现异常，请稍后重试");
			}			
		} else {			
			// 订单状态不允许进行退票
			throw new SystemException("当前订单状态为:"+OrderUtil.getDisByName(order.getStatus())+"不允许进行退票操作");
		}
	}

	/**
	 * 改签中更新订单的操作
	 * @param trafficDateId 交通安排日期编号
	 * @param totlePrice 总价格描述
	 * @param explain 座位安排描述
	 */
	@Transactional
	public void updateOrder(Integer trafficDateId, String totlePrice, String explain,Order order) {
		
		// 首先要将之前订单的票务给取消掉
		Integer tdaId = order.getTrafficDateArrangeId();
		String [] explains = order.getExplain();
		for(String e : explains){
			String [] seats = e.split(",");
			Integer type = TrainSeat.seatMap.get(seats[0]);		
			// 依次将当前的座位从数据库中清除
			Integer code = trainDao.returnTrainTicket(tdaId, seats[1], type);
			if(200 != code){
				throw new SystemException("系统出现异常，请稍后重试");
			}
		}
		
		Integer count = orderMapper.updateChangeTicketOrder(trafficDateId, totlePrice, explain, order.getId());
		if(count < 1) {
			throw new SystemException("系统出现异常，请稍后重试");
		}
	}
}




