package entity;

import org.omg.CORBA.PUBLIC_MEMBER;

/**
 * 火车安排日期实体类
 * @author 李元浩
 *
 */
public class TrainDateArrange {
	
	// 编号
    private Integer id;
    
    // 行程安排开始日期
    private String day;
    
    // 火车安排编号
    private Integer trainArrangeId;
    
    // 座位剩余情况
    private String explain;
    
    // 行程安排结束日期
    private String endDay;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public Integer getTrainArrangeId() {
		return trainArrangeId;
	}

	public void setTrainArrangeId(Integer trainArrangeId) {
		this.trainArrangeId = trainArrangeId;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}

	public String getEndDay() {
		return endDay;
	}

	public void setEndDay(String endDay) {
		this.endDay = endDay;
	}
    
    
}
