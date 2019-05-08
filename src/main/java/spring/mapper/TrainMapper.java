package spring.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;
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
}

