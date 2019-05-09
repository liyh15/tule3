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
 * �𳵵�mybatisʵ�ֽӿ�mapper
 * @author ��Ԫ��
 */
public interface TrainMapper {
   
	/**
	 * ͨ���𳵰��ű�Ż�û𳵰�����Ϣ
	 * @param id �𳵰��ű��
	 * @return ���ػ𳵰�����Ϣ
	 */
	public TrainArrange getTrainArrangeById(@Param("id") Integer id);
	
	/**
	 * ͨ����Ż�û����ڰ��Ŷ���
	 * @param id
	 * @return
	 */
	public TrainDateArrange getTrainDateArrangeById(@Param("id") Integer id);
	
	/**
	 * ��ȡ���е��г�
	 * @return
	 */
	public List<Train> getAllTrain();
	
	/**
	 * ����г�
	 * @param trainName
	 * @return
	 */
	public Integer addTrain(@Param("name") String trainName);
	
	/**
	 * ͨ���г���ѯ�г�����
	 * @param id
	 * @return
	 */
	public Train getTrainById(@Param("id") Integer id);
	
	/**
	 * �����г�
	 * @param trainName
	 * @param id
	 * @return
	 */
	public Integer updateTrainById(@Param("name") String trainName,@Param("id") Integer id);
	
	/**
	 * ������еĻ��г�
	 * @return
	 */
	public List<TrainTrip> getAllTrainTrip();
	
	/**
	 * ������еĳ�վ
	 * @return
	 */
	public List<TrainStation> getAllTrainStation();
	
	/**
	 * ͨ���г̱�Ż��ָ�����г�
	 * @param id
	 * @return
	 */
	public TrainTrip getTrainTripById(@Param("id") Integer id);
	
	/**
	 * ���»��г�
	 * @param id
	 * @param explain
	 * @return
	 */
	public Integer updateTrainTripById(@Param("id") Integer id,@Param("explain") String explain);
	
	/**
	 * ��ӳ����г�
	 * @param explain
	 * @return
	 */
	public Integer addTrainTrip(@Param("explain") String explain);
	
	/**
	 * ������еĻ�վ
	 * @return
	 */
	public List<EditTrainStaion> getEditTrainStaions();
	
	/**
	 * ��ӻ�վ
	 * @param stationName ��վ����
	 * @param cityId ���б��
	 * @return
	 */
	public Integer addTrainStaion(@Param("name") String stationName,@Param("id") Integer cityId);
	
	/**
	 * ������еĻ𳵰���
	 * @return
	 */
	public List<EditTrainArrange> getAllTrainArrange();
	
	/**
	 * ��ӻ𳵰���
	 * @param startId ��ʼ��վ
	 * @param endId ���ﳵվ
	 * @param trainId �𳵱��
	 * @param trainTripId �𳵰��ű��
	 * @param startTime ��ʼʱ��
	 * @param endTime ����ʱ��
	 * @param totleTime ��ʱ��
	 * @return
	 */
	public Integer addTrainArrange(@Param("startId")Integer startId,
			@Param("endId")Integer endId, @Param("trainId") Integer trainId, @Param("trainTripId") Integer trainTripId,
			@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("totleTime") String totleTime);
}

