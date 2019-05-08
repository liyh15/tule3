package spring.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
			}
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
			char [] arriveChar = arriveTime.toCharArray();
			char [] arriveCharZ = stopStation2.getArriveTime().toCharArray();
			for(int j = 0; j < arriveChar.length ; j++) {				
				if(arriveChar[j] == 'z') {
					arriveBuilder.append(arriveCharZ[j]);
				} else {
					arriveBuilder.append(arriveChar[j]);
				}
			}
			System.out.println(startBuilder.toString());
			System.out.println(arriveBuilder.toString());
		}		
	}	
}
