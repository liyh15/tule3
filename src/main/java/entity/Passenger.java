package entity;

import java.io.Serializable;

/**
 * �˿�ʵ����
 * @author ��Ԫ��
 */
public class Passenger implements Serializable {
    private int id;  //�˿ͱ��
    private String name;//�˿�����
    private String personalId;//�˿�֤������
    private String type;//�˿�֤������
    private int userId; //�˿������û����
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
	@Override
	public String toString() {
		return "Passenger [id=" + id + ", name=" + name + ", personalId=" + personalId + ", type=" + type + ", userId="
				+ userId + "]";
	}
    
}
