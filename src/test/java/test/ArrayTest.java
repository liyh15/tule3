package test;

public class ArrayTest {
public static void main(String[] args) {
	String a = "&aa&bb&";
	String [] as = a.split("&");
	System.out.println(as[0].equals(""));
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
