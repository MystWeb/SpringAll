package com.proaim.security.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
        // 定制请求的授权规则
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/level1/**").hasRole("VIP1")
                .antMatchers("/level2/**").hasRole("VIP2")
                .antMatchers("/level3/**").hasRole("VIP3")
                .and()
                // 开启自动配置的登录功能，场景：如果未登录，无权限就会跳转到登录页面
                .formLogin()
                .usernameParameter("user")
                .passwordParameter("pwd")
                .loginPage("/userlogin")
                // 1、/login来到登录页
                // 2、重定向到/login?error表示登录失败
                // 3、更多详细规定
                // 4、默认post形式提交 /login 代表处理登录
                // 5、一旦定制loginPage，那么loginPage的请求就是登录
                .loginPage("/userlogin") // 自定义登录页面
        ;
        // 开启自动配置的注销功能
        http.logout();
        // 1、访问 /logout 表示用户注销并清空Session
        // 2、注销成功会返回 /login?logout 页面；

        // 开启记住登录功能
        http.rememberMe().rememberMeParameter("remember");
        // 登录成功以后，将cookie发给浏览器保存，以后访问页面就会带上cookie，只要通过检查就可以免登录
        // 点击注销会删除cookie

    }

    // 定义认证规则
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        super.configure(auth);
        // 加密
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        auth.inMemoryAuthentication().passwordEncoder(passwordEncoder)
                .withUser("zhangsan")
                .password(passwordEncoder.encode("123456"))
                .roles("VIP1", "VIP2")
                .and()
                .withUser("lisi")
                .password(passwordEncoder.encode("123456"))
                .roles("VIP2", "VIP3")
                .and()
                .withUser("wangwu")
                .password(passwordEncoder.encode("123456"))
                .roles("VIP1", "VIP3")
        ;
    }
}
