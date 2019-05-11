package spring.controller;

import java.awt.image.RenderedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import entity.City;
import entity.EditTrainArrange;
import entity.EditTrainStaion;
import entity.Manager;
import entity.StopOverSation;
import entity.StopOverStationRequest;
import entity.TableTrainArrange;
import entity.Train;
import entity.TrainArrange;
import entity.TrainSeat;
import entity.TrainStation;
import net.sf.json.JSONArray;
import spring.service.IManagerService;
import spring.service.ITrainService;
import spring.service.ex.SystemException;
import utils.CodeUtil;
import utils.ConstantUtil;
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
	
	 @Autowired
	 @Qualifier("redisTemplate")
	 private RedisTemplate redisTemplate;
	
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
		
		if(StringUtils.isEmpty(name)) {
			throw new SystemException("列车名字不能为空");
		}
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
		
		if(StringUtils.isEmpty(name)) {
			throw new SystemException("列车名字不能为空");
		}
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
	
	/**
	 * 添加行程接口
	 * @param stopOverSations
	 * @return
	 */
	@RequestMapping("/addTrainTrip.do")
	@ResponseBody
	public ResultResponse<String> addTrainTrip(@RequestBody List<StopOverSation> stopOverSations) {
		
		trainService.addTrainTrip(stopOverSations);
		return new ResultResponse<String>();
	}
	
	/**
	 * 转发到编辑火车站的界面
	 * @return
	 */
	@RequestMapping("/editTrainStaionView.do")
	public String editTrainStaionView(HttpSession session) {
		
		List<City> cities=(List<City>) redisTemplate.opsForValue().get("tule_CITY");
		List<EditTrainStaion> editTrainStaions = trainService.getAllEditTrainStaions();
		session.setAttribute("stations", editTrainStaions);
		session.setAttribute("citys", cities);
		return "edit_train_station";
	}
	
	/**
	 * 进行火车的站的添加
	 * @param stationName
	 * @param cityName
	 * @return
	 */
	@RequestMapping("/editTrainStaion.do")
	@ResponseBody
	public ResultResponse<String> editTrainStaion(String stationName,String cityName) {
		
		if(StringUtils.isEmpty(stationName)) {
			throw new SystemException("火车站名称不能为空");
		}
		if(StringUtils.isEmpty(cityName)) {
			throw new SystemException("城市名称不能为空");
		}
		List<TrainStation> trainStations = trainService.getTrainStation();
		for(TrainStation trainStation : trainStations) {
			if(trainStation.getName().equals(stationName+"站")) {
				throw new SystemException("此火车站已经存在");
			}
		}
		List<City> cities=(List<City>) redisTemplate.opsForValue().get("tule_CITY");
		Integer cityId = null;
		for(City city : cities) {
			if(city.getName().equals(cityName)) {
				cityId = city.getId();
			}
		}
		trainService.addTrainStaion(stationName+"站", cityId);
		return new ResultResponse<String>();
	}
	
	/**
	 * 转发到编辑火车安排界面
	 * @return
	 */
	@RequestMapping("/editTrainArrangeView.do")
	public String editTrainArrange(HttpSession session){
		
		List<EditTrainArrange> editTrainArranges = trainService.getAllTrainArrange();
		List<TableTrainArrange> tableTrainArranges = new ArrayList<TableTrainArrange>();
		Map<String, Map<Integer,List<EditTrainArrange>>> map = new HashMap<String, Map<Integer,List<EditTrainArrange>>>();
		for(EditTrainArrange editTrainArrange : editTrainArranges) {
			
			String trainName = editTrainArrange.getTrainName();
			Integer tripId = editTrainArrange.getTripId();
			if(map.containsKey(trainName)) {
				Map<Integer,List<EditTrainArrange>> map2 = map.get(trainName);
				if(map2.containsKey(tripId)) {
					map2.get(tripId).add(editTrainArrange);
				} else {
					ArrayList<EditTrainArrange> editTrainArranges2 = new ArrayList<EditTrainArrange>();
					editTrainArranges2.add(editTrainArrange);
					map2.put(tripId,editTrainArranges2);
				}
			} else {
				
				Map<Integer,List<EditTrainArrange>> map2 = new HashMap<Integer, List<EditTrainArrange>>();
				ArrayList<EditTrainArrange> editTrainArranges2 = new ArrayList<EditTrainArrange>();
				editTrainArranges2.add(editTrainArrange);
				map2.put(tripId, editTrainArranges2);
				map.put(trainName, map2);
			}
		}
		List<Train> trains = trainService.getAllTrain();
		Map<Integer, List<StopOverSation>> tripMaps = trainService.getTrainTrip();
		
		session.setAttribute("arranges", map);
		session.setAttribute("trains",trains);
		session.setAttribute("tripMaps",tripMaps);
		return "edit_train_arrange";
	}
	
	/**
	 * 添加火车安排
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping("/addTrainArrange.do")
	@ResponseBody
	public ResultResponse<String> addTrainArrange(String trainName,String trip) throws ParseException {
		
		if(StringUtils.isEmpty(trip)) {
			throw new SystemException("请选择行程");
		}
		// 首先判断此列车是否已经安排了此行程
		List<EditTrainArrange> trainArranges = trainService.getAllTrainArrange();
		
		for(EditTrainArrange editTrainArrange : trainArranges) {	
			
			if(editTrainArrange.getTrainName().equals(trainName) && editTrainArrange.getTripId() == Integer.valueOf(trip)) {
				throw new SystemException("该列车已经已经安排了此行程");
			}
		}
		Map<Integer, List<StopOverSation>> tMap = trainService.getTrainTrip();
		// 获得当前行程的停靠信息
		List<StopOverSation> sations = tMap.get(Integer.valueOf(trip));
		// 获得所有车站的信息
		List<TrainStation> trainStations = trainService.getTrainStation();
		// 获得所有的火车的信息
		List<Train> trains = trainService.getAllTrain();
		Integer trainId = null;
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		for(Train train : trains) {
			if(train.getName().equals(trainName)) {
				trainId = train.getId();
			}
		}	
		for(int i = 0;i < sations.size();i++) {
			if(i != sations.size()-1) {
				for(int j = i+1;j < sations.size();j++) {
					// 分别获得一次安排的起始站和终点站
					String startStation = sations.get(i).getStation();
					String endStation = sations.get(j).getStation();
					String startTime = sations.get(i).getStartTime();
					String endTime = sations.get(j).getArriveTime();
					Long reduce = format.parse(endTime).getTime()-format.parse(startTime).getTime();
					
					Double totleTime = Double.valueOf(reduce)/1000/60/60;
					BigDecimal bg = new BigDecimal(totleTime).setScale(1, RoundingMode.UP);
					Integer startId = null;
					Integer endId = null;
					for(TrainStation trainStation : trainStations) {
						if(trainStation.getName().equals(startStation)) {
							startId = trainStation.getId();
						}
						if(trainStation.getName().equals(endStation)) {
							endId = trainStation.getId();
						}
					}
					trainService.addTrainArrange(startId, endId, trainId, Integer.valueOf(trip), startTime, endTime, bg.doubleValue()+"小时");
				}
			}			
		}
		return new ResultResponse<String>();
	}
	
	/**
	 * 转发到编辑火车日期安排界面
	 * @return
	 */
	@RequestMapping("/editTrainDateArrangeView.do")
	public String editTrainDateArrangeView(HttpSession session) {
		
		List<Train> trains = trainService.getAllTrain();
		session.setAttribute("trains", trains);
		return "edit_train_date_arrange";
	}
	
	/**
	 * 通过列车名称查询此列车所有的火车日期安排
	 * @param trainName
	 * @return
	 */
	@RequestMapping("/queryTrainDateArrangeByName.do")
	@ResponseBody
	public ResultResponse<Map<Integer, Map<Integer, List<TrainArrange>>>> queryTrainDateArrangeByName(String trainName) {
		
		List<Train> trains = trainService.getAllTrain();
		Integer trainId = null;
		for(Train train : trains) {
			if(train.getName().equals(trainName)) {
				trainId = train.getId();
			}
		}
		List<TrainArrange> tArranges = trainService.queryTrainDateArrangeByTrainName(trainId);
		
		Map<Integer, Map<Integer, List<TrainArrange>>> map = new HashMap<Integer, Map<Integer,List<TrainArrange>>>();
		
		for(TrainArrange trainArrange : tArranges) {
			
			JSONArray jsonArray = JSONArray.fromObject(trainArrange.getTrainSeat());
			ArrayList<TrainSeat> trainSeats = (ArrayList<TrainSeat>) jsonArray.toList(jsonArray, TrainSeat.class);
			trainArrange.setTrainSeats(trainSeats);
			if(map.containsKey(trainArrange.getTripId())) {
				
				Map<Integer, List<TrainArrange>> map2 = map.get(trainArrange.getTripId());
				if(map2.containsKey(trainArrange.getGroupId())) {
					map2.get(trainArrange.getGroupId()).add(trainArrange);
				} else {
					List<TrainArrange> list = new ArrayList<TrainArrange>();
					list.add(trainArrange);
					map2.put(trainArrange.getGroupId(), list);
				}				
			} else {
				// 如果集合map不包含这个行程编号
				Map<Integer, List<TrainArrange>> map2 = new HashMap<Integer, List<TrainArrange>>();
				List<TrainArrange> list = new ArrayList<TrainArrange>();
				list.add(trainArrange);
				map2.put(trainArrange.getGroupId(), list);
				map.put(trainArrange.getTripId(), map2);
			}
		}			
		ResultResponse<Map<Integer, Map<Integer, List<TrainArrange>>>> response = new ResultResponse<Map<Integer,Map<Integer,List<TrainArrange>>>>();
		response.setParam(map);
		return response;
	}
	
	/**
	 * 查询指定火车的所有安排
	 * @return
	 */
	@RequestMapping("/queryTrainArrangeByTrain.do")
	@ResponseBody
	public ResultResponse<Map<Integer, List<EditTrainArrange>>> queryTrainArrangeByTrain(String trainName) {
		
		// 查询出所有的该车次安排
	    List<EditTrainArrange> trainArranges = trainService.getAllTrainArrange();
	    Map<Integer, List<EditTrainArrange>> map = new HashMap<Integer, List<EditTrainArrange>>();
	    for(EditTrainArrange editTrainArrange : trainArranges) {
	    	
	    	if(editTrainArrange.getTrainName().equals(trainName)) {
	    		if(map.containsKey(editTrainArrange.getTripId())) {
	    			map.get(editTrainArrange.getTripId()).add(editTrainArrange);
	    		} else {
	    			ArrayList<EditTrainArrange> list = new ArrayList<EditTrainArrange>();
	    			list.add(editTrainArrange);
	    			map.put(editTrainArrange.getTripId(),list);
	    		}
	    	}
	    }
	    ResultResponse<Map<Integer, List<EditTrainArrange>>> response = new ResultResponse<Map<Integer,List<EditTrainArrange>>>();
	    response.setParam(map);
		return response;
	}
	
	/**
	 * 添加火车日期安排
	 * @param tripId 行程编号
	 * @param trainName 火车名称
	 * @param date 日期
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/addTrainDateArrange.do")
	@ResponseBody
	public ResultResponse<String> addTrainDateArrange(Integer tripId,String trainName,String date) throws ParseException {
		
		if(null == tripId || "".equals(tripId)) {
			throw new SystemException("没有指定行程编号");
		}
		if(null == date || "".equals(date)) {
			throw new SystemException("没有选择时间");
		}
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		if((new Date().getTime() + ConstantUtil.OutTime.ONEDAY) > simpleDateFormat.parse(date).getTime()) {
			throw new SystemException("选择的日期小于当前的日期后一天");
		}
		
		Integer groupId = (Integer) redisTemplate.opsForValue().get("dateGroupId");
		if(null == groupId) {
			groupId = 3;
			redisTemplate.opsForValue().set("dateGroupId", 3);
		} else {
			redisTemplate.opsForValue().set("dateGroupId", groupId+1);
		}
		// 查询出所有的该车次安排
	    List<EditTrainArrange> trainArranges = trainService.getAllTrainArrange();
	    List<EditTrainArrange> tArranges = new ArrayList<EditTrainArrange>();
	    for(EditTrainArrange editTrainArrange : trainArranges) {
	    	if(editTrainArrange.getTrainName().equals(trainName) && editTrainArrange.getTripId() == tripId) {
	    		tArranges.add(editTrainArrange);
	    	}
	    }
	    
	    List<Train> trains = trainService.getAllTrain();
		Integer trainId = null;
		for(Train train : trains) {
			if(train.getName().equals(trainName)) {
				trainId = train.getId();
			}
		}
		List<TrainArrange> trainArranges2 = trainService.queryTrainDateArrangeByTrainName(trainId);
        for(TrainArrange trainArrange : trainArranges2) {
        	
        	for(EditTrainArrange editTrainArrange : tArranges) {
        		if(editTrainArrange.getId() == trainArrange.getArrangeId() && date.equals(trainArrange.getDate())) {
        			throw new SystemException("该列车已经安排了本次行程");
        		}
        	}
        }
	    
	    for(EditTrainArrange editTrainArrange : tArranges) {
	    	String startDay = date;
	    	String endDay = date;
	    	Integer arrangeId = editTrainArrange.getId();
	    	String trainSeat = null;
	    	if(trainName.startsWith("G") || trainName.startsWith("D")) {
	    		TrainSeat seat1=new TrainSeat();
	    		TrainSeat seat2=new TrainSeat();
	    		TrainSeat seat3=new TrainSeat();
	    		seat1.setSeatType("商务座");
	    		seat2.setSeatType("一等座");
	    		seat3.setSeatType("二等座");
	    		seat1.setPrice(300);
	    		seat2.setPrice(200);
	    		seat3.setPrice(100);
	    		List<TrainSeat> trainSeats=new ArrayList<TrainSeat>();
	    		trainSeats.add(seat1);
	    		trainSeats.add(seat2);
	    		trainSeats.add(seat3);
	    		JSONArray jsonArray=JSONArray.fromObject(trainSeats);
	    		trainSeat = jsonArray.toString();
	    	} else {
	    		TrainSeat seat1=new TrainSeat();
	    		TrainSeat seat2=new TrainSeat();
	    		TrainSeat seat3=new TrainSeat();
	    		TrainSeat seat4=new TrainSeat();
	    		seat1.setSeatType("硬座");
	    		seat2.setSeatType("硬卧");
	    		seat3.setSeatType("软卧");
	    		seat4.setSeatType("无座");
	    		seat1.setPrice(300);
	    		seat2.setPrice(200);
	    		seat3.setPrice(100);
	    		seat4.setPrice(50);
	    		List<TrainSeat> trainSeats=new ArrayList<TrainSeat>();
	    		trainSeats.add(seat1);
	    		trainSeats.add(seat2);
	    		trainSeats.add(seat3);
	    		trainSeats.add(seat4);
	    		JSONArray jsonArray=JSONArray.fromObject(trainSeats);
	    		trainSeat = jsonArray.toString();
	    	}
	    	trainService.addTrainDateArrange(startDay, arrangeId, trainSeat, endDay,groupId);
	    }
		return new ResultResponse<String>();
	}
}





