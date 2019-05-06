package spring.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import entity.MapperOrder;
import entity.Order;
/**
 * 订单的mabatis的mapper层
 * @author 李元浩
 */
import entity.Passenger;
public interface OrderMapper {
	
	/**
	 * 通过订单编号查询指定订单
	 * @param id 订单编号
	 * @return
	 */
    public MapperOrder getOrderByOrderId(@Param("id") Integer id);
    
    /**
     * 通过编号取消订单
     * @param id 订单编号
     * @return
     */
    public Integer cancelOrderById(@Param("id") Integer id);
    
    /**
     * 定时检索超时订单
     * @return
     */
    public List<Integer> cancelOrder();
    
    /**
	 * 通过订单的编号删除订单
	 * @param id
	 * @return
	 */
	public Integer deleteOrderById(@Param("id") Integer id);
	
	/**
	 * 对订单进行付款
	 * @param id 订单编号
	 * @param status 订单状态
	 * @return
	 */
	public Integer payForOrder(@Param("id") Integer id,@Param("status") String status);
	
	/**
	 * 通过乘客所有所有的关联的订单
	 * @param passengers 乘客集合
	 * @return 订单集合
	 */
	public List<MapperOrder> getOrderByPassenger(@Param("passengers") List<Passenger> passengers);
	
	/**
	 * 将订单状态设置为退票状态
	 * @param id
	 * @return
	 */
	public Integer returnTicket(@Param("id") Integer id);
	
	/**
	 * 更新改签中的订单
	 * @param trafficDateId 交通日期安排编号
	 * @param totlePrice 总价格描述
	 * @param explain 座位类型描述
	 * @param id 订单编号
	 * @return
	 */
	public Integer updateChangeTicketOrder(@Param("trafficDateId")Integer trafficDateId, 
			@Param("totlePrice") String totlePrice, @Param("explain") String explain,@Param("id") Integer id);
}
