package test;
import java.util.Date;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.UUID;
import org.mybatis.spring.SqlSessionFactoryBean;
public class TestCase {
public static void main(String[] args) throws ParseException {
	File file = new File("D:\\tuleUserImage\\logo.jpg");
	System.out.println(file.getPath());
	File file2 = new File(file.getPath());
	System.out.println(file2.getPath());
}
}
