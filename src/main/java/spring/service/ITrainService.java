package spring.service;

import org.apache.ibatis.annotations.Param;

import entity.TrainArrange;
import entity.TrainDateArrange;

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
	
}
