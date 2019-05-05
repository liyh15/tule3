package spring.mapper;

import org.apache.ibatis.annotations.Param;

import entity.TableKey;

/**
 * 主键表mapper接口
 * @author 李元浩
 *
 */
public interface TableKeyMapper {

	/**
	 * 通过表名获得表的主键生成类
	 * @param tableName
	 * @return
	 */
	public TableKey getTableKeyByTableName(@Param("tableName") String tableName);
	
	/**
	 * 更新主键表
	 * @param tableKey
	 * @return
	 */
	public Integer updateTableKey(TableKey tableKey);
}
