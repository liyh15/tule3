package entity;

import java.io.Serializable;

/**
 * 乘客实体类
 * @author 李元浩
 */
public class Passenger implements Serializable {
    private int id;  //乘客编号
    private String name;//乘客姓名
    private String personalId;//乘客证件号码
    private String type;//乘客证件类型
    private int userId; //乘客所属用户编号
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPersonalId() {
		return personalId;
	}
	public void setPersonalId(String personalId) {
		this.personalId = personalId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
    
}
