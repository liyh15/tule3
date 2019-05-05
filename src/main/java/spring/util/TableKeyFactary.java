package spring.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Scope;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import entity.TableKey;
import spring.mapper.TableKeyMapper;

/**
 * 主键生成类
 * @author 李元浩
 */
@Component
@Scope("singleton")
public class TableKeyFactary {

	@Autowired
	private TableKeyMapper tableKeyMapper;
	
	
	/**
	 * 通过表名称获取表的主键
	 * @param tableName
	 * @return
	 */
	@Transactional
	public Integer getKeyByTableName(String tableName) {
		
		TableKey tableKey = tableKeyMapper.getTableKeyByTableName(tableName);
		
		Integer key = tableKey.getKeyNum()+tableKey.getAddNum();
		
		tableKey.setKeyNum(key);
		tableKeyMapper.updateTableKey(tableKey);
		return key;
	}
}
