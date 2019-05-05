package utils;

import org.omg.CORBA.PUBLIC_MEMBER;

/**
 * 关于订单的工具方法与常量
 * @author 李元浩
 *
 */
public class OrderUtil {
   
	public static String DELETE = "0";  // 订单删除
	
	public static String DEAL = "1"; // 订单成交
	
	public static String CANCEL = "2"; // 订单取消
	
	public static String BACK = "3"; // 订单退票
	
	public static String NOPAY = "4"; // 未付款
	
	public static String HAS_COMMENT = "1"; // 已经评论
	
	public static String NO_COMMENT = "2"; // 未评论
	
	/**
	 * 通过状态码获取状态描述
	 * @param name
	 * @return
	 */
	public static String getDisByName(String name){
	    if("0".equals(name)){
	    	return "已删除";
	    } else if("1".equals(name)){
	    	return "已成交";
	    } else if("2".equals(name)){
	    	return "已取消";
	    } else if("3".equals(name)){
	    	return "已退票";
	    } else if("4".equals(name)){
	    	return "未付款";
	    }
	    return null;
	}
	
	/**
	 * 通过评论状态获取订单评论描述
	 * @return
	 */
	public static String getCommentByName(String name) {
		 if("1".equals(name)){
		    	return "已评论";
		    } else if("2".equals(name)){
		    	return "未评论";
		    }
	   return null;
	}
}
