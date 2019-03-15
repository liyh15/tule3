package entity;
import java.io.Serializable;
import java.util.ArrayList;
/**
 * 搜索得到的火车车次列表
 */
public class TrainArrange implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int id;     //火车安排编号
     private String date;// 开始日期
     private String endDate;// 到达日期
     private String trainName;//火车名称
     private String startTime;//开车时间
     private String endTime;//到达时间
     private String startStation;//开始站
     private String endStation;//到达站
     private String totalTime;//总时间
     private ArrayList<TrainSeat> trainSeats;//舱段的价格及剩余座位类
     private ArrayList<StopOverSation> stopOverSations;//经停站集合  
	public int getId() {
		return id;
	}
	public String getDate() {
		return date;
	}
	public String getTrainName() {
		return trainName;
	}
	public String getStartTime() {
		return startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public String getStartStation() {
		return startStation;
	}
	public String getTotalTime() {
		return totalTime;
	}
	
	public String getEndStation() {
		return endStation;
	}
	public ArrayList<TrainSeat> getTrainSeats() {
		return trainSeats;
	}
	public ArrayList<StopOverSation> getStopOverSations() {
		return stopOverSations;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public void setStartStation(String startStation) {
		this.startStation = startStation;
	}
	public void setEndStation(String endStation) {
		this.endStation = endStation;
	}
	public void setTotalTime(String totalTime) {
		this.totalTime = totalTime;
	}
	public void setTrainSeats(ArrayList<TrainSeat> trainSeats) {
		this.trainSeats = trainSeats;
	}
	public void setStopOverSations(ArrayList<StopOverSation> stopOverSations) {
		this.stopOverSations = stopOverSations;
	}
	
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	@Override
	public String toString() {
		return "TrainArrange [id=" + id + ", date=" + date + ", trainName=" + trainName + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", startStation=" + startStation + ", endStation=" + endStation
				+ ", totalTime=" + totalTime + ", trainSeats=" + trainSeats + ", stopOverSations=" + stopOverSations
				+ "]";
	}   
}
