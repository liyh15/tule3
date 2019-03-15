package entity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
/**
 * 定义每种火车的座椅排列json字符串
 * @author tarena
 */
public class TrainSeatParamter implements Serializable{
   public static int softBerth=1; //软卧的起始车厢
   public static int hardBerth=3; //硬卧的起始车厢
   public static int hardSeat=5;//硬座的起始车厢
   public static int businessSeat=1;//商务座的起始车厢
   public static int firstSeat=3;//一等座的起始车厢
   public static int secondSeat=5;//二等座的起始车厢
   public static String normalTrain; //普通火车座位排布情况
   public static String highTrain;   //高铁动车座位排布情况
   public static Map<String, Integer> seatA=new HashMap<String, Integer>();

   static
   {
	    TrainSeat seat1=new TrainSeat();
		TrainSeat seat2=new TrainSeat();
		TrainSeat seat3=new TrainSeat();
		TrainSeat seat4=new TrainSeat();
		seat1.setSeatType("硬座");
		seat2.setSeatType("硬卧");
		seat3.setSeatType("软卧");
		seat1.setPrice(300);
		seat2.setPrice(200);
		seat3.setPrice(100);
		seat4.setSeatType("无座");
		seat4.setPrice(50);
		List<TrainSeat> trainSeats=new ArrayList<TrainSeat>();
		trainSeats.add(seat1);
		trainSeats.add(seat2);
		trainSeats.add(seat3);
		trainSeats.add(seat4);
		JSONArray jsonArray=JSONArray.fromObject(trainSeats);
	    normalTrain=jsonArray.toString();
	    seat1.setSeatType("商务座");
	    seat2.setSeatType("一等座");
	    seat3.setSeatType("二等座");
	    List<TrainSeat> trainSeats2=new ArrayList<TrainSeat>();
	    trainSeats2.add(seat1);
		trainSeats2.add(seat2);
		trainSeats2.add(seat3);
		JSONArray jsonArray2=JSONArray.fromObject(trainSeats2);
		highTrain=jsonArray2.toString();
		seatA.put("硬座", 0);
		seatA.put("硬卧", 1);
		seatA.put("软卧", 2);
		seatA.put("无座", 3);
	    seatA.put("商务座", 0);
	    seatA.put("一等座", 1);
	    seatA.put("二等座", 2);
   }
   public static void main(String[] args) {
	  //System.out.println(TrainSeatParamter.highTrain);
	  System.out.println(TrainSeatParamter.normalTrain);
}
}
