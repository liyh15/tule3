package spring.controller;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
import entity.TrainSeat;
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
	 
	 @Autowired
	 private TrainDao trainDao;
	 
	 
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
	  * 订单退票接口
	  * @param id 订单编号
	  * @return
	 * @throws ParseException 
	  */
	 @RequestMapping("/returnTicket.do")
	 @ResponseBody
	 public ResultResponse<String> returnTicket(@RequestParam("id") Integer id) throws ParseException {
		 
		 orderService.returnTicket(id);
		 return new ResultResponse<String>();
	 }
	 
	 /**
	  * 改签操作，首先转发到购票选择界面
	  * @param id 订单编号
	  * @return
	  */
	 @RequestMapping("/changeTicket.do")
	 public String changeTicket(@RequestParam("id") Integer id,HttpServletRequest request
			 ,HttpSession session,@RequestParam("date") String date) {
		 
		 Order order = orderService.getOrderByOrderId(id);
		 TrainDateArrange trainDateArrange = trainService.getTrainDateArrangeById(order.getTrafficDateArrangeId());
		 TrainArrange trainArrange = trainService.getTrainArrangeById(trainDateArrange.getTrainArrangeId());
		 String startStation = trainArrange.getStartStation();
		 String endStation = trainArrange.getEndStation();
		 String startArea = startStation.substring(0,startStation.lastIndexOf("站"));
		 String endArea = endStation.substring(0,endStation.lastIndexOf("站"));
		 List<TrainArrange> trainArranges=trainDao.findTrain(startArea, endArea, date);
		 session.setAttribute("trainArranges", trainArranges);
		 request.setAttribute("start", startArea);
		 request.setAttribute("end", endArea);
		 session.setAttribute("changeOrder", order);
		 return "train_change_detail";
	 }
	 
	 /**
	  * 获取订单的发车日期(用于改签中判断是否选择了大于当前订单的发车时间)
	  * @return
	 * @throws ParseException 
	  */
	 @RequestMapping("/getOrderDate.do")
	 @ResponseBody
	 public ResultResponse<String> getOrderDate(@RequestParam("id") Integer id) throws ParseException {
		 
		 Order order = orderService.getOrderByOrderId(id);
		 // 判断订单是否存在或者订单的状态是否允许进行改签(只有已成交的订单才可以进行改签)
		 if(null == order) {
			 throw new SystemException("当前订单已被删除");
		 }
		 if(!OrderUtil.DEAL.equals(order.getStatus())) {
			 // 如果订单的状态不为已成交状态
			 throw new SystemException("当前订单状态为:"+OrderUtil.getDisByName(order.getStatus())+"不允许进行改签操作");
		 }
		 // 对订单进行发车时间检查，看是否超出发车时间
		 orderService.checkChangeTicket(order);
		 TrainDateArrange trainDateArrange = trainService.getTrainDateArrangeById(order.getTrafficDateArrangeId());
		 String date = trainDateArrange.getDay();
		 ResultResponse<String> response = new ResultResponse<String>();
		 response.setParam(date);
		 return response;
	 }
	 
	 /**
	  * 进行火车票改签操作的接口
	  * @param arrangeid 用户选择的火车安排日期下标
	  * @param seatId 用户选择的座位类型下标
	  * @param session
	  * @return
	  */
	 @RequestMapping("/ticketChange.do")
	 @ResponseBody
	 public ResultResponse<String> ticketChange(@RequestParam("arrangeid") String arrangeid,
			 @RequestParam("seatid")String seatId,HttpSession session) {
		    // 获得符合要求的所有火车日期安排集合
		    List<TrainArrange> trainArranges=(List<TrainArrange>) session.getAttribute("trainArranges");	
		    // 获取用户选中的安排对象
			TrainArrange tArrange=trainArranges.get(Integer.parseInt(arrangeid));
			// 获取用户选择的座位类型对象
			TrainSeat trainSeat=tArrange.getTrainSeats().get(Integer.parseInt(seatId));//获得座椅的对象
			// 获取当前座位类型的价格	
			int price=trainSeat.getPrice();
			// 获取当前需要改签的订单
			Order order = (Order) session.getAttribute("changeOrder");
			// 获得该订单所有的乘客编号
			Integer [] passengers = order.getPassengerId();
			String totalPrice = "车票:"+price*passengers.length;
			// 获取座位
			StringBuilder builder = new StringBuilder();
			String [] seats = new String[passengers.length];
			for(int i = 0;i < passengers.length;i++) {
				String seat = trainDao.getSeatArrange(tArrange.getId(), trainSeat.seatMap.get(trainSeat.getSeatType()));
				if(null == seat) {
					for(String seatt : seats) {
						trainDao.returnTrainTicket(tArrange.getId(),seatt,trainSeat.seatMap.get(trainSeat.getSeatType()));
					}
					throw new SystemException("当前车次票数不足，请更换其他车次");					
				}
				seats[i] = seat;
				String seatStr = trainSeat.getSeatType()+","+seat;
				builder.append("&"+seatStr);
			}
			// 获得订单的座位描述
			String explain = builder.toString().substring(1);
			System.out.println(explain);
			// 进行订单更新的操作
			orderService.updateOrder(tArrange.getId(), totalPrice, explain, order);			
		    return new ResultResponse<String>();
	 }
	 
	 /**
	  * 判断订单是否已经发车
	  * @param id
	  * @return
	 * @throws ParseException 
	  */
	 @RequestMapping("/checkOrderStartTime.do")
	 @ResponseBody
	 public ResultResponse<String> checkOrderStartTime(Integer id) throws ParseException {
		 
		 Order order = orderService.getOrderByOrderId(id);
		 orderService.checkChangeTicket(order);
		 return new ResultResponse<String>();		 
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
 