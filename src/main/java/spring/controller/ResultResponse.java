package  spring.controller;
/**
 * ���ظ�ҳ��ķ��ز���
 * @author ��Ԫ��
 */
public class ResultResponse<T> {
    private Integer state=200; //״̬��
    private String message = "�ɹ�";     //״̬����
    private T param;       //״̬����
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
