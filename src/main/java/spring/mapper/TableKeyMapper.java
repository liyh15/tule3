package spring.mapper;

import org.apache.ibatis.annotations.Param;

import entity.TableKey;

/**
 * ������mapper�ӿ�
 * @author ��Ԫ��
 *
 */
public interface TableKeyMapper {

	/**
	 * ͨ��������ñ������������
	 * @param tableName
	 * @return
	 */
	public TableKey getTableKeyByTableName(@Param("tableName") String tableName);
	
	/**
	 * ����������
	 * @param tableKey
	 * @return
	 */
	public Integer updateTableKey(TableKey tableKey);
}
