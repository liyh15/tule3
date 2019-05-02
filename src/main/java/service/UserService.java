package service;

import dao.CityDao;
import dao.UserDao;
import entity.User;
import spring.mapper.CitizenMapper;

public class UserService {
	
	private UserDao userDao;
	
	private CitizenMapper citizenMapper;
	
	public UserService() {
		userDao = new UserDao();
	}
	
	public int login(String username, String password) {
		return userDao.login(username, password);
	}
	
	public boolean register(String username, String password) {
		return userDao.register(username, password);
	}
	
	public boolean basicSetUp(User user) {
		return userDao.basicSetUp(user);
	}
	
	public User getUser(String username) {
		return userDao.getUser(username);
	}
	
	public boolean changePass(String phone, String oldPass,String newPass) {
		return userDao.changePass(phone, oldPass, newPass);
	}
	
	public int getCityIdByName(String cityName) {
		CityDao cityDao = new CityDao();
		return cityDao.getCityIdByName(cityName);
	}
}
