package entity;

/**
 * ������ʵ����
 * @author ��Ԫ��
 *
 */
public class TableKey {
    
	// ������
	private Integer id;
	// ������
	private String tableName;
    // ����ʼ����
	private Integer startKey;
	// ������ֵ
	private Integer addNum;
	// ��ǰ����ֵ
	private Integer keyNum;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public Integer getStartKey() {
		return startKey;
	}
	public void setStartKey(Integer startKey) {
		this.startKey = startKey;
	}
	public Integer getAddNum() {
		return addNum;
	}
	public void setAddNum(Integer addNum) {
		this.addNum = addNum;
	}
	public Integer getKeyNum() {
		return keyNum;
	}
	public void setKeyNum(Integer keyNum) {
		this.keyNum = keyNum;
	}
	
	
}
