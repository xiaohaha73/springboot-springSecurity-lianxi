package com.example.config;

import com.example.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

    // 用于验证登陆的service层
    @Autowired
    private UserServiceImpl userService;

    // 导入springSecurity的密码加密对象
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 配置用户信息的来源 内存/数据库
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("user")
//                .password("{noop}123")
//                .roles("USER"); // 登陆的角色 不要写ROLE_USER这种带前缀的不然容易报错

        // 改为使用数据库的数据源
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());

    }


    // 配置springSecurity相关信息
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 释放静态资源
                .antMatchers("/login.jsp","/failer.jsp","/css/**","/img/**","/plugins/**").permitAll()
                // 设置访问权限
                .antMatchers("/**").hasAnyRole("ROLE_USER","ROLE_ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                // 设置登陆页面
                .loginPage("/login.jsp")
                // 登陆页面的url
                .loginProcessingUrl("/login")
                // 成功后跳转的页面
                .successForwardUrl("/index.jsp")
                .failureForwardUrl("/failer.jsp")
                .permitAll()
                .and()
                // 配置登出
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login.jsp")
                // 清空session
                .invalidateHttpSession(true)
                .permitAll()
                .and()
                .csrf()
                .disable(); // 禁用csrf

    }
}
