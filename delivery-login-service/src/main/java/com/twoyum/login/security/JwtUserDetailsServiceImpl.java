package com.twoyum.login.security;

import com.twoyum.login.domain.SysRoleOutDto;
import com.twoyum.login.domain.SysUserOutDto;
import com.twoyum.login.mapper.AuthorityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import  java.util.List;
import java.util.stream.Collectors;

/**
 * 用户验证方法
 *
 * @author hackyo
 * Created on 2017/12/8 9:18.
 */
@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private AuthorityMapper authorityMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUserOutDto user = authorityMapper.findUserInfoByEmail(username);
            List<SysRoleOutDto> sysRoleOutDtos= authorityMapper.findRoleInfoByEmail(username);
            List<String> roles = new ArrayList<String>();
            for(SysRoleOutDto sysRoleOutDto: sysRoleOutDtos){
                roles.add(sysRoleOutDto.getRoleCode());
            }
            return new JwtUser(user.getEmail(), user.getPassword(), roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
    }

}