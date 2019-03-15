package  spring.controller;
/**
 * 返回给页面的返回参数
 * @author 李元浩
 */
public class ResultResponse<T> {
    private Integer state=200; //状态码
    private String message;     //状态描述
    private T param;       //状态参数
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getParam() {
		return param;
	}
	public void setParam(T param) {
		this.param = param;
	}
	@Override
	public String toString() {
		return "ResultResponse [state=" + state + ", message=" + message + ", param=" + param + "]";
	}
	
}
