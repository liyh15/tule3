package entity;

import java.io.Serializable;

/**
 * 编辑火车安排类
 * @author 李元浩
 *
 */
public class EditTrainArrange implements Serializable {

	private int id; //火车日期安排编号
	private String trainName;//火车名称
    private String startTime;//开车时间
    private String endTime;//到达时间
    private String startStation;//开始站
    private String endStation;//到达站
    private String totalTime;//总时间
    private int tripId;//行程编号
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
