package spring.controller;
import java.awt.image.RenderedImage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Date;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.jws.soap.SOAPBinding.Use;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.mysql.jdbc.log.Log;

import dao.PassengerDao;
import entity.MapperOrder;
import entity.Order;
import entity.Passenger;
import entity.PassengerList;
import entity.TrainArrange;
import entity.TrainDateArrange;
import entity.User;
import service.OrderService;
import service.UserService;
import spring.service.IOrderService;
import spring.service.IPassengerService;
import spring.service.ITrainService;
import spring.service.IUserService;
import spring.service.ex.ServiceException;
import spring.service.ex.SystemException;
import utils.CodeUtil;
import utils.ConstantUtil;
import utils.DateUtil;
import utils.OrderUtil;

/**
 * �û�����Ŀ�������
 * @author ��Ԫ��
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {	
	
	@Autowired
	@Qualifier("userService")
	private IUserService service;
	
	@Autowired
	private PassengerDao passengerDao;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private IOrderService orderService;
	
	@Autowired
	private IPassengerService passengerService;
	
	@Autowired
	private ITrainService iTrainService;
	
	/**
	 * ת������¼����
	 * @param session
	 * @param method Ҫ���¼��ע��
	 * @return
	 */
	@RequestMapping("/login.do")
	public String loginView(HttpSession session,
			@RequestParam(value="method",required=false) String method,
			HttpServletRequest request)
	{
		request.setAttribute("method", method);
		return "login";
	}
	
	/**
	 * ת����������
	 * @return
	 */
	@RequestMapping("mainView.do")
	public String mainView(HttpSession session)
	{
		User user = userService.getUser("15720786592");
		session.setAttribute("user", user);
		return "main";
	}
	
	/**
	 * ת�����𳵹�Ʊ������
	 * @return
	 */
	@RequestMapping("trainView.do")
	public String trainView()
	{
	   return "train_main";
	}
	
	/**
	 * ��̬�Ļ�ȡ��֤��
	 * @param session
	 * @param response
	 */
	@RequestMapping("/codePic.do")
	public void codePic(HttpSession session,HttpServletResponse response)
	{	
    	OutputStream out = null;
    	Map<String, Object> map = CodeUtil.generateCodeAndPic();
    	session.setAttribute("code",map.get("code").toString().toUpperCase());
		try {
			out = response.getOutputStream();
			ImageIO.write((RenderedImage) map.get("codePic"), "jpeg", out);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * �û��޸�����
	 * @param old_pwd ������
	 * @param new_pwd ������
	 */
	@RequestMapping(value="/changePass.do",method=RequestMethod.POST)
	@ResponseBody
	public ResultResponse<Void> changeUserPassWord(@RequestParam("old_pwd") String oldPass,
			@RequestParam("new_pwd") String newPass,
			HttpSession session
			)
	{
		User user=(User) session.getAttribute("user");	
		service.changeUserPass(user.getPhone(), oldPass, newPass);
		ResultResponse<Void> response=new ResultResponse<Void>();
		response.setMessage("�޸�����ɹ�");
		return response;
	}
	
    /**
     * ��ȡ�û���ͷ��
     * @param session
     */
	@RequestMapping("/imageUrl.do")
	public void getUserHeadImage(HttpSession session,HttpServletResponse response){
		
		User user = (User) session.getAttribute("user");
		Integer id = user.getId();
		File file = service.getUserImageUrlByUserId(id);
		OutputStream outputStream = null;
		FileInputStream inputStream = null;
		try {
			outputStream = response.getOutputStream();
			inputStream = new FileInputStream(file);
			byte [] b = new byte[1024];
			int a = 0;
			while((a = inputStream.read(b))!= -1){
				outputStream.write(b, 0, a);
			}
		} catch (IOException e) {
		}finally {
			try {
				outputStream.close();
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}		
		}
	}
	
	/**
	 * �û��ϴ�ͷ��
	 * @param file
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/putHeadImage.do",method = RequestMethod.POST)
	@ResponseBody
	public ResultResponse<String> putHeadImage(@RequestParam CommonsMultipartFile file,HttpSession session){
		
		String fileName = file.getOriginalFilename();
		// ��ȡ�ϴ��ļ���׺
		String index = fileName.substring(fileName.lastIndexOf("."));
		String contentType = file.getContentType();
		ResultResponse<String> resultResponse = new ResultResponse<String>();
		// �涨ֻ���ϴ�jpg��pngͼƬ�ļ�����
		if(contentType.equals("image/jpeg") || contentType.equals("image/png")) {
			User user = (User) session.getAttribute("user");
			Integer userId = user.getId();
			String newFileName = userId+"headImage"+index;  //��ʱĬ��
			
			File file2 = new File("D:\\tuleUserImage", newFileName);
			try {
				if(file2.exists()) {
					file2.delete();
				}
				file.transferTo(file2);
			} catch (Exception e) {
				e.printStackTrace();
                throw new SystemException("ϵͳ�����쳣�����Ժ�����");
			}
			// ��ȡ�ļ���ȫ·��
			String path = file2.getPath();
			Integer id = service.putHeadImage(userId, path);
			if(id == 0) {
				throw new SystemException("ϵͳ�����쳣,���Ժ�����");
			}
			resultResponse.setState(200);
			resultResponse.setMessage("ͷ���ϴ��ɹ�");
			return resultResponse;
		} else {
			resultResponse.setState(500);
			resultResponse.setMessage("���ϴ�jpg����png���͵�ͼƬ�ļ�");
			return resultResponse;
		}
	}
	
	/**
	 * ����û���֤�������Ƿ���ȷ
	 * @param name �û�����
	 * @param type �û�֤������
	 * @param code �û�֤������
	 * @return
	 */
	@RequestMapping(value = "/checkPersonalCode.do",method = RequestMethod.POST)
	@ResponseBody
	public ResultResponse<String> checkPersonalCode(String name,String type,String code) {
		
		service.checkPersonalCode(name, type, code);
		return new ResultResponse<String>();
	}
	
	/**
	 * �жϵ�ǰ�˿��Ƿ���Թ���ǰ����(�г̵�ʱ��������С����Сʱ)
	 * @param trafficId �����ڰ��ű��
	 * @param type �˿�֤������
	 * @param code �˿�֤������
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/checkPersonalTwo.do",method = RequestMethod.POST)
	@ResponseBody
	public ResultResponse<String> checkPersonalTwo(Integer trafficId,String type,String code) throws ParseException {
		
		// �Ȳ��֤�������֤����Ӧ�ĳ˿���Ϣ
		List<Passenger> passengers = passengerService.getPassengerByCodeAndType(type, code);
		System.out.println("�˿͵�����:"+passengers.size());
		if(null != passengers && passengers.size() >= 1) {
			
			// ��ѯ�����û����еĶ���
		    List<MapperOrder> mapperOrders = orderService.getOrderByPassenger(passengers);
		    System.out.println("�ж���������:"+mapperOrders.size());
		    TrainDateArrange trainDateArrangeOne = iTrainService.getTrainDateArrangeById(trafficId);
		    TrainArrange trainArrangeOne = iTrainService.getTrainArrangeById(trainDateArrangeOne.getTrainArrangeId());
		    
		    Long startTimeOne = DateUtil.getTimeByDate(trainDateArrangeOne.getDay()+" "+trainArrangeOne.getStartTime(), DateUtil.YYMMRRHHMMSS);
		    Long endTimeOne = DateUtil.getTimeByDate(trainDateArrangeOne.getEndDay()+" "+trainArrangeOne.getEndTime(), DateUtil.YYMMRRHHMMSS);

		    for(MapperOrder mapperOrder : mapperOrders) {
		    	// ѭ��ȡ�����е����ڰ��ű��
		    	Integer trafficDateId = mapperOrder.getTrafficDateArrangeId();
		    	if(trafficId == trafficDateId) {
		    		throw new SystemException("�ó˿Ͳ����ظ�������ͬ���εĳ�Ʊ");
		    	}
		    	// ��ø��г̵����ڰ�����
		    	TrainDateArrange trainDateArrange = iTrainService.getTrainDateArrangeById(trafficDateId);
		    	// ��ø��г̵İ�����
		    	TrainArrange trainArrange = iTrainService.getTrainArrangeById(trainDateArrange.getTrainArrangeId());
		    	
		    	Long startTime = DateUtil.getTimeByDate(trainDateArrange.getDay()+" "+trainArrange.getStartTime(), DateUtil.YYMMRRHHMMSS);
			    Long endTime = DateUtil.getTimeByDate(trainDateArrange.getEndDay()+" "+trainArrange.getEndTime(), DateUtil.YYMMRRHHMMSS);
		    	if(startTimeOne < endTime+ConstantUtil.OutTime.TWOHOUR && endTimeOne > startTime-ConstantUtil.OutTime.TWOHOUR) {
		    		throw new SystemException("�ó˿���һ��ʱ�����������г̰��ţ�������Ʊ");
		    	}
		    }		    
		}
		return new ResultResponse<String>();
	}
	
	
	/**
	 * �û��������
	 * @param orderId ����id
	 * @return
	 */
	@RequestMapping(value = "/payForOrder.do",method = RequestMethod.POST)
	@ResponseBody
	@Transactional
	public ResultResponse<String> payForOrder(Integer orderId,HttpSession session) {
		
		ResultResponse<String> response = new ResultResponse<String>();
		Order order = orderService.getOrderByOrderId(orderId);
		if(null == order) {
			throw new SystemException("�����Ѿ�������");
		}
		if(!OrderUtil.NOPAY.equals(order.getStatus())) {
			throw new SystemException("��ǰ������������"+OrderUtil.getDisByName(order.getStatus())+"״̬�����ܽ��и���");
		}	
		String [] totlePrice = order.getTotlePrice();
		User us = (User) session.getAttribute("user");
		User user = userService.getUser(us.getPhone());
		Integer money = 0;
		for(String price : totlePrice) {
			
			money += Integer.valueOf(price.split(":")[1]);			
		}
		System.out.println(user.getMoney());
		if(user.getMoney() < money) {
			throw new SystemException("��ǰ�û����㣬���ֵ������");
		}
		// ���¶���״̬Ϊ�Ѹ�����޸��û����˻���������Ҫ�������� 
		orderService.payForOrder(orderId);
		service.reduceMoney(money, user.getId());
		return response;
	}
}
