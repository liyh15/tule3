package spring.service;

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
}
