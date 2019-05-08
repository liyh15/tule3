package entity;

import java.io.Serializable;

/**
 * 火车站
 * @author 李元浩
 */
public class TrainStation implements Serializable {
    
	// 火车站编号
	private Integer id;
	
	// 火车站名称
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
