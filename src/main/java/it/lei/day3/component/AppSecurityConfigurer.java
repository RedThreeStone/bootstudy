package it.lei.day3.component;
/**
 * @author lei
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class AppSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        System.out.println("开始认证角色..............");
        auth.inMemoryAuthentication().passwordEncoder(new MyPasswordEncoder()).withUser("huangLei").password("a7633050").roles("ADMIN","DBA","USER");
        auth.inMemoryAuthentication().passwordEncoder(new MyPasswordEncoder()).withUser("renling").password("huangl").roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("开始配置权限...............");
        //过滤静态登录页面和静态资源,这里的/路径都是指的是controller请求路径并不是指静态页面路径
        http.authorizeRequests()
                .antMatchers("/login","/js/**").permitAll()
        .antMatchers("/","/home").hasRole("USER")
        .antMatchers("/admin/**").hasAnyRole("ADMIN","DBA")
        .antMatchers("/dba/**").hasAnyRole("DBA")
        .anyRequest().authenticated()
        .and().csrf().disable()
        .formLogin()
        .loginPage("/login")
        .successHandler(authenticationSuccessHandler)
                .failureForwardUrl("/myError")
        .usernameParameter("loginname").passwordParameter("password")
        .and()
        .logout().permitAll()
        .and()
        .exceptionHandling().accessDeniedPage("/accessDenied");
    }
}
