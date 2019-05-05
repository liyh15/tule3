package spring.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import spring.service.ex.PassErrorException;
import spring.service.ex.ServiceException;
import spring.service.ex.SystemException;

/**
 * 基类Controller用来处理公共的事务，例如异常处理
 * @author 李元浩
 */
public class BaseController {
    
	/**
	 * 用来返回服务端异常情况给客户端
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
