package entity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingDeque;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * 火车座位号及剩余描述
 * @author tarena
 */
public class TrainSeat implements Serializable {

	
	private static final long serialVersionUID = 1L;
	public static Map<String, Integer> seatMap = new HashMap<String, Integer>();
	static{
		seatMap.put("硬座",0);
		seatMap.put("硬卧",1);
		seatMap.put("软卧",2);
		seatMap.put("无座",3);
		seatMap.put("商务座",0);
		seatMap.put("一等座",1);
		seatMap.put("二等座",2);
	}
	private String seatType;     //座位类型
    private int[][][] seatLayout=new int[2][10][5];//座椅型座位剩余情况,每种火车每种车厢都是2车10排5列     
    private int[][][] softSeatLayout=new int[2][10][2];//软卧的情况
    private int[][][] hardSeatLayout=new int[2][10][3];//硬卧的情况
    private int [][] noSeatLayout=new int[2][10];//无座的情况
    private int price; // 价格
    public TrainSeat() {
	}
    /**
     * 获得座位的名称
     * @return
     */
	public String getSeatType() {
		return seatType;
	}
	public int[][][] getSoftSeatLayout() {
		return softSeatLayout;
	}
	public void setSoftSeatLayout(int[][][] softSeatLayout) {
		this.softSeatLayout = softSeatLayout;
	}
	public int[][][] getHardSeatLayout() {
		return hardSeatLayout;
	}
	public void setHardSeatLayout(int[][][] hardSeatLayout) {
		this.hardSeatLayout = hardSeatLayout;
	}
	/**
	 * 获得选定座位的剩余数量
	 * @return
	 */
	public int getCount()
	{
		if(seatType.equals("无座"))
		{
			int count=0;
			for(int i=0;i<noSeatLayout.length;i++)
			{
				for(int y=0;y<noSeatLayout[i].length;y++)
				{
					if(noSeatLayout[i][y]==0)
					{
						count++;
					}
				}
			}
			return count;
		}
		else if(seatType.equals("软卧"))
		{
			int count=0;
			for(int i=0;i<softSeatLayout.length;i++)
			{
				for(int y=0;y<softSeatLayout[i].length;y++)
				{
					for(int t=0;t<softSeatLayout[i][y].length;t++)
					{
						if(softSeatLayout[i][y][t]==0)
						{
							count++;
						}
					}
				}
			}
			return count;
		}
		else if(seatType.equals("硬卧"))
		{
			int count=0;
			for(int i=0;i<hardSeatLayout.length;i++)
			{
				for(int y=0;y<hardSeatLayout[i].length;y++)
				{
					for(int t=0;t<hardSeatLayout[i][y].length;t++)
					{
						if(hardSeatLayout[i][y][t]==0)
						{
							count++;
						}
					}
				}
			}
			return count;
		}
		else
		{
			int count=0;
			for(int i=0;i<seatLayout.length;i++)
			{
				for(int y=0;y<seatLayout[i].length;y++)
				{
					for(int t=0;t<seatLayout[i][y].length;t++)
					{
						if(seatLayout[i][y][t]==0)
						{
							count++;
						}
					}
				}
			}
			return count;
		}
	}
	
	/**
	 * 获得当前座位的一个座位
	 * @return
	 */
	public String findSeat()
	{
		if(seatType.equals("无座"))
		{
			int count=0;
			for(int i=0;i<noSeatLayout.length;i++)
			{
				for(int y=0;y<noSeatLayout[i].length;y++)
				{
					if(noSeatLayout[i][y]==0)
					{
						noSeatLayout[i][y]=1;
						return i+":"+y;
					}
				}
			}
		}
		else if(seatType.equals("软卧"))
		{
			int count=0;
			for(int i=0;i<softSeatLayout.length;i++)
			{
				for(int y=0;y<softSeatLayout[i].length;y++)
				{
					for(int t=0;t<softSeatLayout[i][y].length;t++)
					{
						if(softSeatLayout[i][y][t]==0)
						{
							softSeatLayout[i][y][t]=1;
							return i+":"+y+":"+t;
						}
					}
				}
			}
		}
		else if(seatType.equals("硬卧"))
		{
			int count=0;
			for(int i=0;i<hardSeatLayout.length;i++)
			{
				for(int y=0;y<hardSeatLayout[i].length;y++)
				{
					for(int t=0;t<hardSeatLayout[i][y].length;t++)
					{
						if(hardSeatLayout[i][y][t]==0)
						{
							hardSeatLayout[i][y][t]=1;
							return i+":"+y+":"+t;
						}
					}
				}
			}
		}
		else
		{
			for(int i=0;i<seatLayout.length;i++)
			{
				for(int y=0;y<seatLayout[i].length;y++)
				{
					for(int t=0;t<seatLayout[i][y].length;t++)
					{
						if(seatLayout[i][y][t]==0)
						{
							seatLayout[i][y][t]=1;
							return i+":"+y+":"+t;
						}
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * 让指定座位占座
	 * @param seat 指定座位的编号
	 */
	public void reduceSeat(String seat)
	{
		String [] seats=seat.split(":");
		if(seatType.equals("无座"))
		{
			int a=Integer.parseInt(seats[0]);
			int b=Integer.parseInt(seats[1]);
			noSeatLayout[a][b]=1;
		}
		else  if(seatType.equals("硬卧"))
		{
			int a=Integer.parseInt(seats[0]);
			int b=Integer.parseInt(seats[1]);
			int c=Integer.parseInt(seats[2]);
			hardSeatLayout[a][b][c]=1;
		}
		else  if(seatType.equals("软卧"))
		{
			int a=Integer.parseInt(seats[0]);
			int b=Integer.parseInt(seats[1]);
			int c=Integer.parseInt(seats[2]);
			softSeatLayout[a][b][c]=1;
		}
		else {
			int a=Integer.parseInt(seats[0]);
			int b=Integer.parseInt(seats[1]);
			int c=Integer.parseInt(seats[2]);
			seatLayout[a][b][c]=1;
		}		
	}
	
	/**
	 * 空出指定的座位
	 * @param seat 指定座位的编号
	 */
	public void returnSeat(String seat)
	{
		String [] seats=seat.split(":");
		if(seatType.equals("无座"))
		{
			int a=Integer.parseInt(seats[0]);
			int b=Integer.parseInt(seats[1]);
			noSeatLayout[a][b]=0;
		}
		else  if(seatType.equals("硬卧"))
		{
			int a=Integer.parseInt(seats[0]);
			int b=Integer.parseInt(seats[1]);
			int c=Integer.parseInt(seats[2]);
			hardSeatLayout[a][b][c]=0;
		}
		else  if(seatType.equals("软卧"))
		{
			int a=Integer.parseInt(seats[0]);
			int b=Integer.parseInt(seats[1]);
			int c=Integer.parseInt(seats[2]);
			softSeatLayout[a][b][c]=0;
		}
		else {
			int a=Integer.parseInt(seats[0]);
			int b=Integer.parseInt(seats[1]);
			int c=Integer.parseInt(seats[2]);
			seatLayout[a][b][c]=0;
		}		
	}
	
	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}	
	public int[][][] getSeatLayout() {
		return seatLayout;
	}
	public void setSeatLayout(int[][][] seatLayout) {
		this.seatLayout = seatLayout;
	}
	public int[][] getNoSeatLayout() {
		return noSeatLayout;
	}
	public void setNoSeatLayout(int[][] noSeatLayout) {
		this.noSeatLayout = noSeatLayout;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
		
	
	@Override
	public String toString() {
		return "TrainSeat [seatType=" + seatType + ", seatLayout=" + Arrays.toString(seatLayout) + ", softSeatLayout="
				+ Arrays.toString(softSeatLayout) + ", hardSeatLayout=" + Arrays.toString(hardSeatLayout)
				+ ", noSeatLayout=" + Arrays.toString(noSeatLayout) + ", price=" + price + "]";
	}
	public static void main(String[] args) {
		TrainSeat seat1=new TrainSeat();
		TrainSeat seat2=new TrainSeat();
		TrainSeat seat3=new TrainSeat();
		TrainSeat seat4=new TrainSeat();
		seat1.setSeatType("硬座");
		seat2.setSeatType("硬卧");
		seat3.setSeatType("软卧");
		//seat1.setSeatType("商务座");
		seat1.setPrice(300);
		//seat2.setSeatType("一等座");
		seat2.setPrice(200);
		//seat3.setSeatType("二等座");
		seat3.setPrice(100);
		seat4.setSeatType("无座");
		seat4.setPrice(50);
		List<TrainSeat> trainSeats=new ArrayList<TrainSeat>();
		trainSeats.add(seat1);
		trainSeats.add(seat2);
		trainSeats.add(seat3);
		trainSeats.add(seat4);
		JSONArray jsonArray=JSONArray.fromObject(trainSeats);
		System.out.println(jsonArray.toString());
	    ArrayList<TrainSeat> trainSeatss=(ArrayList<TrainSeat>)jsonArray.toList(jsonArray,TrainSeat.class);
        //JSONObject jsonObject=JSONObject.fromObject(seat);
        //JSONObject jsonObject2=JSONObject.fromObject(jsonObject);
        //System.out.println(jsonObject);
        //seat=(TrainSeat) JSONObject.toBean(jsonObject2, TrainSeat.class);
        
	}
}
