package spring.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import entity.EditTrainArrange;
import entity.EditTrainStaion;
import entity.StopOverSation;
import entity.StopOverStationRequest;
import entity.Train;
import entity.TrainArrange;
import entity.TrainDateArrange;
import entity.TrainStation;
import entity.TrainTrip;
import net.sf.json.JSONArray;
import spring.mapper.TrainMapper;
import spring.service.ITrainService;
import spring.service.ex.SystemException;
@Service
public class TrainServiceImpl implements ITrainService {
	
	private final static String NEVERSET = "未设置";
	
	private final static String MINUTE = "分钟";
	
	private final static String BASETIME = "zz:zz:zz";
	
	@Autowired
	@Qualifier("redisTemplate")
	private RedisTemplate redisTemplate;
	
	@Autowired
	private TrainMapper trainMapper;
	/**
	 * 火车类型的服务
	 * @author 李元浩
	 */
	public TrainArrange getTrainArrangeById(Integer id) {		
		return trainMapper.getTrainArrangeById(id);
	}
	
	/**
	 * 通过编号获得火车日期安排对象
	 * @param id
	 * @return
	 */
	public TrainDateArrange getTrainDateArrangeById(Integer id) {
		return trainMapper.getTrainDateArrangeById(id);
	}

	/**
	 * 获取所有的列车
	 * @return
	 */
	public List<Train> getAllTrain() {
	
		return trainMapper.getAllTrain();
	}

	/**
	 * 添加列车
	 * @param trainName
	 */
	public void addTrain(String trainName) {
		// TODO Auto-generated method stub
		List<Train> trains = trainMapper.getAllTrain();
		for(Train train : trains) {			
			if(train.getName().equals(trainName)) {
				throw new SystemException("该列车已经存在");
			}
		}
		Integer line = trainMapper.addTrain(trainName);
		if(line < 1) {
			throw new SystemException("添加列车失败");
		}
	}

	/**
	 * 编辑列车
	 * @param name 列车名称
	 * @param id 列车标号
	 */
	public void editTrain(String name, Integer id) {
		
		Train train = trainMapper.getTrainById(id);
		String trainName = train.getName();
		String newName = trainName.substring(0,1)+name;
		Integer line = trainMapper.updateTrainById(newName, id);
		if(line < 1) {
			throw new SystemException("编辑列车失败");
		}
	}

	/**
	 * 获得所有的列车的行程
	 * @return
	 */
	public Map<Integer, List<StopOverSation>> getTrainTrip() {
		
		Map<Integer, List<StopOverSation>> map = new HashMap<Integer, List<StopOverSation>>();
		List<TrainTrip> trainTrips = trainMapper.getAllTrainTrip();
		for(TrainTrip trainTrip : trainTrips){
			
			JSONArray jsonArray = JSONArray.fromObject(trainTrip.getTrip());
			ArrayList<StopOverSation> sation = (ArrayList<StopOverSation>) jsonArray.toList(jsonArray,
					StopOverSation.class);
			for(StopOverSation stopOverSation : sation) {
				stopOverSation.setStopTime(stopOverSation.getStopTime().substring(0,stopOverSation.getStopTime().indexOf("分钟")));
			}
			map.put(trainTrip.getId(), sation);
		}		
		return map;
	}


	/**
	 * 获得所有的火车站
	 * @return
	 */
	public List<TrainStation> getTrainStation() {
		
		List<TrainStation> trainStations = trainMapper.getAllTrainStation();
		return trainStations;
	}

	/**
	 * 更新车次行程
	 * @param requests
	 */
	public void updateTrainTrip(List<StopOverStationRequest> requests) {
		
		// 获得本次行程的编号
		Integer id = requests.get(0).getId();
		TrainTrip trainTrip = trainMapper.getTrainTripById(id);
		JSONArray jsonArray = JSONArray.fromObject(trainTrip.getTrip());
		ArrayList<StopOverSation> sation = (ArrayList<StopOverSation>) jsonArray.toList(jsonArray,
				StopOverSation.class);
		// 建立集合存储新的行程信息
		ArrayList<StopOverSation> sations = new ArrayList<StopOverSation>();
		for(int i = 0;i < requests.size(); i++) {
			
			StopOverSation stopStation = new StopOverSation();			
			// 遍历前端传过来的集合，查看修改过的值
			String station = requests.get(i).getStation();
			String startTime = requests.get(i).getStartTime();
			String arriveTime = requests.get(i).getArriveTime();
			String stopTime = requests.get(i).getStopTime();
			StringBuilder startBuilder = new StringBuilder();
			StringBuilder arriveBuilder = new StringBuilder();
			StopOverSation stopStation2 = sation.get(i);
			if(NEVERSET.equals(station)) {				
				stopStation.setStation(stopStation2.getStation());
			} else {
				stopStation.setStation(station);
			}
			String newStartTime = null;
			String newArriveTime = null;
			// 遍历开始时间，查看那个时间变换过了
			char [] startChar = startTime.toCharArray();
			char [] startCharZ = stopStation2.getStartTime().toCharArray();
			for(int j = 0; j < startChar.length ; j++) {				
				if(startChar[j] == 'z') {
					startBuilder.append(startCharZ[j]);
				} else {
					startBuilder.append(startChar[j]);
				}
			}
			
			if(startBuilder.toString().contains("z") && !startBuilder.toString().equals(BASETIME)) {
				newStartTime = BASETIME;
			} else {
				newStartTime = startBuilder.toString();
			}
					
			char [] arriveChar = arriveTime.toCharArray();
			char [] arriveCharZ = stopStation2.getArriveTime().toCharArray();
			for(int j = 0; j < arriveChar.length ; j++) {				
				if(arriveChar[j] == 'z') {
					arriveBuilder.append(arriveCharZ[j]);
				} else {
					arriveBuilder.append(arriveChar[j]);
				}
			}
			
			if(arriveBuilder.toString().contains("z") && !arriveBuilder.toString().equals(BASETIME)) {
				newArriveTime = BASETIME;
			} else {
				newArriveTime = arriveBuilder.toString();
			}
			
			stopStation.setArriveTime(newArriveTime);
			stopStation.setStartTime(newStartTime);
			stopStation.setStopTime(requests.get(i).getStopTime() + MINUTE);
			sations.add(stopStation);
		}		
		String newJson = JSONArray.fromObject(sations).toString();
		Integer line = trainMapper.updateTrainTripById(id, newJson);
		if(line < 1) {
			throw new SystemException("更新火车行程出现异常，请重试");
		}
	}

	/**
	 * 添加车次行程
	 * @param stopOverSations
	 */
	public void addTrainTrip(List<StopOverSation> stopOverSations) {
		
		if(stopOverSations.size() <= 1) {
			
			throw new SystemException("行程中的车站不能小于两个");
		}
		
		for(StopOverSation sation : stopOverSations) {
			
			if(sation.getStation().equals(NEVERSET)) {
				
				throw new SystemException("请设置车站名称");
			}
			
			if(sation.getArriveTime().contains("z") && !sation.getArriveTime().equals(BASETIME)) {
				throw new SystemException("请输入"+sation.getStation()+"正确的到达时间");
			}
			
			if(sation.getStartTime().contains("z") && !sation.getStartTime().equals(BASETIME)) {
				throw new SystemException("请输入"+sation.getStation()+"正确的开车时间");
			}
			
			if(StringUtils.isEmpty(sation.getStopTime())) {
				throw new SystemException("车站的停靠时间不能为空");
			}
			sation.setStopTime(sation.getStopTime()+"分钟");
		}
		
		String newJson = JSONArray.fromObject(stopOverSations).toString();
		Integer line = trainMapper.addTrainTrip(newJson);
		if(line < 1) {
			throw new SystemException("添加火车行程出现异常，请重试");
		}
	}

	/**
	 * 获得所有的火车站
	 * @return
	 */
	public List<EditTrainStaion> getAllEditTrainStaions() {
		
		List<EditTrainStaion> editTrainStaions = trainMapper.getEditTrainStaions();
		return editTrainStaions;
	}

	/**
	 * 添加火车站
	 * @param stationName 火车站名称
	 * @param cityName 城市名称
	 */
	public void addTrainStaion(String stationName, Integer cityId) {
		
		Integer line = trainMapper.addTrainStaion(stationName, cityId);
		if(line < 1) {
			throw new SystemException("添加火车站出现异常，请重试");
		}
	}

	/**
	 * 获得所有的火车安排
	 * @return
	 */
	public List<EditTrainArrange> getAllTrainArrange() {
		// TODO Auto-generated method stub		
		return trainMapper.getAllTrainArrange();
	}

	/**
	 * 添加火车安排
	 * @param startId 起始站编号
	 * @param endId 到达站编号
	 * @param trainId 火车编号
	 * @param trainTripId 火车行程编号
	 * @param startTime 开始时间
	 * @param endTime 到达时间
	 * @param totleTime 总时间
	 */
	public void addTrainArrange(Integer startId, Integer endId, Integer trainId, Integer trainTripId, String startTime,
			String endTime, String totleTime) {		
		Integer line = trainMapper.addTrainArrange(startId, endId, trainId, trainTripId, startTime, endTime, totleTime);
		if(line < 1) {
			throw new SystemException("添加火车安排失败，请重试");
		}
	}

	/**
	 * 通过火车名称查询火车日期安排
	 * @param trainName
	 * @return
	 */
	public List<TrainArrange> queryTrainDateArrangeByTrainName(Integer trainId) {
		
		List<TrainArrange> tArranges = trainMapper.queryTrainDateArrangeByTrainName(trainId);
		
		return tArranges;
	}

	/**
	 * 添加火车日期安排
	 * @param startDay
	 * @param arrangeId
	 * @param explain
	 * @param endDay
	 */
	public void addTrainDateArrange(String startDay, Integer arrangeId, String explain, String endDay,Integer groupId) {

		Integer line = trainMapper.addTrainDateArrange(startDay, arrangeId, explain, endDay, groupId);
		if(line < 1) {
			throw new SystemException("添加火车日期安排失败，请重试");
		}
	}	
}
