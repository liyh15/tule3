package spring.controller;

import java.io.IOException;
import java.net.Authenticator.RequestorType;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Controller;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import entity.City;
import entity.TestEntity;
import entity.Transaction;
import jdk.nashorn.internal.ir.RuntimeNode.Request;
import spring.validator.TransactionValidator;

/**
 * �����õ�Controller
 * @author ��Ԫ��
 *
 */
@Controller
@RequestMapping("/test")
public class TestController {
	
	 
	public void InitBinder(DataBinder binder){
		// ���ݰ���������֤��
		binder.setValidator(new TransactionValidator());
	}
	
	@RequestMapping(value = "/one.do",method=RequestMethod.GET)
	@ResponseBody
    public String testTransaction(@Valid Transaction transaction,Errors errors){		
    	if(errors.hasErrors()){
    		//��ȡ������Ϣ
    		List<FieldError> errors2=errors.getFieldErrors();
    		for(FieldError error:errors2){
    			System.out.println(error.getDefaultMessage());
    		}
    	}   		
		return "hello";
    } 
	
	
	@RequestMapping(value = "/post.do",method = RequestMethod.POST)
	@ResponseBody
	public City postRequest(@RequestBody City city,HttpServletRequest request) {
		System.out.println(request.getQueryString());
		System.out.println(city);
		return city;
	}
	
	@RequestMapping(value = "/tests.do",method = RequestMethod.GET)
	@ResponseBody
	public TestEntity getTestEntity() {
		TestEntity testEntity = new TestEntity();
		return testEntity;
	}
	
	@RequestMapping(value = "/two.do",method = RequestMethod.POST)
	@ResponseBody
	public String two(@RequestBody Apple [] apple,HttpServletRequest request) throws IOException {
	
		System.out.println(request.getInputStream().read());
		return "aaa";
	}
}
