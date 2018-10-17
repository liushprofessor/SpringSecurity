package com.liu;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * UserDetailsService为、接口的作用是规定在此接口的实现中完成对用户的验证，此方法为传入用户名模拟完成验证，该方法将验证信息写死
 * 如果需要从数据库中查找账号则，需要加入数据实现，
 */
@Service
public class MyUserDetailService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        if("liu".equals(s)){
            List<SimpleGrantedAuthority> list=new ArrayList<>();
            SimpleGrantedAuthority simpleGrantedAuthority=new SimpleGrantedAuthority("ROLE_ADMIN");
            list.add(simpleGrantedAuthority);
            return new User("liu","123456",list);
        }
        return null;

    }
}
