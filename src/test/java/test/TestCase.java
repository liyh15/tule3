package test;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.UUID;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.util.DigestUtils;

import net.sf.json.JSONObject;
public class TestCase {
public static void main(String[] args) throws ParseException {
	
	Map<String, String> map = new HashMap<String, String>();
	map.put("a", "bbbb");
	map.put("b", "bbb");
	JSONObject jsonObject = JSONObject.fromObject(map);
	String a = jsonObject.toString();
	System.out.println(a);
	Map<String, String> map2 = (Map<String, String>)jsonObject;
	System.out.println(map2);
}
}
