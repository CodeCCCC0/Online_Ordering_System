package com.takeout.service.serviceImpl;

import com.takeout.dao.UserMapper;
import com.takeout.pojo.User;
import com.takeout.service.UserService;
import com.takeout.util.MD5Util;
import com.takeout.util.NewDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean isExistUser(String uname) {
        return userMapper.selectUserByUname(uname) != null;
    }

    @Override
    public boolean isExistEmail(String email) {
        return userMapper.selectUserByEmail(email) != null;
    }

    @Override
    public boolean isItsEmail(String uname, String email) {
        User user = userMapper.selectUserByUname(uname);
        return user.getEmail().equals(email);
    }

    @Override
    public Map<String, Object> register(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Map<String, Object> map = new HashMap<>();
        if(userMapper.selectUserByUname(user.getUname())!=null){
            map.put("position", 0);
            map.put("message", "用户名已被注册");
        }else{
            user.setCreateDate(NewDate.getDate());
            user.setPwd(MD5Util.EncoderPwdByMD5(user.getPwd()));
            int rs = userMapper.addUser(user);
            map.put("position", -1);
            if(rs!=0){
                map.put("message", "注册成功！用户名: " + user.getUname());
            }else{
                map.put("message", "注册失败！请重试");
            }
        }
        return map;
    }

    @Override
    public Map<String, Object> login(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Map<String, Object> map = new HashMap<>();
        User rs = userMapper.selectUserByUname(user.getUname());
        if(rs==null){
            map.put("position", 0);
            map.put("message", "用户不存在");
        }else if(!rs.getPwd().equals(MD5Util.EncoderPwdByMD5(user.getPwd()))){
            map.put("position", 1);
            map.put("message", "密码错误");
        }else{
            map.put("position", -1);
            map.put("userType", rs.getUserType());
            map.put("message", "登录成功！");
        }
        return map;
    }

    @Override
    public Map<String, Object> updatePassword(String newPassword, String uname) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Map<String, Object> map = new HashMap<>();
        newPassword = MD5Util.EncoderPwdByMD5(newPassword);
        int rs = userMapper.updateUserPwd(uname, newPassword);
        map.put("position", -1);
        if(rs==0){
            map.put("message", "更改密码失败！");
        }else{
            map.put("message", "更改密码成功！");
        }
        return map;
    }

    @Override
    public User getUserInfo(String username) {
        return userMapper.selectUserByUname(username);
    }

    @Override
    public int updateUserInfo(User user) {
        return userMapper.updateUserInfo(user);
    }

}
