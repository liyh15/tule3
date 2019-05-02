package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.sun.crypto.provider.RSACipher;

import entity.Passenger;

/**
 * ���ڳ˿͵����ݿ����
 * 
 * @author ��Ԫ��
 */
public class PassengerDao {
	/**
	 * ����û�ע��ĳ˿�
	 * @param userId
	 * @return
	 */
	public List<Passenger> getPassenger(int userId) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		ArrayList<Passenger> passengers = new ArrayList<Passenger>();
		String sql = "select * from tule_passenger where user_id=?";
		try {
			connection = DBUtils.getConnection();
			statement = connection.prepareStatement(sql);
			statement.setInt(1, userId);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String personalId = resultSet.getString("personal_id");
				String type = resultSet.getString("type");
				Passenger passenger = new Passenger();
				passenger.setId(id);
				passenger.setName(name);
				passenger.setPersonalId(personalId);
				passenger.setType(type);
				passenger.setUserId(userId);
				passengers.add(passenger);
				return passengers;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(connection, resultSet, statement);
		}
		return null;
	}

	/**
	 * ���û���ӳ˿�
	 * 
	 * @param list�˿͵ļ���
	 */
	public void addPassenger(List<Passenger> list) {
		
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String sql = "insert into tule_passenger(name,personal_id,type,user_id) values(?,?,?,?)";
		try {
			connection = DBUtils.getConnection();
			statement = connection.prepareStatement(sql);
			for(int i=0;i<list.size();i++)
			{
				Passenger passenger=list.get(i);
				if(isPassagenerExist(passenger.getPersonalId(), passenger.getType(),passenger.getUserId()))
				{
					//�ж��û��Ƿ����
					statement.setString(1, passenger.getName());
					statement.setString(2, passenger.getPersonalId());
					statement.setString(3, passenger.getType());				
					statement.setInt(4, passenger.getUserId());	
					statement.addBatch();
				}				
			}
			statement.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(connection, resultSet, statement);
		}
	}
	/**
	 * ��ȡ�˿���ѡ��ĳ˿ͱ������
	 * @param userId
	 * @return
	 */
	public Integer[] getPassengerIdByPassNumber(int userId,String [] numbers)
	{
		Integer [] id=new Integer[numbers.length];
		List<String> numList=Arrays.asList(numbers);
		
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String sql ="select * from tule_passenger where user_id=?";
		try {
			connection = DBUtils.getConnection();
			statement = connection.prepareStatement(sql);
			statement.setInt(1, userId);
			resultSet=statement.executeQuery();
			int count=0;
			while(resultSet.next())
			{
				String num=resultSet.getString("personal_id");
				if(numList.contains(num))
				{
					id[count]=resultSet.getInt("id");
					count++;
				}
				else
				{
					
				}
			}
			return id;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(connection, resultSet, statement);
		}
		return null;
		
	}
	/**
	 * �ж��û��Ƿ����
	 * @return
	 */
    private boolean isPassagenerExist(String passId,String type,int userId)
    {
    	Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String sql = "select * from tule_passenger where personal_id=? and type=? and user_id=?";
		try {
			connection = DBUtils.getConnection();
			statement = connection.prepareStatement(sql);
			statement.setString(1, passId);
			statement.setString(2, type);
			statement.setLong(3, userId);
			resultSet=statement.executeQuery();
			while(resultSet.next())
			{
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(connection, resultSet, statement);
		}
    	return true;
    }
    
    /**
     * �жϳ˿������Ƿ���ȷ
     * �����ѯ��������ж��û��˿������Ƿ����
     * @return
     * @throws Exception 
     */
    public boolean judgeIsCorrect(Passenger passenger) throws Exception {
    	
    	Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String sql = "select * from tule_citizen where personal_id = ? and name = ? and paper_type = ?";
		try {
			connection = DBUtils.getConnection();
			statement = connection.prepareStatement(sql);
			statement.setString(1, passenger.getPersonalId());
			statement.setString(2, passenger.getName());
			statement.setString(3, passenger.getType());
			resultSet=statement.executeQuery();
			while(resultSet.next())
			{
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(connection, resultSet, statement);
		}
    	throw new Exception("�û���Ϣ����ȷ");
    }
    
	public static void main(String[] args) {
		PassengerDao dao = new PassengerDao();
		/*ArrayList<Passenger> passengers=new ArrayList<Passenger>();
		Passenger passenger=new Passenger();
		passenger.setName("lyh");
		passenger.setPersonalId("123456");
		passenger.setType("���֤");
		passenger.setUserId(1);
		Passenger passenger2=new Passenger();
		passenger2.setName("lyh");
		passenger2.setPersonalId("123456");
		passenger2.setType("���֤");
		passenger2.setUserId(1);
		passengers.add(passenger);
		passengers.add(passenger2);
		dao.addPassenger(passengers);	*/	
		//System.out.println(dao.isPassagenerExist(321081197, "���֤",3));
	}
}
