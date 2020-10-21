package com.wusd.familyfinanceapi.security;

import com.wusd.familyfinanceapi.entity.gen.Authority;
import com.wusd.familyfinanceapi.entity.gen.AuthorityExample;
import com.wusd.familyfinanceapi.entity.gen.User;
import com.wusd.familyfinanceapi.entity.gen.UserExample;
import com.wusd.familyfinanceapi.mapper.gen.AuthorityMapper;
import com.wusd.familyfinanceapi.mapper.gen.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AuthorityMapper authorityMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andUsernameEqualTo(username);
        List<User> users = userMapper.selectByExample(userExample);
        if (CollectionUtils.isEmpty(users)) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        MyUserDetails userDetails = new MyUserDetails();
        userDetails.setUserId(users.get(0).getUserId());
        userDetails.setUsername(username);
        userDetails.setPassword(users.get(0).getPassword());
        AuthorityExample authorityExample = new AuthorityExample();
        authorityExample.createCriteria()
                .andUsernameEqualTo(username);
        List<Authority> authorities = authorityMapper.selectByExample(authorityExample);
        List<MyGrantedAuthority> grantedAuthorities = authorities.stream().map(o -> {
            MyGrantedAuthority authority = new MyGrantedAuthority();
            authority.setAuthority(o.getAuthority());
            return authority;
        }).collect(Collectors.toList());
        userDetails.setAuthorities(grantedAuthorities);
        return userDetails;
    }
}
