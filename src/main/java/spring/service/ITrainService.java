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
 * 火车类型的服务
 * @author 李元浩
 *
 */
public interface ITrainService {
   
	/**
	 * 通过火车安排编号获得火车安排信息
	 * @param id 火车安排编号
	 * @return 返回火车安排信息
	 */
	public TrainArrange getTrainArrangeById(Integer id);
	
	/**
	 * 通过编号获得火车日期安排对象
	 * @param id
	 * @return
	 */
	public TrainDateArrange getTrainDateArrangeById(Integer id);
	
	/**
	 * 获取所有的列车
	 * @return
	 */
	public List<Train> getAllTrain();	
	
	/**
	 * 添加列车
	 * @param trainName
	 */
	public void addTrain(String trainName);
	
	/**
	 * 编辑列车
	 * @param name 列车名称
	 * @param id 列车标号
	 */
	public void editTrain(String name,Integer id);
	
	/**
	 * 获得所有的列车的行程
	 * @return
	 */
	public Map<Integer, List<StopOverSation>> getTrainTrip();
	
	/**
	 * 获得所有的火车站
	 * @return
	 */
	public List<TrainStation> getTrainStation();
	
	/**
	 * 更新车次行程
	 * @param requests
	 */
	public void updateTrainTrip(List<StopOverStationRequest> requests);
	
	/**
	 * 添加车次行程
	 * @param stopOverSations
	 */
	public void addTrainTrip(List<StopOverSation> stopOverSations);
	
	/**
	 * 获得所有的火车站
	 * @return
	 */
	public List<EditTrainStaion> getAllEditTrainStaions();
	
	/**
	 * 添加火车站
	 * @param stationName 火车站名称
	 * @param cityName 城市名称
	 */
	public void addTrainStaion(String stationName,Integer cityId);
	
	/**
	 * 获得所有的火车安排
	 * @return
	 */
	public List<EditTrainArrange> getAllTrainArrange();
	
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
	public void addTrainArrange(Integer startId,
			Integer endId,Integer trainId,Integer trainTripId,
			String startTime,String endTime,String totleTime);
}
