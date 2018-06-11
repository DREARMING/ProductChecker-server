package com.mvcoder.models;

import com.mvcoder.bean.User;

public interface IUserModel {

    User login(String username, String password);

}
