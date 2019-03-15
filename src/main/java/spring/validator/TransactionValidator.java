package spring.validator;

import java.util.Set;

import javax.validation.ConstraintViolation;

import javax.validation.metadata.BeanDescriptor;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import entity.Transaction;

public class TransactionValidator implements Validator {

	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return Transaction.class.equals(arg0);
	}

	public void validate(Object arg0, Errors arg1) {
		// TODO Auto-generated method stub
		Transaction transaction=(Transaction) arg0;
		if(transaction.getProduct()>0){
			arg1.rejectValue("amount", null,"不能大于零");
		}
	}
   
}
