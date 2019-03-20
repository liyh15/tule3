package spring.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import entity.User;

/**
 * 基于用户的拦截器
 * @author 李元浩
 *
 */
public class UserInterceptor implements HandlerInterceptor {

	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if(user != null){
			return true;
		}
		response.sendRedirect("login.do");
		return false;
	}
	
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		
	}
	
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception arg3)
			throws Exception {
		
		
	}
}
