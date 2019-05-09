package spring.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import entity.EditTrainArrange;
import entity.EditTrainStaion;
import entity.StopOverSation;
import entity.StopOverStationRequest;
import entity.Train;
import entity.TrainArrange;
import entity.TrainDateArrange;
import entity.TrainStation;

/**
 * �����͵ķ���
 * @author ��Ԫ��
 *
 */
public interface ITrainService {
   
	/**
	 * ͨ���𳵰��ű�Ż�û𳵰�����Ϣ
	 * @param id �𳵰��ű��
	 * @return ���ػ𳵰�����Ϣ
	 */
	public TrainArrange getTrainArrangeById(Integer id);
	
	/**
	 * ͨ����Ż�û����ڰ��Ŷ���
	 * @param id
	 * @return
	 */
	public TrainDateArrange getTrainDateArrangeById(Integer id);
	
	/**
	 * ��ȡ���е��г�
	 * @return
	 */
	public List<Train> getAllTrain();	
	
	/**
	 * ����г�
	 * @param trainName
	 */
	public void addTrain(String trainName);
	
	/**
	 * �༭�г�
	 * @param name �г�����
	 * @param id �г����
	 */
	public void editTrain(String name,Integer id);
	
	/**
	 * ������е��г����г�
	 * @return
	 */
	public Map<Integer, List<StopOverSation>> getTrainTrip();
	
	/**
	 * ������еĻ�վ
	 * @return
	 */
	public List<TrainStation> getTrainStation();
	
	/**
	 * ���³����г�
	 * @param requests
	 */
	public void updateTrainTrip(List<StopOverStationRequest> requests);
	
	/**
	 * ��ӳ����г�
	 * @param stopOverSations
	 */
	public void addTrainTrip(List<StopOverSation> stopOverSations);
	
	/**
	 * ������еĻ�վ
	 * @return
	 */
	public List<EditTrainStaion> getAllEditTrainStaions();
	
	/**
	 * ��ӻ�վ
	 * @param stationName ��վ����
	 * @param cityName ��������
	 */
	public void addTrainStaion(String stationName,Integer cityId);
	
	/**
	 * ������еĻ𳵰���
	 * @return
	 */
	public List<EditTrainArrange> getAllTrainArrange();
	
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
	public void addTrainArrange(Integer startId,
			Integer endId,Integer trainId,Integer trainTripId,
			String startTime,String endTime,String totleTime);
}
