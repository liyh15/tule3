package entity;

import java.io.Serializable;

/**
 * ���г�mapperӳ����
 * @author ��Ԫ��
 */
public class TrainTrip implements Serializable {

	private Integer id;
	
	private String trip;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTrip() {
		return trip;
	}

	public void setTrip(String trip) {
		this.trip = trip;
	}
}
