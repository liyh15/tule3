package entity;

import java.io.Serializable;


public class TestEntity implements Serializable {
   
	private String name ;
	
	private String hign ;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHign() {
		return hign;
	}

	public void setHign(String hign) {
		this.hign = hign;
	}
	
	
}
