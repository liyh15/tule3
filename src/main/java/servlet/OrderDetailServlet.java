package servlet;

import java.io.IOException;
import java.sql.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eclipse.jdt.internal.compiler.ast.SuperReference;

import dao.OrderDao;
import dao.PassengerDao;
import dao.TrainDao;
import entity.Order;
import entity.Passenger;
import entity.TrainArrange;
import entity.TrainSeat;
import entity.TrainSeatParamter;
import entity.User;
import utils.OrderUtil;

/**
 * 生成火车订单的servlet
 * @author 李元浩
 */

public class OrderDetailServlet extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		PassengerDao dao = new PassengerDao();
		OrderDao orderDao = new OrderDao();
		TrainDao trainDao = new TrainDao();
		String[] userNames = request.getParameterValues("username");
		String[] bodyTypes = request.getParameterValues("bodytype");
		String[] numbers = request.getParameterValues("number");
		String phone = request.getParameter("phone");
		String seatType = request.getParameter("seattype");
		HttpSession session = request.getSession();
		TrainArrange trainArrange = (TrainArrange) session.getAttribute("trainArrange");
		User user = (User) session.getAttribute("user");
		if (user == null) // 如果用户未登录则提示登录
		{
			response.sendRedirect("user/login.do");
		} else {
			int userId = user.getId();
			int trafficDateArrangeId = trainArrange.getId(); // 交通日期安排编号
			String trainName = trainArrange.getTrainName();
			String[] price = getPrice(seatType, userNames.length, trainArrange);
			addPassenger(userNames, bodyTypes, numbers, user,request,response); // 添加乘客
			Integer[] passId = dao.getPassengerIdByPassNumber(userId, numbers); // 获得乘客编号
			String type = "火车票";
			String reservation = "电脑预定";
			// 获取火车座位(核心思想展示处)
			String[] explain = returnSeatArrange(userNames.length, trainDao, trafficDateArrangeId, seatType);
			Order order = new Order();
			order.setContactPhone(phone);
			order.setUserId(userId);
			order.setStatus(OrderUtil.NOPAY);
			order.setTrafficDateArrangeId(trafficDateArrangeId);
			order.setTotlePrice(price);
			order.setPassengerId(passId);
			order.setType(type);
			order.setExplain(explain);
			order.setReservation(reservation);
			// 设置订单超时时间
			Long time = 20L*60*1000;
			Date data = new Date(new Date().getTime()+time);
			order.setTimeClose(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(data));		
			// 把订单生成到用户的订单列表
			orderDao.addOrder(order);
			response.sendRedirect("orderlist");

		}

	}

	/**
	 * 返回火车的座位类型及位置
	 * @param length 购票数 
	 * @param dao 火车的数据库持久层
	 * @param trainDateId 火车安排-日期表编号
	 * @param type 座位类型
	 * @return
	 */
	private String[] returnSeatArrange(int length, TrainDao dao, int trainDateId, String type) {
		String[] explain = new String[length];
		for (int i = 0; i < length; i++) {
			String sa = dao.getSeatArrange(trainDateId, TrainSeatParamter.seatA.get(type));// 获取座位排列号
			explain[i] = type + "," + sa;
		}
		return explain;
	}

	/**
	 * 添加乘客的集合
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void addPassenger(String[] userNames, String[] bodyTypes, String[] numbers, User user,
			HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		PassengerDao dao = new PassengerDao();
		ArrayList<Passenger> passengers = new ArrayList<Passenger>();
		for (int i = 0; i < userNames.length; i++) {
			Passenger passenger = new Passenger();
			passenger.setName(userNames[i]);
			passenger.setPersonalId(numbers[i]);
			passenger.setType(bodyTypes[i]);

			passenger.setUserId(user.getId());
			passengers.add(passenger);
		}
		dao.addPassenger(passengers);
	}

	/**
	 * 获取价格明细的集合
	 * 
	 * @param seatType 座位类型
	 * @param length 人数
	 * @param trainArrange 火车安排实体类
	 * @return
	 */
	private String[] getPrice(String seatType, int length, TrainArrange trainArrange) {
		int money = 0;
		List<TrainSeat> trainSeats = trainArrange.getTrainSeats();
		for (TrainSeat trainSeat : trainSeats) {
			if (trainSeat.getSeatType().equals(seatType)) {
				money = trainSeat.getPrice();
			}
		}
		String[] strings = new String[1];
		money = money * length;
		strings[0] = "车票:" + money;
		return strings;
	}
}
