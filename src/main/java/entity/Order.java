package entity;
import java.io.Serializable;
import java.util.Date;
import java.util.Arrays;
import dao.DBUtils;
import dao.TrainDao;
/**
 * ������ʵ����
 * @author tarena
 */
public class Order implements Serializable {
    private int id; //�������
    private int userId;//�û����
    private String status;//����״̬
    private String createTime;//����ʱ��
    private int trafficDateArrangeId;//��ͨ����-���ڱ��
    private String trainName; //��վ����
    private String [] totlePrice;//�����ܽ��
    private Integer [] passengerId;//�˿ͱ��
    private String type;//��������
    private String [] explain;//����˵��,����ÿ���˿͵�˵�������ն����ţ��նΣ���λ��
    private String reservation;//Ԥ����ʽ
    private int returnPrice;//�˻����
    private String dAddress;//���͵�ַ
    private String contactPhone;//��ϰ���ֻ���     
    private String timeClose; // ������ֹʱ��
    private String created; // ��������ʱ��
    private String commentStatus; // ��������״̬
    private String comment; // ��������
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
