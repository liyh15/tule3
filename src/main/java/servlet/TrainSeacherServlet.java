package servlet;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import dao.TrainDao;
import entity.TrainArrange;
public class TrainSeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		//获取火车页面主页的搜索数据
		String startArea=request.getParameter("startArea");
		String endArea=request.getParameter("endArea");
		String date=request.getParameter("date");
		TrainDao trainDao=new TrainDao();
		List<TrainArrange> trainArranges=trainDao.findTrain(startArea, endArea, date);
		HttpSession session=request.getSession();
		String contextPath=request.getContextPath();
		session.removeAttribute("line");
		if(trainArranges.size()>0){
			session.setAttribute("trainArranges", trainArranges);
			request.setAttribute("start", startArea);
			request.setAttribute("end", endArea);
			request.getRequestDispatcher("WEB-INF/train_detail.jsp").forward(request, response);
		}else{
			if(StringUtils.isEmpty(startArea)) {
				session.setAttribute("line", "出发城市不能为空");
				response.sendRedirect("user/trainView.do");
				return;
			}
			if(StringUtils.isEmpty(endArea)) {
				session.setAttribute("line", "到达城市不能为空");
				response.sendRedirect("user/trainView.do");	
				return;
			}
			if(StringUtils.isEmpty(date)) {
				session.setAttribute("line", "出发时间不能为空");
				response.sendRedirect("user/trainView.do");	
				return;
			}
			session.setAttribute("line", "当前车次未开通");
			response.sendRedirect("user/trainView.do");			
		}		
	}
}
