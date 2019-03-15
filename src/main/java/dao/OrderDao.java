package dao;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entity.Order;
import utils.OrderUtil;
/**
 * 关于订单的数据库查询
 * @author tarena
 */
public class OrderDao {
	/**
	 * 通过用户编号查找到用户的订单集合
	 */
	 public List<Order> getOrderByUserId(int userId)
	 {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		ArrayList<Order> orders=new ArrayList<Order>();
		String sql = "select * from tule_order where user_id=? and state=1";
		try {
			connection = DBUtils.getConnection();
			statement = connection.prepareStatement(sql);
			statement.setInt(1, userId);
			resultSet=statement.executeQuery();
			while(resultSet.next())
			{
				int id=resultSet.getInt("id");
				String status=resultSet.getString("status");
				String created=resultSet.getString("create_time");
				int tdai=resultSet.getInt("traffic_date_arrange_id");
				String [] totlePrice=(String[]) getArrayA(resultSet.getString("totle_price"));
				Integer [] passId=(Integer[]) getArrayB(resultSet.getString("passenger_id"));
				String type=resultSet.getString("type");
				String [] explain=(String[]) getArrayA(resultSet.getString("oexplain"));
				String reservation=resultSet.getString("reservation");
				int returnPrice=resultSet.getInt("return_price");
				String address=resultSet.getString("distribution_address");
				String contactPhone=resultSet.getString("contact_phone");
				Order order=new Order();
				order.setId(id);
				order.setCreateTime(created.substring(0, 19));
				order.setUserId(userId);
				order.setStatus(OrderUtil.getDisByName(status));
				System.out.println(order.getStatus());
				order.setType(type);
				order.setTrafficDateArrangeId(tdai);
				order.setTotlePrice(totlePrice);;
				order.setPassengerId(passId);
				order.setExplain(explain);
				order.setReservation(reservation);
				order.setReturnPrice(returnPrice);
				order.setdAddress(address);
				order.setContactPhone(contactPhone);
				orders.add(order);
			}
			return orders;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(connection, resultSet, statement);
		}
		 return null;
	 }
	 /**
	  * 给用户添加订单
	  * @param order 订单的实体类
	  */
	 public void addOrder(Order order)
	 {
		 int userId=order.getUserId();
		 String status=order.getStatus();
		 int trafficDateArrangeId=order.getTrafficDateArrangeId();
		 String [] totlePrice=order.getTotlePrice();
		 Integer[]passengerId=order.getPassengerId();
		 String type=order.getType();
		 String [] explain=order.getExplain();
		 String reservation=order.getReservation();
		 int returnPrice=order.getReturnPrice();
		 String dAddress=order.getdAddress();
		 String contactPhone=order.getContactPhone();		 
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String sql ="insert into tule_order(user_id,status,traffic_date_arrange_id,totle_price,passenger_id,type,oexplain,reservation,return_price,distribution_address,contact_phone,time_close,state) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			connection = DBUtils.getConnection();
			statement = connection.prepareStatement(sql);
			statement.setInt(1, userId);
			statement.setString(2, status);
			statement.setInt(3, trafficDateArrangeId);
			statement.setString(4, connectArray(totlePrice));
			statement.setString(5, connectArray(passengerId));
			statement.setString(6, type);
			statement.setString(7, connectArray(explain));
			statement.setString(8, reservation);
			statement.setInt(9, returnPrice);
			statement.setString(10,dAddress);
			statement.setString(11,contactPhone);
			statement.setString(12, order.getTimeClose());
			statement.setString(13, "1");
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(connection, resultSet, statement);
		}
	 }
	 /**
	  * 拼接数组，用&连接,返回的是一个字符串
	  * @param obj
	  */
	 private String connectArray(Object [] obj)
	 {
		 StringBuffer stringBuffer=new StringBuffer();
		 for(int i=0;i<obj.length;i++)
		 {
			 stringBuffer.append(obj[i]);
			 if(i!=obj.length-1)
			 {
				 stringBuffer.append("&");
			 }		
		 }
		 return stringBuffer.toString();
	 }
	 /**
	  * 把字符串分割成字符串数组
	  * @param line
	  * @return
	  */
	 private Object getArrayA(String line)
	 {
		 Object [] lines=line.split("&");		 
		 return lines;
	 }
	 /**
	  * 把字符串分割成数字数组
	  * @param line
	  * @return
	  */
	 private Integer[] getArrayB(String line)
	 {
		 String [] lines=line.split("&");
		 Integer[] a=new Integer[lines.length];
		 for(int i=0;i<lines.length;i++)
		 {
			 a[i]=Integer.parseInt(lines[i]);
		 }
		 return a;
	 }
	 public static void main(String[] args) {
		OrderDao dao=new OrderDao();
		/*Order order=new Order();
		order.setUserId(1);
		order.setStatus("未付款");
		order.setTrafficDateArrangeId(1);
		String [] tp={"车票:100&保险:200"};
		order.setTotlePrice(tp);
		Integer a[]={123,456};
		order.setPassengerId(a);
		order.setType("火车");
		String []explain={"123456,卧铺,2:3:2","12345,卧铺,2:3:2"};
		order.setExplain(explain);
		order.setReservation("手机");
		order.setReturnPrice(0);
		order.setdAddress("南京");
		order.setContactPhone("110");
		OrderDao dao=new OrderDao();
		dao.addOrder(order);*/	
		List<Order> orders=dao.getOrderByUserId(1);
		for(Order order:orders)
		{
			System.out.println(order);
		}
		
	}
}
