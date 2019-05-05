package utils;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateUtil {

	public final static String YYMMRRHHMMSS = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * ���ַ���ת����ʱ�����
	 * @param time
	 * @return
	 * @throws ParseException 
	 */
	public static Long getTimeByDate(String time,String format) throws ParseException {
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		Date date = simpleDateFormat.parse(time);
		return date.getTime();
	}
}
