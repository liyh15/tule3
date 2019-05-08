package spring.controller;

import java.awt.image.RenderedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import entity.Manager;
import entity.StopOverSation;
import entity.StopOverStationRequest;
import entity.Train;
import entity.TrainStation;
import spring.service.IManagerService;
import spring.service.ITrainService;
import spring.service.ex.SystemException;
import utils.CodeUtil;
import utils.TrainUtil;

/**
 * 管理方面的控制类
 * @author 李元浩
 *
 */
@Controller
@RequestMapping("/manager")
public class ManagerController extends BaseController {
	
	@Autowired
	private IManagerService managerService;
	
	@Autowired
	private ITrainService trainService;
	
	/**
	 * 管理员登录控制器
	 * @param phone 账号
	 * @param password 密码
	 * @param code 验证码
	 * @return
	 */
	@RequestMapping("/login.do")
	@ResponseBody
    public ResultResponse<Void> managerLogin(@RequestParam("phone") String phone,
    		@RequestParam("password")String password,
    		@RequestParam("code") String code,
    		HttpSession session){
		ResultResponse<Void> response = new ResultResponse<Void>();
		String maCode = (String) session.getAttribute("maCode");
		if(!maCode.equals(code.toUpperCase())) {
			throw new SystemException("验证码错误");
		}
		Manager manager = managerService.getManagerById(phone);
		if(null == manager){
			throw new SystemException("此账号不存在");
		}
		String salt = manager.getSalt();
		if(managerService.confirmPassword(password, manager.getPassword(), salt)) {
			session.setAttribute("manager", manager);
			response.setState(200);
			return response;
		} else {
			throw new SystemException("密码不正确");
		}
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
    	session.setAttribute("maCode",map.get("code").toString().toUpperCase());
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
	
	@RequestMapping("/createManager.do")
	public void createManager() {
		managerService.createManager();
	}
	
	/**
	 * 转发到管理员登录界面
	 * @return
	 */
	@RequestMapping("/loginView.do")
	public String managerLoginView(){
		return "manager_login";
	}
	
	/**
	 * 转发到管理员的主界面
	 * @return
	 */
	@RequestMapping("mainView.do")
	public String managerMainView(){
		return "manager_main";
	}
	
	/**
	 * 转发到编辑列车界面
	 * @return
	 */
	@RequestMapping("editTrainView.do")
	public String editTrainView(HttpSession session) {
		
		List<Train> trains = trainService.getAllTrain();
		session.setAttribute("train", trains);
		return "edit_train";
	}

	/**
	 * 添加列车的接口
	 * @param name 列车名称
	 * @param type 列车类型
	 * @return
	 */
	@RequestMapping("/addTrain.do")
	@ResponseBody
	public ResultResponse<String> addTrain(String name,String type) {
		
		String typed = TrainUtil.getTypeByName(type);
		String trainName = typed+name;
		trainService.addTrain(trainName);
		return new ResultResponse<String>();
	}
	
	/**
	 * 列车编辑接口
	 * @return
	 */
	@RequestMapping("/editTrain.do")
	@ResponseBody
	public ResultResponse<String> editTrain(String name,Integer id) {
		
		trainService.editTrain(name, id);
		return new ResultResponse<String>();
	}
	
	/**
	 * 转发到列车行程编辑界面
	 * @return
	 */
	@RequestMapping("/editTrainTripView.do")
	public String editTrainTripView(HttpSession session) {
		
		List<TrainStation> trainStations = trainService.getTrainStation();
		Map<Integer, List<StopOverSation>> map = trainService.getTrainTrip();
		session.setAttribute("map", map);
		session.setAttribute("stations", trainStations);
		return "edit_train_trip";
	}
	
	/**
	 * 编辑行程
	 * @return
	 */
	@RequestMapping("/editTrainTrip.do")
	@ResponseBody
	public ResultResponse<String> editTrainTrip(@RequestBody List<StopOverStationRequest> stopOverSations) {
		
		trainService.updateTrainTrip(stopOverSations);
		return new ResultResponse<String>();
	}
}