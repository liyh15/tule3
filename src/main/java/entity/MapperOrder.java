package entity;

import java.io.Serializable;

/**
 * 通过数据库取出来的类
 * @author 李元浩
 *
 */
public class MapperOrder implements Serializable {
     
	private Integer id;
	private Integer userId;
	private String  status;
	private Integer trafficDateArrangeId;
	private String totlePrice;
	private String passengerId;
	private String type;
	private String oexplain;
	private String reservation;
	private Integer returnPrice;
	private String distributionAddress;
	private String contactPhone;
	private String createTime;
	private String timeClose;
	private String comment;
	private String commentStatus;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getTrafficDateArrangeId() {
		return trafficDateArrangeId;
	}
	public void setTrafficDateArrangeId(Integer trafficDateArrangeId) {
		this.trafficDateArrangeId = trafficDateArrangeId;
	}
	public String getTotlePrice() {
		return totlePrice;
	}
	public void setTotlePrice(String totlePrice) {
		this.totlePrice = totlePrice;
	}
	public String getPassengerId() {
		return passengerId;
	}
	public void setPassengerId(String passengerId) {
		this.passengerId = passengerId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getOexplain() {
		return oexplain;
	}
	public void setOexplain(String oexplain) {
		this.oexplain = oexplain;
	}
	public String getReservation() {
		return reservation;
	}
	public void setReservation(String reservation) {
		this.reservation = reservation;
	}
	public Integer getReturnPrice() {
		return returnPrice;
	}
	public void setReturnPrice(Integer returnPrice) {
		this.returnPrice = returnPrice;
	}
	public String getDistributionAddress() {
		return distributionAddress;
	}
	public void setDistributionAddress(String distributionAddress) {
		this.distributionAddress = distributionAddress;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
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
	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getCommentStatus() {
		return commentStatus;
	}
	public void setCommentStatus(String commentStatus) {
		this.commentStatus = commentStatus;
	}	
}
