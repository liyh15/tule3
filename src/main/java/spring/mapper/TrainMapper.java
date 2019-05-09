package spring.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import entity.EditTrainArrange;
import entity.EditTrainStaion;
import entity.Train;
import entity.TrainArrange;
import entity.TrainDateArrange;
import entity.TrainStation;
import entity.TrainTrip;

/**
 * 火车的mybatis实现接口mapper
 * @author 李元浩
 */
public interface TrainMapper {
   
	/**
	 * 通过火车安排编号获得火车安排信息
	 * @param id 火车安排编号
	 * @return 返回火车安排信息
	 */
	public TrainArrange getTrainArrangeById(@Param("id") Integer id);
	
	/**
	 * 通过编号获得火车日期安排对象
	 * @param id
	 * @return
	 */
	public TrainDateArrange getTrainDateArrangeById(@Param("id") Integer id);
	
	/**
	 * 获取所有的列车
	 * @return
	 */
	public List<Train> getAllTrain();
	
	/**
	 * 添加列车
	 * @param trainName
	 * @return
	 */
	public Integer addTrain(@Param("name") String trainName);
	
	/**
	 * 通过列车查询列车车次
	 * @param id
	 * @return
	 */
	public Train getTrainById(@Param("id") Integer id);
	
	/**
	 * 更新列车
	 * @param trainName
	 * @param id
	 * @return
	 */
	public Integer updateTrainById(@Param("name") String trainName,@Param("id") Integer id);
	
	/**
	 * 获得所有的火车行程
	 * @return
	 */
	public List<TrainTrip> getAllTrainTrip();
	
	/**
	 * 获得所有的车站
	 * @return
	 */
	public List<TrainStation> getAllTrainStation();
	
	/**
	 * 通过行程编号获得指定的行程
	 * @param id
	 * @return
	 */
	public TrainTrip getTrainTripById(@Param("id") Integer id);
	
	/**
	 * 更新火车行程
	 * @param id
	 * @param explain
	 * @return
	 */
	public Integer updateTrainTripById(@Param("id") Integer id,@Param("explain") String explain);
	
	/**
	 * 添加车次行程
	 * @param explain
	 * @return
	 */
	public Integer addTrainTrip(@Param("explain") String explain);
	
	/**
	 * 获得所有的火车站
	 * @return
	 */
	public List<EditTrainStaion> getEditTrainStaions();
	
	/**
	 * 添加火车站
	 * @param stationName 火车站名称
	 * @param cityId 城市编号
	 * @return
	 */
	public Integer addTrainStaion(@Param("name") String stationName,@Param("id") Integer cityId);
	
	/**
	 * 获得所有的火车安排
	 * @return
	 */
	public List<EditTrainArrange> getAllTrainArrange();
	
	/**
	 * 添加火车安排
	 * @param startId 开始车站
	 * @param endId 到达车站
	 * @param trainId 火车编号
	 * @param trainTripId 火车安排编号
	 * @param startTime 开始时间
	 * @param endTime 到达时间
	 * @param totleTime 总时间
	 * @return
	 */
	public Integer addTrainArrange(@Param("startId")Integer startId,
			@Param("endId")Integer endId, @Param("trainId") Integer trainId, @Param("trainTripId") Integer trainTripId,
			@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("totleTime") String totleTime);
}

