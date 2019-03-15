package dao;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import org.apache.commons.dbcp.BasicDataSource;
import java.sql.Connection;
import java.sql.ResultSet;
public class DBUtils {
	private static String username=null;
	private static String url=null;
	private static String root=null;
	private static String password=null;
	private static BasicDataSource dataSource=null;
	static
	{
		Properties properties=new Properties();
		//在properties文件中字符串不用双引号括起来
		dataSource=new BasicDataSource();
		dataSource.setMaxActive(15);
		InputStream inputStream=DBUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
		try {
			properties.load(inputStream);
			username=properties.getProperty("username");
			url=properties.getProperty("url");
			root=properties.getProperty("root");
			password=properties.getProperty("password");
			dataSource.setUsername(root);
			dataSource.setUrl(url);
			dataSource.setPassword(password);
			dataSource.setDriverClassName(username);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}    	 
	} 

	public static Connection getConnection() throws SQLException
	{
		return dataSource.getConnection();
	}
	public static void main(String[] args) throws SQLException {
		System.out.println(DBUtils.getConnection());
	}
	public static void close(Connection connection,ResultSet resultSet,Statement statement)
	{

		//利用return可以终止执行，这个很少用到，但应该非常实用
		if(connection!=null)
		{
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(resultSet!=null)
		{
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(statement!=null)
		{
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
