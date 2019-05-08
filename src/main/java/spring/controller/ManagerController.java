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
 * ������Ŀ�����
 * @author ��Ԫ��
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
	 * ����Ա��¼������
	 * @param phone �˺�
	 * @param password ����
	 * @param code ��֤��
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
			throw new SystemException("��֤�����");
		}
		Manager manager = managerService.getManagerById(phone);
		if(null == manager){
			throw new SystemException("���˺Ų�����");
		}
		String salt = manager.getSalt();
		if(managerService.confirmPassword(password, manager.getPassword(), salt)) {
			session.setAttribute("manager", manager);
			response.setState(200);
			return response;
		} else {
			throw new SystemException("���벻��ȷ");
		}
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
	 * ת��������Ա��¼����
	 * @return
	 */
	@RequestMapping("/loginView.do")
	public String managerLoginView(){
		return "manager_login";
	}
	
	/**
	 * ת��������Ա��������
	 * @return
	 */
	@RequestMapping("mainView.do")
	public String managerMainView(){
		return "manager_main";
	}
	
	/**
	 * ת�����༭�г�����
	 * @return
	 */
	@RequestMapping("editTrainView.do")
	public String editTrainView(HttpSession session) {
		
		List<Train> trains = trainService.getAllTrain();
		session.setAttribute("train", trains);
		return "edit_train";
	}

	/**
	 * ����г��Ľӿ�
	 * @param name �г�����
	 * @param type �г�����
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
	 * �г��༭�ӿ�
	 * @return
	 */
	@RequestMapping("/editTrain.do")
	@ResponseBody
	public ResultResponse<String> editTrain(String name,Integer id) {
		
		trainService.editTrain(name, id);
		return new ResultResponse<String>();
	}
	
	/**
	 * ת�����г��г̱༭����
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
	 * �༭�г�
	 * @return
	 */
	@RequestMapping("/editTrainTrip.do")
	@ResponseBody
	public ResultResponse<String> editTrainTrip(@RequestBody List<StopOverStationRequest> stopOverSations) {
		
		trainService.updateTrainTrip(stopOverSations);
		return new ResultResponse<String>();
	}
}