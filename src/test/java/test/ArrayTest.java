package test;

public class ArrayTest {
public static void main(String[] args) {
	Integer a = 200;
	a= (int) (a*0.90);
	System.out.println(a);
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
