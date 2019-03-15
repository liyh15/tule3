package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 记录经停每一站的实体类
 * @author 李元浩
 */
public class StopOverSation implements Serializable {

	private static final long serialVersionUID = 1L;
	private String station;  //站名
    private String arriveTime;//到达时间
    private String startTime;//出发事件
    private String stopTime; //停留时间
	public String getStation() {
		return station;
	}	
	public void setStation(String station) {
		this.station = station;
	}
	public String getArriveTime() {
		return arriveTime;
	}
	public void setArriveTime(String arriveTime) {
		this.arriveTime = arriveTime;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getStopTime() {
		return stopTime;
	}
	public void setStopTime(String stopTime) {
		this.stopTime = stopTime;
	}
	
	@Override
	public String toString() {
		return "StopOverSation [station=" + station + ", arriveTime=" + arriveTime + ", startTime=" + startTime
				+ ", stopTime=" + stopTime + "]";
	}
	public static void main(String[] args) {
		StopOverSation sation=new StopOverSation();
		StopOverSation sation1=new StopOverSation();		
		StopOverSation sation2=new StopOverSation();
		sation2.setStartTime("---");
		sation2.setArriveTime("16:20:00");
		sation2.setStopTime("10分钟");
		sation2.setStation("徐州站");	
		sation1.setStartTime("14:20:00");
		sation1.setArriveTime("12:10:00");
		sation1.setStopTime("10分钟");
		sation1.setStation("宿州站");
		sation.setStartTime("10:10:00");
		sation.setArriveTime("---");
		sation.setStopTime("10分钟");
		sation.setStation("南京站");
		List<StopOverSation> sations=new ArrayList<StopOverSation>();
		sations.add(sation2);
		sations.add(sation1);
		sations.add(sation);
		JSONArray jsonArray=JSONArray.fromObject(sations);
		System.out.println(jsonArray);
        //JSONObject jsonObject=JSONObject.fromObject(sation);
        //System.out.println(jsonObject.toString());
	}
}
