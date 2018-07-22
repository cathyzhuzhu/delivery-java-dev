package com.twoyum.login.service;

import com.twoyum.login.domain.UserInDto;
import com.twoyum.login.domain.UserOutDto;

import java.util.List;
import  java.util.Map;

/**
 * 用户服务层接口
 *
 * @author huangjuan
 * Created on 2018/2/22
 */
public interface IUserService {

    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 操作结果
     */
    Map<String,Object> login(String username, String password);

    /**
     *  新增注册用户
     * @param userInDto 输入对象
     * @return 返回结果
     */
    void addRegisterUser(UserInDto userInDto);


    boolean findByEmailCount(UserInDto userInDto);

    /**
     * 根据email获取客户信息
     * @param email 邮箱
     * @return 返回用户信息
     */
    UserOutDto findUserByEmail(String email);

    /**
     * 更新用户信息
     * @param userInDto 输入对象
     * @return 返回结果
     */
    void updateUserInfo(UserInDto userInDto);

    /**
     * 修改密码信息
     * @param userInDto
     * @return 返回结果
     */
    int updatePassword(UserInDto userInDto);
}
