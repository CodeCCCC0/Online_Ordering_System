package com.takeout.service;

import com.takeout.pojo.User;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public interface UserService {
    public boolean isExistUser(String uname);
    public boolean isExistEmail(String email);
    public boolean isItsEmail(String uname, String email);
    public Map<String, Object> register(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException;
    public Map<String, Object> login(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException;
    public Map<String, Object> updatePassword(String newPassword, String uname) throws UnsupportedEncodingException, NoSuchAlgorithmException;
    public User getUserInfo(String username);
    public int updateUserInfo(User user);
}
