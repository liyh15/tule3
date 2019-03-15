package spring.controller;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.io.OutputStream;
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

import entity.User;
import spring.service.IUserService;
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
	public String mainView()
	{
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
    	OutputStream out;
    	Map<String, Object> map = CodeUtil.generateCodeAndPic();
    	session.setAttribute("code",map.get("code").toString().toUpperCase());
		try {
			out = response.getOutputStream();
			ImageIO.write((RenderedImage) map.get("codePic"), "jpeg", out);
		} catch (IOException e) {
			e.printStackTrace();
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
}
