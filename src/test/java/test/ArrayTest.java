package test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ArrayTest {
public static void main(String[] args) throws ParseException {
	
	Long a = 1000000L;
	Double b = Double.valueOf(a)/1000/60/60;
	BigDecimal bg = new BigDecimal(b).setScale(2, RoundingMode.UP);
	System.out.println(bg.doubleValue());
}

public int aaa(){
	try {
		throw new Exception();
	} catch (Exception e) {
		return 1;
	}finally {
		System.out.println("aaa");
	}
}
}
