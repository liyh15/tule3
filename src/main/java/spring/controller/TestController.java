package spring.controller;

import java.net.Authenticator.RequestorType;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Controller;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import entity.Transaction;
import jdk.nashorn.internal.ir.RuntimeNode.Request;
import spring.validator.TransactionValidator;

/**
 * 测试用的Controller
 * @author 李元浩
 *
 */
@Controller
@RequestMapping("/test")
public class TestController {
	
	
	public void InitBinder(DataBinder binder){
		// 数据绑定器加上验证器
		binder.setValidator(new TransactionValidator());
	}
	
	@RequestMapping(value = "/one.do",method=RequestMethod.GET)
	@ResponseBody
    public String testTransaction(@Valid Transaction transaction,Errors errors){		
    	if(errors.hasErrors()){
    		//获取错误信息
    		List<FieldError> errors2=errors.getFieldErrors();
    		for(FieldError error:errors2){
    			System.out.println(error.getDefaultMessage());
    		}
    	}   		
		return "hello";
    }  
}
