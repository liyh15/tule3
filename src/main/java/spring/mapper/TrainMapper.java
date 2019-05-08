package spring.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;
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
}

