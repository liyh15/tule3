package entity;

import java.io.Serializable;

/**
 * 保险的实体类
 * @author 李元浩
 */
public class Insurance implements Serializable {
    private int id;      //保险的编号
    private String name; //保险的名称
    private int price;   //保险的的价格
    private int comId;//保险公司的编号
    private String type; //保险的类型
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
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	public int getComId() {
		return comId;
	}
	public void setComId(int comId) {
		this.comId = comId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
    
}
