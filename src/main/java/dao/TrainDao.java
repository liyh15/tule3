package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.management.RuntimeErrorException;
import com.sun.org.apache.xml.internal.security.algorithms.implementations.IntegrityHmac;
import entity.StopOverSation;
import entity.TrainArrange;
import entity.TrainSeat;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import spring.service.ex.DaoSystemException;

import java.sql.PreparedStatement;

/**
 * 关于火车票相关的数据库查询
 * 
 * @author tarena
 */
public class TrainDao {
	// 成功时的返回码
    private static int OK = 200;
    // 异常时的返回码
    private static int ERROR = 400;
	// 起始站
	private String start;
	// 终点站
	private String end;
	// 版本号
	private int version;
	// 更改后的查询的行程票数剩余情况
	private String exp;
	/**
	 * 通过起始站和终点站的名字和日期查找火车安排
	 * 
	 * @param startArea
	 *            起始站名字
	 * @param endArea
	 *            终点站名字
	 * @param date
	 *            日期
	 */
	public List<TrainArrange> findTrain(String startArea, String endArea, String date) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		ArrayList<TrainArrange> tArranges = new ArrayList<TrainArrange>();
		String sql = "select  * from tule_train_arrange ta join tule_train t on ta.train_id=t.id join tule_train_trip tt on ta.train_trip_id =tt.id "
				+ "join tule_train_date_arrange td on ta.id=td.train_arrange_id join "
				+ "tule_train_station tsa on ta.start_id=tsa.id join " + "tule_train_station tsb on ta.end_id=tsb.id   "
				+ "where ta.start_id in(select id from tule_train_station where city_id=(select id from tule_city where name like ?)) "
				+ "and ta.end_id in (select id from tule_train_station where city_id=(select id from tule_city where name like ?)) and td.day=?";
		try {
			connection = DBUtils.getConnection();
			statement = connection.prepareStatement(sql);
			if(startArea.contains("站")){
				startArea = startArea.substring(0,startArea.length()-1);
			}
			if(endArea.contains("站")){
				endArea = endArea.substring(0,endArea.length()-1);
			}
			statement.setString(1, "%"+startArea+"%");
			statement.setString(2, "%"+endArea+"%");
			statement.setString(3, date);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				String trainName = resultSet.getString("name");
				String day = resultSet.getString("day");
				int id = resultSet.getInt("td.id");
				String startTime = resultSet.getString("start_time");
				String endTime = resultSet.getString("end_time");
				String startStation = resultSet.getString("tsa.name");
				String endStation = resultSet.getString("tsb.name");
				String totalTime = resultSet.getString("t_time");
				ArrayList<TrainSeat> trainSeats = geTrainSeats(resultSet.getString("td.explain"));
				ArrayList<StopOverSation> stopOverSations = getStopOverStaions(resultSet.getString("tt.explain"));
				TrainArrange arrange = new TrainArrange();
				arrange.setTrainName(trainName);
				arrange.setDate(day);
				arrange.setId(id);
				arrange.setStartTime(startTime);
				arrange.setEndTime(endTime);
				arrange.setStartStation(startStation);
				arrange.setEndStation(endStation);
				arrange.setTotalTime(totalTime);
				arrange.setTrainSeats(trainSeats);
				arrange.setStopOverSations(stopOverSations);
				tArranges.add(arrange);
			}
			return tArranges;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(connection, resultSet, statement);
		}
		return null;
	}

	/**
	 * 通过火车安排日期来查找对应的火车名
	 * 
	 * @param id
	 */
	public String getTrainNameByDateId(int id) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String sql = "select ttsa.`name` sa,ttsb.`name` sb from tule_train_date_arrange tda join tule_train_arrange tta "
				+ "on tda.train_arrange_id=tta.id join tule_train_station ttsa on "
				+ "tta.start_id=ttsa.id join tule_train_station ttsb on " + "tta.end_id=ttsb.id where tda.id=?";
		try {
			connection = DBUtils.getConnection();
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				String one = resultSet.getString("sa");
				String two = resultSet.getString("sb");
				String c = one + "-" + two;
				return c;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(connection, resultSet, statement);
		}
		return null;
	}

	public static void main(String[] args) {
		TrainDao trainDao = new TrainDao();
		/*
		 * List<TrainArrange> trainArranges=trainDao.findTrain("宿州", "南京",
		 * "2018-10-18"); for(TrainArrange trainArrange:trainArranges) {
		 * System.out.println(trainArrange.getTrainSeats().get(2).getCount()); }
		 */
		trainDao.getSeatArrange(3, 1);
	}

	/**
	 * 通过火车日期安排编号和座位类型来返回座位号
	 * 
	 * @param trainDateId
	 *            火车日期安排编号
	 * @param type
	 *            座位类型下标
	 * @return 返回座位号
	 */
	public String getSeatArrange(int trainDateId, int type) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String sql = "select `explain`,group_id,version" + type + " from tule_train_date_arrange where id=?";
		try {
			connection = DBUtils.getConnection();
			// 开启事务
			connection.setAutoCommit(false);
			// 设置事务的隔离等级为读已提交
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			statement = connection.prepareStatement(sql);
			statement.setInt(1, trainDateId);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				String explain = resultSet.getString("explain");
				int groupId = resultSet.getShort("group_id");
				// 读取当前座位所对应的版本号
				version = resultSet.getInt("version" + type);
				JSONArray jsonArray = JSONArray.fromObject(explain);
				ArrayList<TrainSeat> trainSeatss = (ArrayList<TrainSeat>) jsonArray.toList(jsonArray, TrainSeat.class);
				TrainSeat trainSeat = trainSeatss.get(type);
				// 获得座位号
				String seat = trainSeat.findSeat();
				if(seat != null){
					// 如果还有座位，修改沿线的火车安排的座位情况
					reduceAllTrainArrangeA(trainDateId, type, seat, groupId, connection);
				}	
				// 返回座位号
				return seat;
			}
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			DBUtils.close(connection, resultSet, statement);
		}
		return getSeatArrange(trainDateId,type);
	}

	/**
	 * 通过这个方法获得起始站名称，终点站名称和沿途停靠站集合
	 * 
	 * @param id
	 *            火车日期安排编号
	 * @param type
	 *            座位类型下标
	 * @param seat
	 *            已购买的座位号
	 * @param groupId
	 *            火车日期安排组编号
	 * @throws Exception 
	 */
	private void reduceAllTrainArrangeA(int id, int type, String seat, int groupId, Connection connection)
			throws Exception {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String sql = "select * from tule_train_date_arrange tda join tule_train_arrange tta  "
				+ "on tda.train_arrange_id=tta.id join tule_train_station ttsa on "
				+ "tta.start_id=ttsa.id join tule_train_station ttsb on "
				+ "tta.end_id=ttsb.id  join tule_train_trip ttt on tta.train_trip_id=ttt.id where tda.id=?";
		statement = connection.prepareStatement(sql);
		statement.setInt(1, id);
		resultSet = statement.executeQuery();
		while (resultSet.next()) {
			String oneStation = resultSet.getString("ttsa.name");
			String twoStation = resultSet.getString("ttsb.name");
			start = oneStation;
			end = twoStation;
			String explain = resultSet.getString("ttt.explain");
			JSONArray jsonArray = JSONArray.fromObject(explain);
			ArrayList<StopOverSation> sation = (ArrayList<StopOverSation>) jsonArray.toList(jsonArray,
					StopOverSation.class);
			// 查找到对应的起始站和终点站还有经停站集合
			reduceAllTrainArrangeB(oneStation, twoStation, type, sation, seat, groupId, connection);
		}
	}

	/**
	 * 获得需要更新的火车日期安排的班次，获得起始站和终点站是为了通过这两个来修改沿线的车票情况
	 * 
	 * @param one
	 *            购买的起始站名称
	 * @param two
	 *            购买的终点站名称
	 * @param type
	 *            座位类型编号
	 * @param sation
	 *            沿途停靠信息集合
	 * @param seat
	 *            已购买的座位编号
	 * @param groupId
	 *            火车日期安排组编号
	 * @throws Exception 
	 */
	private void reduceAllTrainArrangeB(String one, String two, int type, ArrayList<StopOverSation> sation, String seat,
			int groupId, Connection connection) throws Exception {
		int second = 0; // 起始站开始，第二个车站的下标
		int last = 0; // 终点站的下标
		for (int i = 0; i < sation.size(); i++) {
			/*
			 * 获得这两个下标
			 */
			if (sation.get(i).getStation().equals(one)) {
				second = i + 1;
			}
			if (sation.get(i).getStation().equals(two)) {
				last = i;
			}
		}
		for (int i = 0; i < last; i++) {

			if (i < second) {
				for (int j = second; j < sation.size(); j++) {
					reduceAllTrainArrangeC(sation.get(i).getStation(), sation.get(j).getStation(), seat, groupId, type,
							connection);
				}
			}
			if (i >= second && i != last) {
				for (int j = i + 1; j < sation.size(); j++) {
					reduceAllTrainArrangeC(sation.get(i).getStation(), sation.get(j).getStation(), seat, groupId, type,
							connection);
				}
			}
		}
		// 最后更新购买的班次，然后通过version判断是否可以提交
		updateTrainSeatFirst(start, end, groupId, exp, connection, type);
		// 提交事务
		connection.commit();
		// 恢复数据库为自动提交
		connection.setAutoCommit(true);
	}

	/**
	 * 通过这个函数获得需要更新的火车日期安排的座位剩余情况(explain)
	 * 
	 * @param one
	 *            更新的起始站名称
	 * @param two
	 *            更新的终点站名称
	 * @param seat
	 *            购买的座位编号(0:1:1)
	 * @param groupId
	 *            火车安排组编号
	 * @param type
	 *            座位类型编号
	 * @throws SQLException
	 */
	private void reduceAllTrainArrangeC(String one, String two, String seat, int groupId, int type,
			Connection connection) throws SQLException {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String sql = "select * from tule_train_date_arrange tda join tule_train_arrange tta  on "
				+ "tda.train_arrange_id=tta.id join tule_train_station ttsa on "
				+ "tta.start_id=ttsa.id join tule_train_station ttsb on "
				+ "tta.end_id=ttsb.id  where tda.group_id=? and ttsa.`name`=? and ttsb.`name`=?;";
		statement = connection.prepareStatement(sql);
		statement.setInt(1, groupId);
		statement.setString(2, one);
		statement.setString(3, two);
		resultSet = statement.executeQuery();
		while (resultSet.next()) {
			String explain = resultSet.getString("tda.explain");
			int arrangeId = resultSet.getInt("tda.train_arrange_id");
			JSONArray jsonArray = JSONArray.fromObject(explain);
			// 获得每种座位的剩余情况集合
			ArrayList<TrainSeat> trainSeatss = (ArrayList<TrainSeat>) jsonArray.toList(jsonArray, TrainSeat.class);
			// 获得所指定的座位实体类
			TrainSeat trainSeat = trainSeatss.get(type);
			// 将剩余座位的个数减1
			trainSeat.reduceSeat(seat);
			// 将座位集合重新打包成json对象
			explain = JSONArray.fromObject(trainSeatss).toString();
			// 更新到对应的数据库中
			if(one.equals(start) && two.equals(end)){
				// 当遍历到所购买的行程时
				exp=explain;
			}else{
				updateTrainSeat(one, two, groupId, explain, connection,type);
			}
		}		
	}

	/**
	 * 当购买了一趟火车安排之后，更新沿线各火车安排的座位
	 * 
	 * @param one
	 *            起始站名称
	 * @param two
	 *            终点站名称
	 * @param groupId
	 *            火车安排组编号
	 * @param explain
	 *            火车安排座位情况json字符串
	 * @throws SQLException
	 */
	private void updateTrainSeat(String one, String two, int groupId, String explain, Connection connection,int type)
			throws SQLException {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		String sql = "update tule_train_date_arrange set `explain`=?,version"+type+"=version"+type+"+1 where group_id=? and train_arrange_id="
				+ "(select id from tule_train_arrange where start_id="
				+ "(select id from tule_train_station where name=?) and end_id="
				+ "(select id from tule_train_station where name=?));";
		statement = connection.prepareStatement(sql);
		statement.setString(1, explain);
		statement.setInt(2, groupId);
		statement.setString(3, one);
		statement.setString(4, two);
		statement.executeUpdate();
	}

	/**
	 * 更新购买的行程的座位剩余，用于进行事务的控制
	 * 
	 * @param one
	 *            起始站名称
	 * @param two
	 *            终点站名称
	 * @param groupId
	 *            火车安排组编号
	 * @param explain
	 *            火车安排座位情况json字符串
	 * @throws Exception 
	 */
	private void updateTrainSeatFirst(String one, String two, int groupId, String explain, Connection connection,int type)
			throws Exception {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		String sql = "update tule_train_date_arrange set `explain`=?,version"+type+"=version"+type+"+1 where group_id=? and train_arrange_id="
				+ "(select id from tule_train_arrange where start_id="
				+ "(select id from tule_train_station where name=?) and version"+type+"="+version+" and end_id="
				+ "(select id from tule_train_station where name=?));";
		statement = connection.prepareStatement(sql);
		statement.setString(1, explain);
		statement.setInt(2, groupId);
		statement.setString(3, one);
		statement.setString(4, two);
		// 获取受影响行数，如果为零则表示事务执行行已被更改，执行回滚
		int rows=statement.executeUpdate();
		if(rows == 0){
			connection.rollback();
			throw new DaoSystemException("事务遭回滚");
		}
	}
	
	/**
	 * 返回座位类型及剩余情况的集合
	 * 
	 * @param line
	 *            传入的json字符串
	 * @return
	 */
	private ArrayList<TrainSeat> geTrainSeats(String line) {
		JSONArray jsonArray = JSONArray.fromObject(line);
		ArrayList<TrainSeat> trainSeats = (ArrayList<TrainSeat>) jsonArray.toList(jsonArray, TrainSeat.class);
		return trainSeats;
	}

	
	/**
	 * 返回经停站
	 * 
	 * @param line
	 *            经停站json集合字符串
	 * @return
	 */
	private ArrayList<StopOverSation> getStopOverStaions(String line) {
		JSONArray jsonArray = JSONArray.fromObject(line);
		ArrayList<StopOverSation> stopOverSations = (ArrayList<StopOverSation>) jsonArray.toList(jsonArray,
				StopOverSation.class);
		
		return stopOverSations;
	}
	
	/**
	 * 取消已预订的座位(退票，取消订单)
	 * @param trainDateId 火车日期安排编号
	 * @param seat 需要退的座位位置
	 * @param type 对应的车票类型
	 */
	public Integer returnTrainTicket(int trainDateId,String seat,int type){
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String sql = "select `explain`,group_id,version" + type + " from tule_train_date_arrange where id=?";
		try {
			connection = DBUtils.getConnection();
			// 开启事务
			connection.setAutoCommit(false);
			// 设置事务的隔离等级为读已提交
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			statement = connection.prepareStatement(sql);
			statement.setInt(1, trainDateId);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				String explain = resultSet.getString("explain");
				int groupId = resultSet.getShort("group_id");
				// 读取当前座位所对应的版本号
				version = resultSet.getInt("version" + type);
				JSONArray jsonArray = JSONArray.fromObject(explain);
				ArrayList<TrainSeat> trainSeatss = (ArrayList<TrainSeat>) jsonArray.toList(jsonArray, TrainSeat.class);			
				TrainSeat trainSeat = trainSeatss.get(type);
				// 空出指定的座位
				trainSeat.returnSeat(seat);
				// 如果还有座位，修改沿线的火车安排的座位情况
				returnAllTrainArrangeA(trainDateId, type, seat, groupId, connection);
				return OK;	
			}
			return ERROR;
		} catch (Exception e) {
            e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			if(e instanceof DaoSystemException){
				return returnTrainTicket(trainDateId,seat,type);
			} else {
				return ERROR;
			}
		} finally {
			DBUtils.close(connection, resultSet, statement);
		}	
	}
	
	/**
	 * 空出所有中途行程车次对应的车位
	 * 
	 * @param id
	 *            火车日期安排编号
	 * @param type
	 *            座位类型下标
	 * @param seat
	 *            需要空出的座位位置
	 * @param groupId
	 *            火车日期安排组编号
	 * @throws Exception 
	 */
	private void returnAllTrainArrangeA(int id, int type, String seat, int groupId, Connection connection)
			throws Exception {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String sql = "select * from tule_train_date_arrange tda join tule_train_arrange tta  "
				+ "on tda.train_arrange_id=tta.id join tule_train_station ttsa on "
				+ "tta.start_id=ttsa.id join tule_train_station ttsb on "
				+ "tta.end_id=ttsb.id  join tule_train_trip ttt on tta.train_trip_id=ttt.id where tda.id=?";
		statement = connection.prepareStatement(sql);
		statement.setInt(1, id);
		resultSet = statement.executeQuery();
		while (resultSet.next()) {
			String oneStation = resultSet.getString("ttsa.name");
			String twoStation = resultSet.getString("ttsb.name");
			start = oneStation;
			end = twoStation;
			String explain = resultSet.getString("ttt.explain");
			JSONArray jsonArray = JSONArray.fromObject(explain);
			ArrayList<StopOverSation> sation = (ArrayList<StopOverSation>) jsonArray.toList(jsonArray,
					StopOverSation.class);
			// 查找到对应的起始站和终点站还有经停站集合
			returnAllTrainArrangeB(oneStation, twoStation, type, sation, seat, groupId, connection);
		}
	}
	
	/**
	 * 获得需要空出的火车日期安排的班次，获得起始站和终点站是为了通过这两个来修改沿线的车票情况
	 * 
	 * @param one
	 *            购买的起始站名称
	 * @param two
	 *            购买的终点站名称
	 * @param type
	 *            座位类型编号
	 * @param sation
	 *            列车沿途停靠信息集合
	 * @param seat
	 *            已购买的座位编号
	 * @param groupId
	 *            火车日期安排组编号
	 * @throws Exception 
	 */
	private void returnAllTrainArrangeB(String one, String two, int type, ArrayList<StopOverSation> sation, String seat,
			int groupId, Connection connection) throws Exception {
		int second = 0; // 起始站开始，第二个车站的下标
		int last = 0; // 终点站的下标
		for (int i = 0; i < sation.size(); i++) {
			/*
			 * 获得这两个下标
			 */
			if (sation.get(i).getStation().equals(one)) {
				second = i + 1;
			}
			if (sation.get(i).getStation().equals(two)) {
				last = i;
			}
		}
		for (int i = 0; i < last; i++) {

			if (i < second) {
				for (int j = second; j < sation.size(); j++) {
					returnAllTrainArrangeC(sation.get(i).getStation(), sation.get(j).getStation(), seat, groupId, type,
							connection);
				}
			}
			if (i >= second && i != last) {
				for (int j = i + 1; j < sation.size(); j++) {
					returnAllTrainArrangeC(sation.get(i).getStation(), sation.get(j).getStation(), seat, groupId, type,
							connection);
				}
			}
		}
		// 最后更新购买的班次，然后通过version判断是否可以提交
		updateTrainSeatFirst(start, end, groupId, exp, connection, type);
		// 提交事务
		connection.commit();
		// 恢复数据库为自动提交
		connection.setAutoCommit(true);
	}
	
	/**
	 * 通过这个函数获得需要更新的火车日期安排的座位剩余情况(explain)
	 * 
	 * @param one
	 *            更新的起始站名称
	 * @param two
	 *            更新的终点站名称
	 * @param seat
	 *            购买的座位编号(0:1:1)
	 * @param groupId
	 *            火车安排组编号
	 * @param type
	 *            座位类型编号
	 * @throws SQLException
	 */
	private void returnAllTrainArrangeC(String one, String two, String seat, int groupId, int type,
			Connection connection) throws SQLException {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String sql = "select * from tule_train_date_arrange tda join tule_train_arrange tta  on "
				+ "tda.train_arrange_id=tta.id join tule_train_station ttsa on "
				+ "tta.start_id=ttsa.id join tule_train_station ttsb on "
				+ "tta.end_id=ttsb.id  where tda.group_id=? and ttsa.`name`=? and ttsb.`name`=?;";
		statement = connection.prepareStatement(sql);
		statement.setInt(1, groupId);
		statement.setString(2, one);
		statement.setString(3, two);
		resultSet = statement.executeQuery();
		while (resultSet.next()) {
			String explain = resultSet.getString("tda.explain");
			int arrangeId = resultSet.getInt("tda.train_arrange_id");
			JSONArray jsonArray = JSONArray.fromObject(explain);
			// 获得每种座位的剩余情况集合
			ArrayList<TrainSeat> trainSeatss = (ArrayList<TrainSeat>) jsonArray.toList(jsonArray, TrainSeat.class);
			// 获得所指定的座位实体类
			TrainSeat trainSeat = trainSeatss.get(type);
			// 将指定座位空出来
			trainSeat.returnSeat(seat);
			// 将座位集合重新打包成json对象
			explain = JSONArray.fromObject(trainSeatss).toString();
			// 更新到对应的数据库中
			if(one.equals(start) && two.equals(end)){
				// 当遍历到所购买的行程时
				exp=explain;
			}else{
				updateTrainSeat(one, two, groupId, explain, connection,type);
			}
		}		
	}
}
