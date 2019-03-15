package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import entity.User;
import service.UserService;

public class ChangePassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		
		String phone = user.getPhone();
		String oldPass = request.getParameter("old_pwd");
		
		
		String newPass = request.getParameter("new_pwd");
		
		UserService userService = new UserService();
		boolean flag = userService.changePass(phone, oldPass, newPass);
		
		if(flag) {			
			request.getRequestDispatcher("person.jsp").forward(request, response);
		} else {
			request.setAttribute("change_fail", "√‹¬Î ‰»Î¥ÌŒÛ");
		}
	}

}
