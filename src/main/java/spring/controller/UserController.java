package spring.controller;
import java.awt.image.RenderedImage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import dao.PassengerDao;
import entity.Passenger;
import entity.PassengerList;
import entity.User;
import service.UserService;
import spring.service.IUserService;
import spring.service.ex.ServiceException;
import spring.service.ex.SystemException;
import utils.CodeUtil;

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
}
