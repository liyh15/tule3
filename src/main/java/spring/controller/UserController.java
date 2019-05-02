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
 * 用户方面的控制器类
 * @author 李元浩
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
	 * 转发到登录界面
	 * @param session
	 * @param method 要求登录或注册
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
	 * 转发到主界面
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
	 * 转发到火车购票主界面
	 * @return
	 */
	@RequestMapping("trainView.do")
	public String trainView()
	{
	   return "train_main";
	}
	
	/**
	 * 动态的获取验证码
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
	 * 用户修改密码
	 * @param old_pwd 旧密码
	 * @param new_pwd 新密码
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
		response.setMessage("修改密码成功");
		return response;
	}
	
    /**
     * 获取用户的头像
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
	 * 用户上传头像
	 * @param file
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/putHeadImage.do",method = RequestMethod.POST)
	@ResponseBody
	public ResultResponse<String> putHeadImage(@RequestParam CommonsMultipartFile file,HttpSession session){
		
		String fileName = file.getOriginalFilename();
		// 获取上传文件后缀
		String index = fileName.substring(fileName.lastIndexOf("."));
		String contentType = file.getContentType();
		ResultResponse<String> resultResponse = new ResultResponse<String>();
		// 规定只能上传jpg和png图片文件类型
		if(contentType.equals("image/jpeg") || contentType.equals("image/png")) {
			User user = (User) session.getAttribute("user");
			Integer userId = user.getId();
			String newFileName = userId+"headImage"+index;  //暂时默认
			
			File file2 = new File("D:\\tuleUserImage", newFileName);
			try {
				if(file2.exists()) {
					file2.delete();
				}
				file.transferTo(file2);
			} catch (Exception e) {
				e.printStackTrace();
                throw new SystemException("系统出现异常，请稍后重试");
			}
			// 获取文件的全路径
			String path = file2.getPath();
			Integer id = service.putHeadImage(userId, path);
			if(id == 0) {
				throw new SystemException("系统出现异常,请稍后重试");
			}
			resultResponse.setState(200);
			resultResponse.setMessage("头像上传成功");
			return resultResponse;
		} else {
			resultResponse.setState(500);
			resultResponse.setMessage("请上传jpg或者png类型的图片文件");
			return resultResponse;
		}
	}
	
	/**
	 * 检查用户的证件号码是否正确
	 * @param name 用户名称
	 * @param type 用户证件类型
	 * @param code 用户证件号码
	 * @return
	 */
	@RequestMapping(value = "/checkPersonalCode.do",method = RequestMethod.POST)
	@ResponseBody
	public ResultResponse<String> checkPersonalCode(String name,String type,String code) {
		
		service.checkPersonalCode(name, type, code);
		return new ResultResponse<String>();
	}
}
