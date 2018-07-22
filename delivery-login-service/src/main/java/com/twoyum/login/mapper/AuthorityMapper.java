package com.twoyum.login.mapper;

import com.twoyum.login.domain.*;

import java.util.List;

public interface AuthorityMapper{

    /**
     * 通过用户名查找用户
     *
     * @param email 邮箱
     * @return 用户信息
     */
    SysUserOutDto findUserInfoByEmail(String email);

    /**
     * 通过角色ID查询信息
     * @param email 邮箱
     * @return 返回查询列表
     */
    List<SysRoleOutDto> findRoleInfoByEmail(String email);

    /**
     * 根据账号获取所有菜单资源
     * @param account 账号
     * @return 返回菜单资源
     */
    List<ResourceOutDto> findResourceByAccount(String account);

}
