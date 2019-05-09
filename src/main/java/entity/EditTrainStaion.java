package entity;

import java.io.Serializable;

/**
 * 编辑火车站显示类
 * @author 李元浩
 *
 */
public class EditTrainStaion implements Serializable {

	private Integer id;
	
	private String stationName;
	
	private String cityName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}	
}
