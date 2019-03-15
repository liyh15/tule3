package spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import entity.TrainArrange;
import entity.TrainDateArrange;
import spring.mapper.TrainMapper;
import spring.service.ITrainService;
@Service
public class TrainServiceImpl implements ITrainService {
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

}
