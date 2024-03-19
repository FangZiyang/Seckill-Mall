package com.miaoshaproject.service;

import com.miaoshaproject.service.model.UserModel;

public interface UserService {
    //get user object through Id
    UserModel getUserById(Integer id);
}
