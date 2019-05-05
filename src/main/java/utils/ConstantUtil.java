package utils;

/**
 * 一些基础的配置文件
 * @author 李元浩
 *
 */
public class ConstantUtil {

	/*
	 * 表名的配置接口
	 */
	public interface TableName {		
		
		String TULE_PASSENGER = "tule_passenger";
	}
	/**
	 * 超时时间
	 * @author 李元浩
	 */
	public interface OutTime {
		
		Long TWOHOUR = 	2*60*60*1000L;
	}
}
