package com.twoyum.login.service.impl;

import com.twoyum.login.domain.*;
import com.twoyum.login.mapper.*;
import com.twoyum.login.security.JwtUserDetailsServiceImpl;
import com.twoyum.login.security.SimpleAuthenticationManager;
import com.twoyum.login.service.IUserService;
import com.twoyum.login.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 用户服务层接口实现
 *
 * @author huangjuan
 * Created on 2018/2/22
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private SimpleAuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private AuthorityMapper authorityMapper;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ValidateInfoMapper validateCodeInfoMapper;

    @Autowired
    private UserRolesMapper userRolesMapper;
    @Autowired
    private JwtUserDetailsServiceImpl jwtUserDetailsServiceImpl;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private YourCertificateMapper yourCertificateMapper;


//    @Override
//    public Map<String,Object> login(String account, String password) {
//        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(account, password);
//        Authentication  authentication = authenticationManager.authenticate(upToken);
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        UserDetails userDetails = userDetailsService.loadUserByUsername(account);
//        List<ResourceOutDto> resources =authorityMapper.findResourceByAccount(account);
//        List<TreeMenu> menus = createMenu(resources);
//        Map<String,Object> map = new HashMap<String,Object>();
//        map.put("menus",menus);
//        map.put("token",jwtUtil.sign(userDetails));
//        UserBaseOutDto user = userMapper.findByAccount(account);
//        map.put("users",user);
//        return map;
//    }

    @Override
    public Map<String,Object> login(String email, String password) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(email, password);
        Authentication  authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("token",jwtUtil.sign(userDetails));
        List<SysRoleOutDto> roles = authorityMapper.findRoleInfoByEmail(email);
        UserOutDto userOutDto =  userMapper.findUserByEmail(email);
        List<String> roleList = new ArrayList<String>();
        for (SysRoleOutDto sysRoleOutDto :roles){
            roleList.add(sysRoleOutDto.getRoleCode());
        }
        map.put("url",userOutDto.getUrl());
        map.put("userName",userOutDto.getUserName());
        map.put("switchType",userOutDto.getSwitchType());
        map.put("roleList",roleList);
        return map;
    }


/*    public List<TreeMenu> createMenu(List<ResourceOutDto> resources){
        List<TreeMenu> treeMenus = new ArrayList<>();
        for(ResourceOutDto resource:resources){
            TreeMenu treeMenu = new TreeMenu();
            if(resource.getParentCode().equals("0")){
                treeMenu.setPath(resource.getPath());
                treeMenu.setName(resource.getResourceName());
                treeMenu.setId(resource.getSysResourceId());
                treeMenus.add(treeMenu);
            }
        }
        List<TreeMenu> newTree = new ArrayList<>();
        for (TreeMenu treeMenu1 :treeMenus){
            List<ChildrenMenu> childrenMenus = new ArrayList<ChildrenMenu>();
            for(ResourceOutDto resource:resources){
                if(treeMenu1.getId().equals(resource.getParentCode())){
                    ChildrenMenu childrenMenu = new ChildrenMenu();
                    childrenMenu.setName(resource.getResourceName());
                    childrenMenu.setPath(resource.getPath());
                    childrenMenus.add(childrenMenu);
                }
            }
            treeMenu1.setChildren(childrenMenus);
            newTree.add(treeMenu1);
        }
        return newTree;
    }*/


    @Override
    @Transactional
    public void addRegisterUser(UserInDto userInDto) {
//        if (userMapper.findByEmail(userInDto) >0) {
//            return 0;//该邮件地址已被使用
//        }
        String rawPassword = userInDto.getPassword();
        userInDto.setPassword(encoder.encode(rawPassword));
        userMapper.addRegisterUser(userInDto);
        UserRolesInDto userRolesInDto = new UserRolesInDto();
        userRolesInDto.setRoleId(2);
        int userId = userInDto.getId();
        userRolesInDto.setSysUserId(userId);
        userRolesMapper.addUserRoles(userRolesInDto);
        yourCertificateMapper.addYourCertificate(userInDto);
//        return 1;//注册用户成功
    }

    @Override
    public boolean findByEmailCount(UserInDto userInDto) {
        boolean ret = false;
        if(userMapper.findByEmailCount(userInDto)>0){
            ret = true;
        }
        return ret;
    }


    @Override
    public UserOutDto findUserByEmail(String email){
        return userMapper.findUserByEmail(email);
    }

    @Override
    @Transactional
    public void updateUserInfo(UserInDto userInDto) {
         userMapper.updateUserInfo(userInDto);
        ValidateInfoInDto validateCodeInfoInDto = new ValidateInfoInDto();
        validateCodeInfoInDto.setEmail(userInDto.getEmail());
        validateCodeInfoInDto.setSign(userInDto.getSign());
        validateCodeInfoInDto.setValidateCode(userInDto.getValidateCode());
        validateCodeInfoInDto.setRegisterDate(userInDto.getRegisterDate());
        validateCodeInfoMapper.addValidateInfo(validateCodeInfoInDto);
    }

    @Override
    public int updatePassword(UserInDto userInDto) {
        return userMapper.updatePassword(userInDto);
    }
}