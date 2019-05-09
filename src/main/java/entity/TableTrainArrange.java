package entity;

import java.util.List;

/**
 * 火车安排表单包装类
 * @author 李元浩
 */
public class TableTrainArrange {

	private String trainName;
	
	private List<EditTrainArrange> editTrainArrange;

	public String getTrainName() {
		return trainName;
	}

	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}

	public List<EditTrainArrange> getEditTrainArrange() {
		return editTrainArrange;
	}

	public void setEditTrainArrange(List<EditTrainArrange> editTrainArrange) {
		this.editTrainArrange = editTrainArrange;
	}	
}
