package com.mvcoder.models;

import com.mvcoder.bean.User;
import com.mvcoder.dao.UserDao;
import com.mvcoder.security.TokenManager;

import static com.mvcoder.utils.Constants.TOKEN_TTL;

public class UserModel implements IUserModel {

    private UserDao userDao;

    private TokenManager tokenManager;

    @Override
    public User login(String username, String password) {
        User user = userDao.get(username,password);
        if(user == null) return null;
        String token = makeToken(user);
        user.setToken(token);
        return user;
    }

    private String makeToken(User user){
        return tokenManager.createJWT(user, TOKEN_TTL);
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public TokenManager getTokenManager() {
        return tokenManager;
    }

    public void setTokenManager(TokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }
}
