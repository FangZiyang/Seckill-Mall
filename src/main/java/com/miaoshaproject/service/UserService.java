package com.miaoshaproject.service;

import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.service.model.UserModel;

public interface UserService {
    //get user object through Id
    UserModel getUserById(Integer id);

    void register(UserModel userModel) throws BusinessException;

    /*
    telphone:
    password:
     */
    UserModel validateLogin(String telphone, String encrptPassword) throws BusinessException;

}
