package spring.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import spring.service.ex.PassErrorException;
import spring.service.ex.ServiceException;
import spring.service.ex.SystemException;

/**
 * ����Controller���������������������쳣����
 * @author ��Ԫ��
 */
public class BaseController {
    
	/**
	 * �������ط�����쳣������ͻ���
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(ServiceException.class)
	@ResponseBody
	public ResultResponse<Void> HandlerException(ServiceException exception)
	{
		ResultResponse<Void> response=new ResultResponse<Void>();			
		if(exception instanceof PassErrorException)
		{
			response.setState(300);
			response.setMessage(exception.getMessage());
		} else if(exception instanceof SystemException){
			response.setState(400);
			response.setMessage(exception.getMessage());
		}
		return response;		
	}	
}
