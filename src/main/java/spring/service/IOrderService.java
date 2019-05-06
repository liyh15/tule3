package spring.service;

import java.text.ParseException;
import java.util.List;

import entity.MapperOrder;
import entity.Order;
import entity.Passenger;

/**
 * 订单服务层接口
 * @author 李元浩
 *
 */
public interface IOrderService {
	
    /**
     * 通过编号获取订单
     * @param id
     * @return
     */
	public Order getOrderByOrderId(Integer id);
	
	/**
	 * 通过编号取消订单
	 * @param id
	 * @return
	 */
	public Integer cancelOrderById(Integer id);
	
	/**
	 * 定时检索超时订单
	 */
	public void cancelOrder();
	
	/**
	 * 通过订单的编号删除订单
	 * @param id
	 * @return
	 */
	public Integer deleteOrderById(Integer id);
	
	/**
	 * 为订单付款
	 * @param id 订单编号
	 */
	public void payForOrder(Integer id);
	
	/**
	 * 通过乘客获取所有的订单信息
	 * @param passengers 乘客信息集合
	 * @return
	 */
	public List<MapperOrder> getOrderByPassenger(List<Passenger> passengers);
	
	/**
	 * 通过订单编号进行退票操作
	 * @param id 订单编号
	 */
	public void returnTicket(Integer id) throws ParseException;
	
	/**
	 * 改签中更新订单的操作
	 * @param trafficDateId 交通安排日期编号
	 * @param totlePrice 总价格描述
	 * @param explain 座位安排描述
	 * @param order 订单实体
	 */
	public void updateOrder(Integer trafficDateId,String totlePrice,String explain,Order order);
}
