package org.hao.litemall.wx.service;

import org.hao.litemall.wx.util.JwtHelper;

public class UserTokenManager {
    public static String generateToken(Integer id) {
        JwtHelper jwtHelper = new JwtHelper();
        return jwtHelper.createToken(id);
    }
    public static Integer getUserId(String token) {
        JwtHelper jwtHelper = new JwtHelper();
        Integer userId = jwtHelper.verifyTokenAndGetUserId(token);
        if (userId == null || userId == 0) {
            return null;
        }
        return userId;
    }
}
