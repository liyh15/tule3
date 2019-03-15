package test;

public class ArrayTest {
public static void main(String[] args) {
	System.out.println(new ArrayTest().aaa());
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
