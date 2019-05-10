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
	
	private final static String NEVERSET = "δ����";
	
	private final static String MINUTE = "����";
	
	private final static String BASETIME = "zz:zz:zz";
	
	@Autowired
	@Qualifier("redisTemplate")
	private RedisTemplate redisTemplate;
	
	@Autowired
	private TrainMapper trainMapper;
	/**
	 * �����͵ķ���
	 * @author ��Ԫ��
	 */
	public TrainArrange getTrainArrangeById(Integer id) {		
		return trainMapper.getTrainArrangeById(id);
	}
	
	/**
	 * ͨ����Ż�û����ڰ��Ŷ���
	 * @param id
	 * @return
	 */
	public TrainDateArrange getTrainDateArrangeById(Integer id) {
		return trainMapper.getTrainDateArrangeById(id);
	}

	/**
	 * ��ȡ���е��г�
	 * @return
	 */
	public List<Train> getAllTrain() {
	
		return trainMapper.getAllTrain();
	}

	/**
	 * ����г�
	 * @param trainName
	 */
	public void addTrain(String trainName) {
		// TODO Auto-generated method stub
		List<Train> trains = trainMapper.getAllTrain();
		for(Train train : trains) {			
			if(train.getName().equals(trainName)) {
				throw new SystemException("���г��Ѿ�����");
			}
		}
		Integer line = trainMapper.addTrain(trainName);
		if(line < 1) {
			throw new SystemException("����г�ʧ��");
		}
	}

	/**
	 * �༭�г�
	 * @param name �г�����
	 * @param id �г����
	 */
	public void editTrain(String name, Integer id) {
		
		Train train = trainMapper.getTrainById(id);
		String trainName = train.getName();
		String newName = trainName.substring(0,1)+name;
		Integer line = trainMapper.updateTrainById(newName, id);
		if(line < 1) {
			throw new SystemException("�༭�г�ʧ��");
		}
	}

	/**
	 * ������е��г����г�
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
				stopOverSation.setStopTime(stopOverSation.getStopTime().substring(0,stopOverSation.getStopTime().indexOf("����")));
			}
			map.put(trainTrip.getId(), sation);
		}		
		return map;
	}


	/**
	 * ������еĻ�վ
	 * @return
	 */
	public List<TrainStation> getTrainStation() {
		
		List<TrainStation> trainStations = trainMapper.getAllTrainStation();
		return trainStations;
	}

	/**
	 * ���³����г�
	 * @param requests
	 */
	public void updateTrainTrip(List<StopOverStationRequest> requests) {
		
		// ��ñ����г̵ı��
		Integer id = requests.get(0).getId();
		TrainTrip trainTrip = trainMapper.getTrainTripById(id);
		JSONArray jsonArray = JSONArray.fromObject(trainTrip.getTrip());
		ArrayList<StopOverSation> sation = (ArrayList<StopOverSation>) jsonArray.toList(jsonArray,
				StopOverSation.class);
		// �������ϴ洢�µ��г���Ϣ
		ArrayList<StopOverSation> sations = new ArrayList<StopOverSation>();
		for(int i = 0;i < requests.size(); i++) {
			
			StopOverSation stopStation = new StopOverSation();			
			// ����ǰ�˴������ļ��ϣ��鿴�޸Ĺ���ֵ
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
			// ������ʼʱ�䣬�鿴�Ǹ�ʱ��任����
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
			throw new SystemException("���»��г̳����쳣��������");
		}
	}

	/**
	 * ��ӳ����г�
	 * @param stopOverSations
	 */
	public void addTrainTrip(List<StopOverSation> stopOverSations) {
		
		if(stopOverSations.size() <= 1) {
			
			throw new SystemException("�г��еĳ�վ����С������");
		}
		
		for(StopOverSation sation : stopOverSations) {
			
			if(sation.getStation().equals(NEVERSET)) {
				
				throw new SystemException("�����ó�վ����");
			}
			
			if(sation.getArriveTime().contains("z") && !sation.getArriveTime().equals(BASETIME)) {
				throw new SystemException("������"+sation.getStation()+"��ȷ�ĵ���ʱ��");
			}
			
			if(sation.getStartTime().contains("z") && !sation.getStartTime().equals(BASETIME)) {
				throw new SystemException("������"+sation.getStation()+"��ȷ�Ŀ���ʱ��");
			}
			
			if(StringUtils.isEmpty(sation.getStopTime())) {
				throw new SystemException("��վ��ͣ��ʱ�䲻��Ϊ��");
			}
			sation.setStopTime(sation.getStopTime()+"����");
		}
		
		String newJson = JSONArray.fromObject(stopOverSations).toString();
		Integer line = trainMapper.addTrainTrip(newJson);
		if(line < 1) {
			throw new SystemException("��ӻ��г̳����쳣��������");
		}
	}

	/**
	 * ������еĻ�վ
	 * @return
	 */
	public List<EditTrainStaion> getAllEditTrainStaions() {
		
		List<EditTrainStaion> editTrainStaions = trainMapper.getEditTrainStaions();
		return editTrainStaions;
	}

	/**
	 * ��ӻ�վ
	 * @param stationName ��վ����
	 * @param cityName ��������
	 */
	public void addTrainStaion(String stationName, Integer cityId) {
		
		Integer line = trainMapper.addTrainStaion(stationName, cityId);
		if(line < 1) {
			throw new SystemException("��ӻ�վ�����쳣��������");
		}
	}

	/**
	 * ������еĻ𳵰���
	 * @return
	 */
	public List<EditTrainArrange> getAllTrainArrange() {
		// TODO Auto-generated method stub		
		return trainMapper.getAllTrainArrange();
	}

	/**
	 * ��ӻ𳵰���
	 * @param startId ��ʼվ���
	 * @param endId ����վ���
	 * @param trainId �𳵱��
	 * @param trainTripId ���г̱��
	 * @param startTime ��ʼʱ��
	 * @param endTime ����ʱ��
	 * @param totleTime ��ʱ��
	 */
	public void addTrainArrange(Integer startId, Integer endId, Integer trainId, Integer trainTripId, String startTime,
			String endTime, String totleTime) {		
		Integer line = trainMapper.addTrainArrange(startId, endId, trainId, trainTripId, startTime, endTime, totleTime);
		if(line < 1) {
			throw new SystemException("��ӻ𳵰���ʧ�ܣ�������");
		}
	}

	/**
	 * ͨ�������Ʋ�ѯ�����ڰ���
	 * @param trainName
	 * @return
	 */
	public List<TrainArrange> queryTrainDateArrangeByTrainName(Integer trainId) {
		
		List<TrainArrange> tArranges = trainMapper.queryTrainDateArrangeByTrainName(trainId);
		
		return tArranges;
	}

	/**
	 * ��ӻ����ڰ���
	 * @param startDay
	 * @param arrangeId
	 * @param explain
	 * @param endDay
	 */
	public void addTrainDateArrange(String startDay, Integer arrangeId, String explain, String endDay,Integer groupId) {

		Integer line = trainMapper.addTrainDateArrange(startDay, arrangeId, explain, endDay, groupId);
		if(line < 1) {
			throw new SystemException("��ӻ����ڰ���ʧ�ܣ�������");
		}
	}	
}
