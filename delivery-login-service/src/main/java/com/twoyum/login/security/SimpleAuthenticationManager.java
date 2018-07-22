package com.twoyum.login.security;

import com.twoyum.login.utils.EnumUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class SimpleAuthenticationManager implements AuthenticationManager {
    @Autowired
    private JwtUserDetailsServiceImpl jwtUserDetailsServiceImpl;

    @Autowired
    private  EnumUtil enumUtil;

    @Autowired
    private BCryptPasswordEncoder encoder;


    public Authentication authenticate(Authentication auth)
            throws AuthenticationException {
        String userName =  auth.getName();
        String password = auth.getCredentials().toString();
//        if (StringUtils.isEmpty(userName)) {
//            throw new BadCredentialsException("账号不为空");
//        }else if(StringUtils.isEmpty(password)){
//            throw new BadCredentialsException("密码不为空");
//        }
        UserDetails  sysUser =  jwtUserDetailsServiceImpl.loadUserByUsername(userName);
        if (sysUser == null) {
            throw new BadCredentialsException(enumUtil.getTipMsg("zh-cn",15));//该账号不存在
        }
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        if (auth.getName().equals(userName)&& encoder.matches(password,sysUser.getPassword())) {
            return new UsernamePasswordAuthenticationToken(sysUser.getUsername(),
                    sysUser.getPassword(), sysUser.getAuthorities());
        }
        throw new BadCredentialsException(enumUtil.getTipMsg("zh-cn",5));
    }
}
