package test;

import java.text.DecimalFormat;

import dao.TrainDao;

public class FloatTest {
	public static void main(String[] args) {
		aaa();
		//bbb();
	}
	
	public static void bbb(){
		for(int i=0;i<4000;i++){
			new Thread(new Runnable() {				
				public void run() {
					TrainDao dao = new TrainDao();
					System.out.println(dao.getSeatArrange(3, 0));	
					System.out.println(Thread.currentThread().getName());
				}
			}).start();
		}
	}
	
	public  static void aaa(){
		TrainDao dao = new TrainDao();
		for(int i=0;i<2;i++){
			for(int j=0;j<10;j++){
				for(int t=0;t<5;t++){
					dao.returnTrainTicket(3,i+":"+j+":"+t,0);
				}
			}			
		}
	}
}
