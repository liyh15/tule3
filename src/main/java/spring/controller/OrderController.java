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
 * ������spring���������
 * @author ��Ԫ��
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
	  * ���ݶ�����Ų�ѯ��������
	  * @param id �������
	  * @param session
	  * @return
	  */
     public String orderDetailView(@RequestParam("id") int id,HttpSession session){	
		 session.removeAttribute("orderList");
		 Order order = orderService.getOrderByOrderId(id);	
		 // ��ȡ������ڰ��ű��
		 Integer tdai = order.getTrafficDateArrangeId();
		 String trafficType = order.getType();
		 if(OrderStaticDate.TRAIN_TYPE.equals(trafficType)){
			 // �����������Ϊ������
			 TrainDateArrange trainDateArrange = trainService.getTrainDateArrangeById(tdai);
			 TrainArrange trainArrange = trainService.getTrainArrangeById(trainDateArrange.getTrainArrangeId());
			 trainArrange.setDate(trainDateArrange.getDay());
			 trainArrange.setEndDate(trainDateArrange.getEndDay());
			 session.setAttribute("TrainArrange", trainArrange);
		 }
		 Integer [] passengerId = order.getPassengerId();
		 // ��ó˿���Ϣ
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
	  * ȡ����������
	  * @param id �������
	  * @return ���ز���״̬
	  */
	 @RequestMapping("/cancelOrder.do")
	 @ResponseBody
	 public ResultResponse<String> cancelOrderController(@RequestParam("id") Integer id){
		 ResultResponse<String> resultResponse = new ResultResponse<String>();
		 Integer state = orderService.cancelOrderById(id);
		 if(state == 0){
			 throw new SystemException("ȡ�����������쳣�����Ժ�����");
		 }
		 resultResponse.setMessage("������ȡ��");
		 resultResponse.setState(200);
		 return resultResponse;
	 }
	 
	 /**
	  * ɾ��������controller
	  * @param id
	  * @return
	  */
	 @RequestMapping("/deleteOrder.do")
	 @ResponseBody
	 public ResultResponse<String> deleteOrderController(@RequestParam("id") Integer id){
		 ResultResponse<String> resultResponse = new ResultResponse<String>();		 
		 Integer state = orderService.deleteOrderById(id);
		 if(state == 0){
			 throw new SystemException("ɾ�����������쳣�����Ժ�����");
		 }
		 resultResponse.setMessage("������ɾ��");
		 resultResponse.setState(200);
		 return resultResponse;
	 }
	 
	 /**
	  * ������Ʊ�ӿ�
	  * @param id �������
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
	  * ��ǩ����������ת������Ʊѡ�����
	  * @param id �������
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
		 String startArea = startStation.substring(0,startStation.lastIndexOf("վ"));
		 String endArea = endStation.substring(0,endStation.lastIndexOf("վ"));
		 List<TrainArrange> trainArranges=trainDao.findTrain(startArea, endArea, date);
		 session.setAttribute("trainArranges", trainArranges);
		 request.setAttribute("start", startArea);
		 request.setAttribute("end", endArea);
		 session.setAttribute("changeOrder", order);
		 return "train_change_detail";
	 }
	 
	 /**
	  * ��ȡ�����ķ�������(���ڸ�ǩ���ж��Ƿ�ѡ���˴��ڵ�ǰ�����ķ���ʱ��)
	  * @return
	 * @throws ParseException 
	  */
	 @RequestMapping("/getOrderDate.do")
	 @ResponseBody
	 public ResultResponse<String> getOrderDate(@RequestParam("id") Integer id) throws ParseException {
		 
		 Order order = orderService.getOrderByOrderId(id);
		 // �ж϶����Ƿ���ڻ��߶�����״̬�Ƿ�������и�ǩ(ֻ���ѳɽ��Ķ����ſ��Խ��и�ǩ)
		 if(null == order) {
			 throw new SystemException("��ǰ�����ѱ�ɾ��");
		 }
		 if(!OrderUtil.DEAL.equals(order.getStatus())) {
			 // ���������״̬��Ϊ�ѳɽ�״̬
			 throw new SystemException("��ǰ����״̬Ϊ:"+OrderUtil.getDisByName(order.getStatus())+"��������и�ǩ����");
		 }
		 // �Զ������з���ʱ���飬���Ƿ񳬳�����ʱ��
		 orderService.checkChangeTicket(order);
		 TrainDateArrange trainDateArrange = trainService.getTrainDateArrangeById(order.getTrafficDateArrangeId());
		 String date = trainDateArrange.getDay();
		 ResultResponse<String> response = new ResultResponse<String>();
		 response.setParam(date);
		 return response;
	 }
	 
	 /**
	  * ���л�Ʊ��ǩ�����Ľӿ�
	  * @param arrangeid �û�ѡ��Ļ𳵰��������±�
	  * @param seatId �û�ѡ�����λ�����±�
	  * @param session
	  * @return
	  */
	 @RequestMapping("/ticketChange.do")
	 @ResponseBody
	 public ResultResponse<String> ticketChange(@RequestParam("arrangeid") String arrangeid,
			 @RequestParam("seatid")String seatId,HttpSession session) {
		    // ��÷���Ҫ������л����ڰ��ż���
		    List<TrainArrange> trainArranges=(List<TrainArrange>) session.getAttribute("trainArranges");	
		    // ��ȡ�û�ѡ�еİ��Ŷ���
			TrainArrange tArrange=trainArranges.get(Integer.parseInt(arrangeid));
			// ��ȡ�û�ѡ�����λ���Ͷ���
			TrainSeat trainSeat=tArrange.getTrainSeats().get(Integer.parseInt(seatId));//������εĶ���
			// ��ȡ��ǰ��λ���͵ļ۸�	
			int price=trainSeat.getPrice();
			// ��ȡ��ǰ��Ҫ��ǩ�Ķ���
			Order order = (Order) session.getAttribute("changeOrder");
			// ��øö������еĳ˿ͱ��
			Integer [] passengers = order.getPassengerId();
			String totalPrice = "��Ʊ:"+price*passengers.length;
			// ��ȡ��λ
			StringBuilder builder = new StringBuilder();
			String [] seats = new String[passengers.length];
			for(int i = 0;i < passengers.length;i++) {
				String seat = trainDao.getSeatArrange(tArrange.getId(), trainSeat.seatMap.get(trainSeat.getSeatType()));
				if(null == seat) {
					for(String seatt : seats) {
						trainDao.returnTrainTicket(tArrange.getId(),seatt,trainSeat.seatMap.get(trainSeat.getSeatType()));
					}
					throw new SystemException("��ǰ����Ʊ�����㣬�������������");					
				}
				seats[i] = seat;
				String seatStr = trainSeat.getSeatType()+","+seat;
				builder.append("&"+seatStr);
			}
			// ��ö�������λ����
			String explain = builder.toString().substring(1);
			System.out.println(explain);
			// ���ж������µĲ���
			orderService.updateOrder(tArrange.getId(), totalPrice, explain, order);			
		    return new ResultResponse<String>();
	 }
	 
	 /**
	  * �ж϶����Ƿ��Ѿ�����
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
	  * ��Ӷ�ʱ������ʱ���¶�����״̬
	  * ��ʱ���ʺ�4.3.9�汾��spring
	  * ����Ҫ����spring-aop������
	  */
	 @Scheduled(cron = "0/1 * * * * ?")
	 public void updateOrderState(){
		 orderService.cancelOrder();
	 }
}
 