package servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import entity.User;
import service.UserService;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    @Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String view=request.getParameter("view");
		//获取访问被拦截页面的路径
		String interupt=request.getParameter("interupt");
		HttpSession session = request.getSession();
		// 执行登录请求的时候吧注册有关的session给清除
		session.removeAttribute("register_fail");
		String verCode = request.getParameter("vercode").toUpperCase();
		if (verCode.equals((String) session.getAttribute("code"))) {
			/*
			 * 验证码校验成功
			 */
			session.removeAttribute("code");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			UserService userService = new UserService();
			int flag = userService.login(username, password);
			if (flag == 1) {
				/*
				 * 登录成功, 重定向到主页 session赋值user, 为后面做准备
				 */
				session.removeAttribute("login_fail_info");
				User user = userService.getUser(username);
				session.setAttribute("user", user);
				if(view==null || view=="")
				{
					/**
					 * view和interupt不会同时存在
					 */
					if(interupt=="")
					{
						response.sendRedirect("user/mainView.do");
					}
					else
					{
						response.sendRedirect(interupt);
					}				
				}
				else
				{
					response.sendRedirect("user/"+view+"View.do");
				}				
			} else if (flag == 2) {
				/*
				 * 登录失败 session赋值user, 为后面做准备
				 */
				session.setAttribute("login_fail_info", "账号不存在");				
				response.sendRedirect("user/login.do?view="+view+"&interupt="+interupt);
			} else if(flag==3) {
				{
					/*
					 * 密码不正确
					 */
					session.setAttribute("login_fail_info", "密码不正确");
					response.sendRedirect("user/login.do?view="+view+"&interupt="+interupt);
				}
			}
		} else {
			/*
			 * 验证码错误
			 */
			session.setAttribute("login_fail_info", "验证码错误");
			response.sendRedirect("user/login.do?view="+view+"&interupt="+interupt);
		}
	}

}
