package entity;

/**
 * 主键表实体类
 * @author 李元浩
 *
 */
public class TableKey {
    
	// 表主键
	private Integer id;
	// 表名称
	private String tableName;
    // 表起始主键
	private Integer startKey;
	// 表自增值
	private Integer addNum;
	// 当前主键值
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
