package com.wusd.familyfinanceapi.security;

import com.alibaba.fastjson.JSON;
import com.wusd.familyfinanceapi.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyUserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder myPasswordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //校验是否登录的拦截器
        http.addFilterBefore(new JWTAuthenticationTokenFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class);

        //登录配置
        http
                .formLogin()
                //get请求 登录页
                .loginPage("/login")
                //post请求 系统自带 登录接口
                .loginProcessingUrl("/login")
                .successHandler((request, response, authentication) -> {
                    //登录成功后设置JWT
                    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                    String token = JwtUtils.encrypt(JSON.toJSONString(userDetails));
                    response.setStatus(HttpServletResponse.SC_OK);
                    response.addHeader("Authorization", token);
                    response.setContentType("application/json;charset=UTF-8");
                    response.getWriter().write("登录成功");
                })
                .failureHandler((request, response, exception) -> {
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    response.setContentType("application/json;charset=UTF-8");
                    response.getWriter().write(exception.getMessage());
                });
        http
                .authorizeRequests()
                // 加权限认证, 等价于hasRole("ADMIN")
                .antMatchers("/test/admin").hasAuthority("ROLE_ADMIN")
                .antMatchers("/test/user").hasAnyRole("ADMIN", "USER")
                //不需要认证
                .antMatchers("/test", "/login", "login", "/test/post").permitAll()
                //其他都需要认证
                .anyRequest().authenticated();

        // 用户注销,并清空session
        http.logout().logoutSuccessUrl("/logoutSuccess");

        //关闭跨域
        http.csrf().disable();
        http.cors();
        //拒绝访问处理
        http
                .exceptionHandling()
                .accessDeniedHandler((request, response, accessDeniedException) -> {
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    response.setContentType("application/json;charset=UTF-8");
                    response.getWriter().write("当前角色无权限访问!");
                });
        //不使用token
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(myPasswordEncoder);
    }
}
