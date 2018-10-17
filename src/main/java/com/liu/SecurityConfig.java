package com.liu;


import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.annotation.Resource;

/**
 * 此类为核心配置类
 * HttpSecurity为配置URL访问权限
 * AuthenticationManagerBuilder为配置验证的类
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private MyProvider myProvider;

    /**
     * /resources/**  资源不做过滤
     * /admin/**资源需要ADMIN权限 （注意hasRole("ADMIN")方法内部中会加上ROLE_，也就是说权限字符串实际为ROLE_ADMIN，需要和Provider中的权限中也需要为ROLE_ADMIN不然无法访问该权限的接口）
     *defaultSuccessUrl 登录成功后的默认跳转页面
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().
                                 antMatchers("/resources/**").permitAll().
                                 antMatchers("/admin/**").hasRole("ADMIN").
                anyRequest().authenticated().and().
                formLogin().defaultSuccessUrl("https://www.baidu.com").and().csrf().disable();

        super.configure(http);
    }

    /**
     * 将自定义的Provider加入到Provider链中
     * 验证时会去每一个Provider中查询验证结果，只要有一个验证成功则成功
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(myProvider);
        super.configure(auth);
    }
}
