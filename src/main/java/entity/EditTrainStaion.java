package entity;

import java.io.Serializable;

/**
 * �༭��վ��ʾ��
 * @author ��Ԫ��
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
