package entity;

import java.io.Serializable;

/**
 * �༭�𳵰�����
 * @author ��Ԫ��
 *
 */
public class EditTrainArrange implements Serializable {

	private int id; //�����ڰ��ű��
	private String trainName;//������
    private String startTime;//����ʱ��
    private String endTime;//����ʱ��
    private String startStation;//��ʼվ
    private String endStation;//����վ
    private String totalTime;//��ʱ��
    private int tripId;//�г̱��
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getStartStation() {
		return startStation;
	}
	public void setStartStation(String startStation) {
		this.startStation = startStation;
	}
	public String getEndStation() {
		return endStation;
	}
	public void setEndStation(String endStation) {
		this.endStation = endStation;
	}
	public String getTotalTime() {
		return totalTime;
	}
	public void setTotalTime(String totalTime) {
		this.totalTime = totalTime;
	}	
	public String getTrainName() {
		return trainName;
	}
	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}
	
	public int getTripId() {
		return tripId;
	}
	public void setTripId(int tripId) {
		this.tripId = tripId;
	}
	@Override
	public String toString() {
		return "EditTrainArrange [id=" + id + ", startTime=" + startTime + ", endTime=" + endTime + ", startStation="
				+ startStation + ", endStation=" + endStation + ", totalTime=" + totalTime + "]";
	} 
}
