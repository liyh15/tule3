package dao.test;

import java.util.ArrayList;

import dao.TrainDao;

/**
 * 火车购票数据库层的事务高并发测试
 * 
 * @author 李元浩
 */
public class TrainDaoTest implements Runnable {
	private TrainDao trainDao = new TrainDao();
	private int one;
	private int two;
	private static ArrayList<String> lists=new ArrayList<String>();

	public TrainDaoTest(int one, int two) {
		super();
		this.one = one;
		this.two = two;
	}

	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 4000; i++) {
            new Thread(new TrainDaoTest(1, 0)).start();
		}
		long b=System.currentTimeMillis();

	/*	Thread.sleep(40000);
		for(String a:lists){
			System.out.println(a);
		}
		System.out.println(lists.size());*/
	}

	public void run() {
		String a=trainDao.getSeatArrange(one, two);
		if(a != null){
			lists.add(a);
		}
	}
}
