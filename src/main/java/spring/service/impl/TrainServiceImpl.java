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
	
	private final static String NEVERSET = "δ����";
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
			}
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
