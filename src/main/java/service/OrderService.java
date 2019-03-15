package service;

import java.util.List;

import dao.OrderDao;
import entity.Order;

public class OrderService {
	
	private OrderDao orderDao;
	
	public OrderService() {
		orderDao = new OrderDao();
	}
	
	public List<Order> getOrderByUserId(int userId) {
		return orderDao.getOrderByUserId(userId);
	}

}
