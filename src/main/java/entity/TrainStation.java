package entity;

import java.io.Serializable;

/**
 * ��վ
 * @author ��Ԫ��
 */
public class TrainStation implements Serializable {
    
	// ��վ���
	private Integer id;
	
	// ��վ����
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
