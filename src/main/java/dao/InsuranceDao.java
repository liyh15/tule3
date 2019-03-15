package dao;
import entity.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
/**
 * 关于保险的数据库查询
 * @author 李元浩
 */
public class InsuranceDao {
	/**
	 * 查询指定交通类型的保险
	 * @param type 火车 飞机 汽车
	 * @return
	 */
     public List<Insurance> findAllInsurance(String type)
     {
    	Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		ArrayList<Insurance> insurances=new ArrayList<Insurance>();
		String sql = "select * from tule_insurance where type=? ";
		try {
			connection = DBUtils.getConnection();
			statement = connection.prepareStatement(sql);
			statement.setString(1, type);
			resultSet=statement.executeQuery();
			while(resultSet.next())
			{
				int id=resultSet.getInt("id");
				String name=resultSet.getString("name");
				int price=resultSet.getInt("price");
				int comId=resultSet.getInt("cmp_id");
				Insurance insurance=new Insurance();
				insurance.setId(id);
				insurance.setName(name);
				insurance.setComId(comId);
				insurance.setPrice(price);
				insurance.setType(type);
				insurances.add(insurance);
			}
			return insurances;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(connection, resultSet, statement);
		}
    	 return null;
     }
     /**
      * 生成保险订单
      * @param passengers 乘客的集合
      * insurance 保险的类型
      */
     public void buildInsuranceOrder(List<Passenger> passengers,Insurance insurance,Order order,String data)
     {
    	 int comId=insurance.getComId();
    	 int price=insurance.getPrice();
    	 
     }
     public static void main(String[] args) {
		InsuranceDao dao=new InsuranceDao();
		System.out.println(dao.findAllInsurance("火车").get(0).getName());
	}
}
