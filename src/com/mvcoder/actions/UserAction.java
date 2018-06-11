package com.mvcoder.actions;

import com.mvcoder.bean.Result;
import com.mvcoder.bean.User;
import com.mvcoder.models.UserModel;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport {

    private String username;
    private String password;

    private UserModel userModel;

    private Result<User> result = new Result<>();

    public String login(){
        User user = userModel.login(username,password);
        if(user == null) {
            result.setCode(-1);
            result.setMsg("用户名或密码不正确");
        }else {
            result.setCode(200);
            result.setMsg("登录成功");
            user.setPassword(null);
            result.setData(user);
        }
        return SUCCESS;

    }

    public Result<User> getResult() {
        return result;
    }

    public void setResult(Result<User> result) {
        this.result = result;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }
}
