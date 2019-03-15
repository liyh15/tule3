package spring.service.ex;
/**
 * 系统异常
 * @author 李元浩
 *
 */
public class SystemException extends ServiceException 
{
	private static final long serialVersionUID = 1L;

	public SystemException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SystemException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public SystemException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public SystemException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public SystemException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
 
}
