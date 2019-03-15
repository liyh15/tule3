package spring.controller;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.deser.Deserializers.Base;

import dao.TrainDao;
import entity.Order;
import entity.Passenger;
import entity.TrainArrange;
import entity.TrainDateArrange;
import spring.service.IOrderService;
import spring.service.IPassengerService;
import spring.service.ITrainService;
import spring.service.ex.SystemException;
import staticdate.OrderStaticDate;
import utils.OrderUtil;
/**
 * 订单的spring处理控制器
 * @author 李元浩
 *
 */
@Controller
@RequestMapping("/order")
public class OrderController extends BaseController {
	
	 @Autowired
	 private IOrderService orderService;
	 @Autowired
	 private ITrainService trainService;
	 @Autowired
	 private IPassengerService passengetService;
	 
	 @RequestMapping(value = "/orderDetailView.do")	 
	 /**
	  * 根据订单编号查询订单详情
	  * @param id 订单编号
	  * @param session
	  * @return
	  */
     public String orderDetailView(@RequestParam("id") int id,HttpSession session){	
		 session.removeAttribute("orderList");
		 Order order = orderService.getOrderByOrderId(id);	
		 // 获取班次日期安排编号
		 Integer tdai = order.getTrafficDateArrangeId();
		 String trafficType = order.getType();
		 if(OrderStaticDate.TRAIN_TYPE.equals(trafficType)){
			 // 如果订单类型为火车类型
			 TrainDateArrange trainDateArrange = trainService.getTrainDateArrangeById(tdai);
			 TrainArrange trainArrange = trainService.getTrainArrangeById(trainDateArrange.getTrainArrangeId());
			 trainArrange.setDate(trainDateArrange.getDay());
			 trainArrange.setEndDate(trainDateArrange.getEndDay());
			 session.setAttribute("TrainArrange", trainArrange);
		 }
		 Integer [] passengerId = order.getPassengerId();
		 // 获得乘客信息
		 List<Passenger> passengers = passengetService.getPassengersById(passengerId);	
    	 session.setAttribute("passengers", passengers);
    	 order.setStatus(OrderUtil.getDisByName(order.getStatus()));
    	 order.setCreateTime(order.getCreateTime().substring(0,19));
    	 session.setAttribute("order", order); 
    	 try {
			Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(order.getTimeClose().substring(0,19));
			session.setAttribute("timeClose", date.getTime());			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		 return "train_order";
     }
	 
	 /**
	  * 取消订单操作
	  * @param id 订单编号
	  * @return 返回操作状态
	  */
	 @RequestMapping("/cancelOrder.do")
	 @ResponseBody
	 public ResultResponse<String> cancelOrderController(@RequestParam("id") Integer id){
		 ResultResponse<String> resultResponse = new ResultResponse<String>();
		 Integer state = orderService.cancelOrderById(id);
		 if(state == 0){
			 throw new SystemException("取消订单出现异常，请稍后重试");
		 }
		 resultResponse.setMessage("订单已取消");
		 resultResponse.setState(200);
		 return resultResponse;
	 }
	 
	 /**
	  * 删除订单的controller
	  * @param id
	  * @return
	  */
	 @RequestMapping("/deleteOrder.do")
	 @ResponseBody
	 public ResultResponse<String> deleteOrderController(@RequestParam("id") Integer id){
		 ResultResponse<String> resultResponse = new ResultResponse<String>();		 
		 Integer state = orderService.deleteOrderById(id);
		 if(state == 0){
			 throw new SystemException("删除订单出现异常，请稍后重试");
		 }
		 resultResponse.setMessage("订单已删除");
		 resultResponse.setState(200);
		 return resultResponse;
	 }
	 
	 /**
	  * 添加定时器，定时更新订单的状态
	  * 定时器适合4.3.9版本的spring
	  * 且需要加入spring-aop的依赖
	  */
	 @Scheduled(cron = "0/1 * * * * ?")
	 public void updateOrderState(){
		 orderService.cancelOrder();
	 }
}
 