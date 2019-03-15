package dao.test;

public class Exc {
public static void main(String[] args) {
	try {
		System.out.println("aaa");
		System.out.println("aaa");
		putex();
		System.out.println("111");
	} catch (Exception e) {
		e.printStackTrace();
	}	
	}
public static void putex()
{
	throw new RuntimeException("aaaa");
}
}
