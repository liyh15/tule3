package servlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import entity.User;
import service.UserService;

/**
 * �û�ע���servlet
 * @author ��Ԫ��
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;     
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String view=request.getParameter("view");
		String vercode= request.getParameter("vercode").toUpperCase();			
		HttpSession session=request.getSession();
	    if(vercode.equals((String)session.getAttribute("code")))
	    {
	    	
	    	session.removeAttribute("code");    	
	    	UserService userService = new UserService();
			boolean flag = userService.register(username, password);		
			if(flag) {
				User user = userService.getUser(username);
				session.setAttribute("user", user); 
				response.sendRedirect("user/"+view+"View.do");
			} else {
				session.setAttribute("register_fail", "�û��Ѵ���");
				response.sendRedirect("user/login.do?method=register&view="+view);
			}
	    }
	    else{
	    	session.setAttribute("register_fail", "��֤�����");
			response.sendRedirect("user/login.do?method=register&view="+view);
	    }	
	}
}
