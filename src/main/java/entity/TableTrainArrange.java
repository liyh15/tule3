package entity;

import java.util.List;

/**
 * �𳵰��ű���װ��
 * @author ��Ԫ��
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
