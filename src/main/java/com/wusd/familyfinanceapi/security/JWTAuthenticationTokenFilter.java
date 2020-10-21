package com.wusd.familyfinanceapi.security;

import com.alibaba.fastjson.JSON;
import com.wusd.familyfinanceapi.util.JwtUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthenticationTokenFilter extends BasicAuthenticationFilter {
    public JWTAuthenticationTokenFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        //0.校验
        //1.获取表头
        //2.获取解密的信息
        //3.获取authentication放入SecurityContextHolder

        String authorization = request.getHeader("Authorization");
        logger.info("JWTAuthenticationTokenFilter.doFilterInternal...authorization->" + authorization);
        if (authorization != null) {
            String str = JwtUtils.decrypt(authorization);
            if (str != null) {
                MyUserDetails myUserDetails = JSON.parseObject(str, MyUserDetails.class);
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                        myUserDetails.getUsername(),
                        myUserDetails.getPassword(),
                        myUserDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(token);
            }
        }
        chain.doFilter(request, response);
    }
}
