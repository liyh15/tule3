package entity;

import java.io.Serializable;

/**
 * �г���ʵ����
 * @author ��Ԫ��
 */
public class Train implements Serializable {
   
	// �г����
	private Integer id;
	
	// �г�����
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
