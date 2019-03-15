package spring.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import entity.MapperOrder;
import entity.Order;
/**
 * 订单的mabatis的mapper层
 * @author 李元浩
 */
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
}
