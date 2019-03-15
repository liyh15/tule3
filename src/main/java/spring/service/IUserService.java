package spring.service;

import spring.service.ex.PassErrorException;

public interface IUserService {
    public void changeUserPass(String phone,String oldPass,String newPass) throws PassErrorException;
}
