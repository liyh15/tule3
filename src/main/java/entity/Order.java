package entity;
import java.io.Serializable;
import java.util.Date;
import java.util.Arrays;
import dao.DBUtils;
import dao.TrainDao;
/**
 * 订单的实体类
 * @author tarena
 */
public class Order implements Serializable {
    private int id; //订单编号
    private int userId;//用户编号
    private String status;//订单状态
    private String createTime;//创建时间
    private int trafficDateArrangeId;//交通安排-日期编号
    private String trainName; //火车站名称
    private String [] totlePrice;//订单总金额
    private Integer [] passengerId;//乘客编号
    private String type;//订单类型
    private String [] explain;//订单说明,对于每个乘客的说明，保险订单号，舱段，座位号
    private String reservation;//预定方式
    private int returnPrice;//退还金额
    private String dAddress;//配送地址
    private String contactPhone;//练习人手机号     
    private String timeClose; // 订单截止时间
    private String created; // 订单创建时间
    private String commentStatus; // 订单评论状态
    private String comment; // 订单评论
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getTrafficDateArrangeId() {
		return trafficDateArrangeId;
	}
	public void setTrafficDateArrangeId(int trafficDateArrangeId) {
		this.trafficDateArrangeId = trafficDateArrangeId;
	}
    
	public String[] getTotlePrice() {
		return totlePrice;
	}
	public void setTotlePrice(String[] totlePrice) {
		this.totlePrice = totlePrice;
	}
	public Integer[] getPassengerId() {
		return passengerId;
	}
	public void setPassengerId(Integer[] passengerId) {
		this.passengerId = passengerId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String[] getExplain() {
		return explain;
	}
	public void setExplain(String[] explain) {
		this.explain = explain;
	}
	public String getReservation() {
		return reservation;
	}
	public void setReservation(String reservation) {
		this.reservation = reservation;
	}
	public int getReturnPrice() {
		return returnPrice;
	}
	public void setReturnPrice(int returnPrice) {
		this.returnPrice = returnPrice;
	}
	public String getdAddress() {
		return dAddress;
	}
	public void setdAddress(String dAddress) {
		this.dAddress = dAddress;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getTrainName() {
		return trainName;
	}
	
	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getTimeClose() {
		return timeClose;
	}
	public void setTimeClose(String timeClose) {
		this.timeClose = timeClose;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getCommentStatus() {
		return commentStatus;
	}
	public void setCommentStatus(String commentStatus) {
		this.commentStatus = commentStatus;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
}
