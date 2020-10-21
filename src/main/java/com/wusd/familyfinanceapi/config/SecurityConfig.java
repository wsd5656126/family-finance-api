package com.wusd.familyfinanceapi.config;

import com.wusd.familyfinanceapi.encoder.SimplePasswordEncoder;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 配置Spring Security的Filter链
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    /**
     * 配置如何通过拦截器保护请求
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //默认登录页
                .formLogin()
//                .loginPage("/login")
                .and()
                //todo wusd 关闭跨域
                .csrf()
                .disable()
                //要求所有进入应用的HTTP请求都要认证
                .authorizeRequests()
                .antMatchers("/demo/**").hasRole("USER")
                .antMatchers("/user/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()

                .requiresChannel()
                .antMatchers("/user/form").requiresSecure()
                .and()

                .formLogin().and()
                .httpBasic()
                //指定域
//                .realmName("/")
        ;
    }

    /**
     * 配置user-detail服务
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //没有用户存储,没人能登录成功
//        super.configure(auth);
        //使用用户存储
//        auth.inMemoryAuthentication()
//                .withUser("user").password("password").roles("USER").and()
//                .withUser("admin").password("password").roles("USER", "ADMIN");
        //等价于
        auth.inMemoryAuthentication()
                .passwordEncoder(new SimplePasswordEncoder())
                .withUser("user").password("password").authorities("ROLE_USER").and()
                .withUser("admin").password("password").authorities("ROLE_USER", "ROLE_ADMIN");
//        auth.jdbcAuthentication()
//                .dataSource(dataSource)
//                .usersByUsernameQuery(
//                        //todo true?
//                        "select username, password, true from ff_user where username=?")
//                .authoritiesByUsernameQuery(
//                        "select username, 'ROLE_USER' from ff_user_role where username=?")
        //明文加密
//                .passwordEncoder(new SCryptPasswordEncoder());
    }
}
