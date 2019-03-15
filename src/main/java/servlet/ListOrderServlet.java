package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.TrainDao;
import entity.Order;
import entity.User;
import service.OrderService;
public class ListOrderServlet extends HttpServlet {	
	private static final long serialVersionUID = 1L;       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("UTF-8");			
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user==null)
		{
		   response.sendRedirect("user/login.do?interupt=orderlist");
		}
		else
		{
		OrderService orderService = new OrderService();		
		List<Order> orderList = orderService.getOrderByUserId(user.getId());
		session.setAttribute("orderList", orderList);
		request.getRequestDispatcher("WEB-INF/person.jsp").forward(request, response);
		}
	}	
}
