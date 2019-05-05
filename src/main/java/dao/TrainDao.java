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
 * ���ڻ�Ʊ��ص����ݿ��ѯ
 * 
 * @author tarena
 */
public class TrainDao {
	// �ɹ�ʱ�ķ�����
    private static int OK = 200;
    // �쳣ʱ�ķ�����
    private static int ERROR = 400;
	// ��ʼվ
	private String start;
	// �յ�վ
	private String end;
	// �汾��
	private int version;
	// ���ĺ�Ĳ�ѯ���г�Ʊ��ʣ�����
	private String exp;
	/**
	 * ͨ����ʼվ���յ�վ�����ֺ����ڲ��һ𳵰���
	 * 
	 * @param startArea
	 *            ��ʼվ����
	 * @param endArea
	 *            �յ�վ����
	 * @param date
	 *            ����
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
			if(startArea.contains("վ")){
				startArea = startArea.substring(0,startArea.length()-1);
			}
			if(endArea.contains("վ")){
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
	 * ͨ���𳵰������������Ҷ�Ӧ�Ļ���
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
		 * List<TrainArrange> trainArranges=trainDao.findTrain("����", "�Ͼ�",
		 * "2018-10-18"); for(TrainArrange trainArrange:trainArranges) {
		 * System.out.println(trainArrange.getTrainSeats().get(2).getCount()); }
		 */
		trainDao.getSeatArrange(3, 1);
	}

	/**
	 * ͨ�������ڰ��ű�ź���λ������������λ��
	 * 
	 * @param trainDateId
	 *            �����ڰ��ű��
	 * @param type
	 *            ��λ�����±�
	 * @return ������λ��
	 */
	public String getSeatArrange(int trainDateId, int type) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String sql = "select `explain`,group_id,version" + type + " from tule_train_date_arrange where id=?";
		try {
			connection = DBUtils.getConnection();
			// ��������
			connection.setAutoCommit(false);
			// ��������ĸ���ȼ�Ϊ�����ύ
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			statement = connection.prepareStatement(sql);
			statement.setInt(1, trainDateId);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				String explain = resultSet.getString("explain");
				int groupId = resultSet.getShort("group_id");
				// ��ȡ��ǰ��λ����Ӧ�İ汾��
				version = resultSet.getInt("version" + type);
				JSONArray jsonArray = JSONArray.fromObject(explain);
				ArrayList<TrainSeat> trainSeatss = (ArrayList<TrainSeat>) jsonArray.toList(jsonArray, TrainSeat.class);
				TrainSeat trainSeat = trainSeatss.get(type);
				// �����λ��
				String seat = trainSeat.findSeat();
				if(seat != null){
					// ���������λ���޸����ߵĻ𳵰��ŵ���λ���
					reduceAllTrainArrangeA(trainDateId, type, seat, groupId, connection);
				}	
				// ������λ��
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
	 * ͨ��������������ʼվ���ƣ��յ�վ���ƺ���;ͣ��վ����
	 * 
	 * @param id
	 *            �����ڰ��ű��
	 * @param type
	 *            ��λ�����±�
	 * @param seat
	 *            �ѹ������λ��
	 * @param groupId
	 *            �����ڰ�������
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
			// ���ҵ���Ӧ����ʼվ���յ�վ���о�ͣվ����
			reduceAllTrainArrangeB(oneStation, twoStation, type, sation, seat, groupId, connection);
		}
	}

	/**
	 * �����Ҫ���µĻ����ڰ��ŵİ�Σ������ʼվ���յ�վ��Ϊ��ͨ�����������޸����ߵĳ�Ʊ���
	 * 
	 * @param one
	 *            �������ʼվ����
	 * @param two
	 *            ������յ�վ����
	 * @param type
	 *            ��λ���ͱ��
	 * @param sation
	 *            ��;ͣ����Ϣ����
	 * @param seat
	 *            �ѹ������λ���
	 * @param groupId
	 *            �����ڰ�������
	 * @throws Exception 
	 */
	private void reduceAllTrainArrangeB(String one, String two, int type, ArrayList<StopOverSation> sation, String seat,
			int groupId, Connection connection) throws Exception {
		int second = 0; // ��ʼվ��ʼ���ڶ�����վ���±�
		int last = 0; // �յ�վ���±�
		for (int i = 0; i < sation.size(); i++) {
			/*
			 * ����������±�
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
		// �����¹���İ�Σ�Ȼ��ͨ��version�ж��Ƿ�����ύ
		updateTrainSeatFirst(start, end, groupId, exp, connection, type);
		// �ύ����
		connection.commit();
		// �ָ����ݿ�Ϊ�Զ��ύ
		connection.setAutoCommit(true);
	}

	/**
	 * ͨ��������������Ҫ���µĻ����ڰ��ŵ���λʣ�����(explain)
	 * 
	 * @param one
	 *            ���µ���ʼվ����
	 * @param two
	 *            ���µ��յ�վ����
	 * @param seat
	 *            �������λ���(0:1:1)
	 * @param groupId
	 *            �𳵰�������
	 * @param type
	 *            ��λ���ͱ��
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
			// ���ÿ����λ��ʣ���������
			ArrayList<TrainSeat> trainSeatss = (ArrayList<TrainSeat>) jsonArray.toList(jsonArray, TrainSeat.class);
			// �����ָ������λʵ����
			TrainSeat trainSeat = trainSeatss.get(type);
			// ��ʣ����λ�ĸ�����1
			trainSeat.reduceSeat(seat);
			// ����λ�������´����json����
			explain = JSONArray.fromObject(trainSeatss).toString();
			// ���µ���Ӧ�����ݿ���
			if(one.equals(start) && two.equals(end)){
				// ����������������г�ʱ
				exp=explain;
			}else{
				updateTrainSeat(one, two, groupId, explain, connection,type);
			}
		}		
	}

	/**
	 * ��������һ�˻𳵰���֮�󣬸������߸��𳵰��ŵ���λ
	 * 
	 * @param one
	 *            ��ʼվ����
	 * @param two
	 *            �յ�վ����
	 * @param groupId
	 *            �𳵰�������
	 * @param explain
	 *            �𳵰�����λ���json�ַ���
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
	 * ���¹�����г̵���λʣ�࣬���ڽ�������Ŀ���
	 * 
	 * @param one
	 *            ��ʼվ����
	 * @param two
	 *            �յ�վ����
	 * @param groupId
	 *            �𳵰�������
	 * @param explain
	 *            �𳵰�����λ���json�ַ���
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
		// ��ȡ��Ӱ�����������Ϊ�����ʾ����ִ�����ѱ����ģ�ִ�лع�
		int rows=statement.executeUpdate();
		if(rows == 0){
			connection.rollback();
			throw new DaoSystemException("������ع�");
		}
	}
	
	/**
	 * ������λ���ͼ�ʣ������ļ���
	 * 
	 * @param line
	 *            �����json�ַ���
	 * @return
	 */
	private ArrayList<TrainSeat> geTrainSeats(String line) {
		JSONArray jsonArray = JSONArray.fromObject(line);
		ArrayList<TrainSeat> trainSeats = (ArrayList<TrainSeat>) jsonArray.toList(jsonArray, TrainSeat.class);
		return trainSeats;
	}

	
	/**
	 * ���ؾ�ͣվ
	 * 
	 * @param line
	 *            ��ͣվjson�����ַ���
	 * @return
	 */
	private ArrayList<StopOverSation> getStopOverStaions(String line) {
		JSONArray jsonArray = JSONArray.fromObject(line);
		ArrayList<StopOverSation> stopOverSations = (ArrayList<StopOverSation>) jsonArray.toList(jsonArray,
				StopOverSation.class);
		
		return stopOverSations;
	}
	
	/**
	 * ȡ����Ԥ������λ(��Ʊ��ȡ������)
	 * @param trainDateId �����ڰ��ű��
	 * @param seat ��Ҫ�˵���λλ��
	 * @param type ��Ӧ�ĳ�Ʊ����
	 */
	public Integer returnTrainTicket(int trainDateId,String seat,int type){
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String sql = "select `explain`,group_id,version" + type + " from tule_train_date_arrange where id=?";
		try {
			connection = DBUtils.getConnection();
			// ��������
			connection.setAutoCommit(false);
			// ��������ĸ���ȼ�Ϊ�����ύ
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			statement = connection.prepareStatement(sql);
			statement.setInt(1, trainDateId);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				String explain = resultSet.getString("explain");
				int groupId = resultSet.getShort("group_id");
				// ��ȡ��ǰ��λ����Ӧ�İ汾��
				version = resultSet.getInt("version" + type);
				JSONArray jsonArray = JSONArray.fromObject(explain);
				ArrayList<TrainSeat> trainSeatss = (ArrayList<TrainSeat>) jsonArray.toList(jsonArray, TrainSeat.class);			
				TrainSeat trainSeat = trainSeatss.get(type);
				// �ճ�ָ������λ
				trainSeat.returnSeat(seat);
				// ���������λ���޸����ߵĻ𳵰��ŵ���λ���
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
	 * �ճ�������;�г̳��ζ�Ӧ�ĳ�λ
	 * 
	 * @param id
	 *            �����ڰ��ű��
	 * @param type
	 *            ��λ�����±�
	 * @param seat
	 *            ��Ҫ�ճ�����λλ��
	 * @param groupId
	 *            �����ڰ�������
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
			// ���ҵ���Ӧ����ʼվ���յ�վ���о�ͣվ����
			returnAllTrainArrangeB(oneStation, twoStation, type, sation, seat, groupId, connection);
		}
	}
	
	/**
	 * �����Ҫ�ճ��Ļ����ڰ��ŵİ�Σ������ʼվ���յ�վ��Ϊ��ͨ�����������޸����ߵĳ�Ʊ���
	 * 
	 * @param one
	 *            �������ʼվ����
	 * @param two
	 *            ������յ�վ����
	 * @param type
	 *            ��λ���ͱ��
	 * @param sation
	 *            �г���;ͣ����Ϣ����
	 * @param seat
	 *            �ѹ������λ���
	 * @param groupId
	 *            �����ڰ�������
	 * @throws Exception 
	 */
	private void returnAllTrainArrangeB(String one, String two, int type, ArrayList<StopOverSation> sation, String seat,
			int groupId, Connection connection) throws Exception {
		int second = 0; // ��ʼվ��ʼ���ڶ�����վ���±�
		int last = 0; // �յ�վ���±�
		for (int i = 0; i < sation.size(); i++) {
			/*
			 * ����������±�
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
		// �����¹���İ�Σ�Ȼ��ͨ��version�ж��Ƿ�����ύ
		updateTrainSeatFirst(start, end, groupId, exp, connection, type);
		// �ύ����
		connection.commit();
		// �ָ����ݿ�Ϊ�Զ��ύ
		connection.setAutoCommit(true);
	}
	
	/**
	 * ͨ��������������Ҫ���µĻ����ڰ��ŵ���λʣ�����(explain)
	 * 
	 * @param one
	 *            ���µ���ʼվ����
	 * @param two
	 *            ���µ��յ�վ����
	 * @param seat
	 *            �������λ���(0:1:1)
	 * @param groupId
	 *            �𳵰�������
	 * @param type
	 *            ��λ���ͱ��
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
			// ���ÿ����λ��ʣ���������
			ArrayList<TrainSeat> trainSeatss = (ArrayList<TrainSeat>) jsonArray.toList(jsonArray, TrainSeat.class);
			// �����ָ������λʵ����
			TrainSeat trainSeat = trainSeatss.get(type);
			// ��ָ����λ�ճ���
			trainSeat.returnSeat(seat);
			// ����λ�������´����json����
			explain = JSONArray.fromObject(trainSeatss).toString();
			// ���µ���Ӧ�����ݿ���
			if(one.equals(start) && two.equals(end)){
				// ����������������г�ʱ
				exp=explain;
			}else{
				updateTrainSeat(one, two, groupId, explain, connection,type);
			}
		}		
	}
}
