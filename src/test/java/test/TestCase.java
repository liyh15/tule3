package test;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.UUID;
import org.mybatis.spring.SqlSessionFactoryBean;
public class TestCase {
public static void main(String[] args) throws ParseException {
	String time = "2019-03-11 17:58:40";
	Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time);
	System.out.println(date.getTime());
}
}
