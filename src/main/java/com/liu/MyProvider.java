package com.liu;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * AuthenticationProvider接口为自定义用户验证方法所要实现的类
 * SpringSecurity验证时会便利每一个AuthenticationProvider，只要有一个AuthenticationProvider验证成功，则返回成功
 * 需要在HttpSecurity中注册
 */
@Service
public class MyProvider implements AuthenticationProvider {

    @Resource
    private MyUserDetailService myUserDetailService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName=authentication.getName();
        String password=authentication.getCredentials().toString();
        UserDetails user =myUserDetailService.loadUserByUsername(userName);
        if(user.getPassword().equals(password)) {
            return new UsernamePasswordAuthenticationToken(userName,password,user.getAuthorities());
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
