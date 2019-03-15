package servlet;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import entity.TrainArrange;
import entity.TrainSeat;
/**
 * Servlet implementation class WriteOrderServlet
 */
public class WriteOrderServlet extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");	
		String index=request.getParameter("arrangeid");		//获得火车安排数组的下标
		String seatId=request.getParameter("seatid");       //获得座位的下标编号
		
		HttpSession session=request.getSession();
		List<TrainArrange> trainArranges=(List<TrainArrange>) session.getAttribute("trainArranges");
		
		TrainArrange tArrange=trainArranges.get(Integer.parseInt(index));
		TrainSeat trainSeat=tArrange.getTrainSeats().get(Integer.parseInt(seatId));//获得座椅的对象
				
		int price=tArrange.getTrainSeats().get(Integer.parseInt(seatId)).getPrice();
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yy-MM-dd");
		SimpleDateFormat s2=new SimpleDateFormat("MM月dd日");
		try {
			tArrange.setDate(s2.format(simpleDateFormat.parse(tArrange.getDate())));
		} catch (ParseException e) {
			e.printStackTrace();
		}	
		session.setAttribute("trainArrange", tArrange);
		session.setAttribute("trainSeat", trainSeat);

		request.getRequestDispatcher("train_choose.jsp").forward(request, response);		
	}
}
