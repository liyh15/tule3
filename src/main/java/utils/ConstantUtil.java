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
	 * 时间段(单位为毫秒)
	 * @author 李元浩
	 */
	public interface OutTime {
		
		// 2小时
		Long TWOHOUR = 	2*60*60*1000L;
		
		// 15天
		Long FIFTEENDAY = 120*60*60*1000L;
		
		// 48小时
		Long FOUREIGHTHOUR = 48*60*60*1000L;
		
		// 24小时
		Long ONEDAY = 24*60*60*1000L;
	}
}
