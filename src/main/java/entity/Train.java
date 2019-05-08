package entity;

import java.io.Serializable;

/**
 * 列车的实体类
 * @author 李元浩
 */
public class Train implements Serializable {
   
	// 列车编号
	private Integer id;
	
	// 列车名称
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
