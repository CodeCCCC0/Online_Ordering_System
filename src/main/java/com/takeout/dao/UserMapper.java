package com.takeout.dao;

import com.takeout.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    public User selectUserByUname(String uname);
    public User selectUserByEmail(String email);

    public int addUser(User user);

    public int updateUserPwd(String uname, String pwd);
    public int updateUserInfo(User user);
}
