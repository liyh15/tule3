package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;
import org.springframework.util.DigestUtils;
import entity.User;
/**
 * �����û�����ز�ѯ
 * @author tarena
 */
public class UserDao {	
	
	/**
	 * �����û���������֤
	 * @param username �������ֻ��ź�����
	 * @param password ������֤������
	 * @return 1����¼�ɹ� 2���˺Ų����� 3�����벻��ȷ
	 */
	public int login(String username, String password)
	{
		
		User user=getUser(username);
		if(user==null)
		{
			return 2;
		}
		else if(!getMd5Password(password, user.getSalt()).equals(user.getPassword()))
		{
			return 3;
		}
		else {
			return 1;
		}
	}
	/**
	 * �û�ע�᷽��
	 * @param username �û���(Ĭ��Ϊ�ֻ���)
	 * @param password ����
	 * �˷���Ϊ��֤ͨ����ִ��(true��ע��ɹ� false:�û��Ѵ���)
	 */
	public boolean register(String username,String password)
	{
		if(userPhoneIsNotExists(username))
		{
			Connection connection = null;
			PreparedStatement statement = null;
			ResultSet resultSet = null;
			String salt=getSalt();
			String pass=getMd5Password(password, salt);
			String sql ="insert into tule_user(phone,password,salt) values(?,?,?)";
			try {
				connection = DBUtils.getConnection();
				statement = connection.prepareStatement(sql);
				statement.setString(1, username);
				statement.setString(2, pass);
				statement.setString(3, salt);
				statement.executeUpdate();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBUtils.close(connection, resultSet, statement);
			}
		}		
		return false;
	}	
      
	/**
	 * ��ȡ�������ֵ
	 * @return
	 */
	private String getSalt()
	{
		return UUID.randomUUID().toString().toUpperCase();
	}
	
	/**
	 * ��ȡ���ܺ������
	 * @param password ԭ����
	 * @param salt ��ֵ
	 * @return
	 */
	private String getMd5Password(String password,String salt)
	{
		String one=md5(password);
		String two=md5(salt);
		String three=one+two;
		for(int i=0;i<5;i++)
		{
			three=md5(three);
		}
		return three;
	}
	
	/**
	 * ���ַ�������md5����
	 * @param str
	 * @return
	 */
	private String md5(String str)
	{
		return DigestUtils.md5DigestAsHex(str.getBytes()).toUpperCase();
	}
	/**
	 * ����ע��ʱ�ж�ԭ���Ǹ����Ƿ����
	 * username:Ĭ��Ϊ�û����ֻ���
	 * true:������ false:����
	 */
	public boolean userPhoneIsNotExists(String username)
	{
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String sql = "select count(*) c from tule_user where phone=?";
		try {
			connection = DBUtils.getConnection();
			statement = connection.prepareStatement(sql);
			statement.setString(1, username);
			resultSet=statement.executeQuery();
			while(resultSet.next())
			{
				int count=resultSet.getInt("c");
				if(count==0)
				{
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(connection, resultSet, statement);
		}
		return false;
	}

	/**
	 * �ж��û������Ƿ��Ѵ���
	 * @param email
	 * @return  true:������ false������
	 * 
	 */
	public boolean userEmailIsNotExists(String email)
	{
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String sql = "select count(*) c from tule_user where email=?";
		try {
			connection = DBUtils.getConnection();
			statement = connection.prepareStatement(sql);
			statement.setString(1, email);
			resultSet=statement.executeQuery();
			while(resultSet.next())
			{
				int count=resultSet.getInt("c");
				if(count==0)
				{
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(connection, resultSet, statement);
		}
		return false;
	}

	/**
	 * ��ȡָ��phone���û���Ϣ
	 * @return ����һ���û���ʵ����
	 */
	public User getUser(String username)
	{
		CityDao cdao=new CityDao();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String sql = "select * from tule_user where phone=?";
		try {
			connection = DBUtils.getConnection();
			statement = connection.prepareStatement(sql);
			statement.setString(1, username);
			resultSet=statement.executeQuery();
			User user=new User();			
			while(resultSet.next())
			{
				int id=resultSet.getInt("id");
				String nickName=resultSet.getString("nickname");
				String name=resultSet.getString("name");
				String phone=resultSet.getString("phone");
				String birthday=resultSet.getString("birthday");
				String email=resultSet.getString("email");
				String liveCity=cdao.getCityNameById(resultSet.getInt("live_city"));
				String address=resultSet.getString("address");
				String married=resultSet.getString("married");
				String job=resultSet.getString("job");
				String education=resultSet.getString("education");
				String password=resultSet.getString("password");
				String passQuestion=resultSet.getString("pass_question");
				String personalId=resultSet.getString("personal_id");
				String paperType=resultSet.getString("paper_type");
				String sex=resultSet.getString("sex");
				String salt=resultSet.getString("salt");
				int money = resultSet.getInt("money");
				user.setId(id);
				user.setNickName(nickName);
				user.setName(name);
				user.setPhone(phone);
				user.setBirthday(birthday);
				user.setEmail(email);
				user.setLiveCity(liveCity);
				user.setAddress(address);
				user.setMarried(married);
				user.setJob(job);
				user.setEducation(education);
				user.setPassword(password);
				user.setPassQuestion(passQuestion);
				user.setPersonalId(personalId);
				user.setPaperType(paperType);
				user.setSex(sex);
				user.setSalt(salt);
				user.setMoney(money);
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(connection, resultSet, statement);
		}
		return null;
	}

	/**
	 * �û��Ļ�����Ϣ����,������������ܱ�����
	 * �������һ���µ�ʵ����
	 */
	public boolean basicSetUp(User user)
	{
		CityDao cdao=new CityDao();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String sql = "update tule_user set nickname=?,name=?,phone=?,"
				+ "birthday=?,sex=?,email=?,live_city=?,address=?,married=?,job=?,education=? where id=?";
		try {
			connection = DBUtils.getConnection();
			statement = connection.prepareStatement(sql);
			statement.setString(1, user.getNickName());
			statement.setString(2, user.getName());
			statement.setString(3, user.getPhone());
			statement.setString(4, user.getBirthday());
			statement.setString(5, user.getSex());
			statement.setString(6, user.getEmail());
			statement.setInt(7, cdao.getCityIdByName(user.getLiveCity()));
			statement.setString(8, user.getAddress());
			statement.setString(9, user.getMarried());
			statement.setString(10, user.getJob());
			statement.setString(11, user.getEducation());
			statement.setInt(12, user.getId());
			statement.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(connection, resultSet, statement);
		}
		return false;
	}

	/**
	 * @param id �˻��ı��
	 * �޸���������
	 * username:Ĭ��Ϊ�û����ֻ���
	 * @param oldPass ��ʼ����
	 * @param newPass ������
	 * @return �����Ƿ��޸ĳɹ�(true:ԭ������ȷ false:ԭ���벻��ȷ)
	 */
	public boolean changePass(String username,String oldPass,String newPass)
	{	
		/*if(login(username, oldPass))
		{
			Connection connection = null;
			PreparedStatement statement = null;
			ResultSet resultSet = null;
			String sql ="update tule_user set password=? where phone=? and password=?";
			try {
				connection = DBUtils.getConnection();
				statement = connection.prepareStatement(sql);
				statement.setString(1, newPass);			
				statement.setString(2, username);
				statement.setString(3, oldPass);
				statement.executeUpdate();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBUtils.close(connection, resultSet, statement);
			}
		}
		return false;*/
		return false;
	}

	/**
	 * �޸��ܱ�����
	 * @param passQuestionJson �µ��ܱ������json�ַ���
	 * @username Ĭ��Ϊ�û����ֻ���
	 */
	public boolean changePassQuestion(String username,String passQuestionJson)
	{
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String sql = "update tule_user set pass_question=? where phone=?";
		try {
			connection = DBUtils.getConnection();
			statement = connection.prepareStatement(sql);
			statement.setString(1, passQuestionJson);
			statement.setString(2, username);
			statement.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(connection, resultSet, statement);
		}
		return false;
	}
	
	public static void main(String[] args) {
		UserDao dao=new UserDao();
		System.out.println(dao.changePassQuestion("15720786592", "asdasdasdas"));	
	}
}
