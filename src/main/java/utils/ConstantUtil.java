package utils;

/**
 * һЩ�����������ļ�
 * @author ��Ԫ��
 *
 */
public class ConstantUtil {

	/*
	 * ���������ýӿ�
	 */
	public interface TableName {		
		
		String TULE_PASSENGER = "tule_passenger";
	}
	/**
	 * ʱ���(��λΪ����)
	 * @author ��Ԫ��
	 */
	public interface OutTime {
		
		// 2Сʱ
		Long TWOHOUR = 	2*60*60*1000L;
		
		// 15��
		Long FIFTEENDAY = 120*60*60*1000L;
		
		// 48Сʱ
		Long FOUREIGHTHOUR = 48*60*60*1000L;
		
		// 24Сʱ
		Long ONEDAY = 24*60*60*1000L;
	}
}
