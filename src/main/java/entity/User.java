package entity;

import java.io.Serializable;

public class User implements Serializable {
    private int id;//用户编号
    private String nickName;//用户昵称
    private String name;//用户姓名
    private String phone;//用户手机号
    private String birthday;//用户生日
    private String email;//用户邮箱
    private String liveCity;//常住城市
    private String address;//详细地址
    private String married;//婚姻情况
    private String job;//职业
    private String education;//学历
    private String password;//密码
    private String passQuestion;//密保问题
    private String personalId;//证件名称
    private String paperType;//证件类型
    private String sex;
    private String salt;
    
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	
	public String getLiveCity() {
		return liveCity;
	}
	public void setLiveCity(String liveCity) {
		this.liveCity = liveCity;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMarried() {
		return married;
	}
	public void setMarried(String married) {
		this.married = married;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassQuestion() {
		return passQuestion;
	}
	public void setPassQuestion(String passQuestion) {
		this.passQuestion = passQuestion;
	}
	public String getPersonalId() {
		return personalId;
	}
	public void setPersonalId(String personalId) {
		this.personalId = personalId;
	}
	public String getPaperType() {
		return paperType;
	}
	public void setPaperType(String paperType) {
		this.paperType = paperType;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", nickName=" + nickName + ", name=" + name + ", phone=" + phone + ", birthday="
				+ birthday + ", email=" + email + ", liveCity=" + liveCity + ", address=" + address + ", married="
				+ married + ", job=" + job + ", education=" + education + ", password=" + password + ", passQuestion="
				+ passQuestion + ", personalId=" + personalId + ", paperType=" + paperType + ", sex=" + sex + ", salt="
				+ salt + "]";
	}
  
}
