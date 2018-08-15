package it.lei.day4.component;
/**
 * @author lei
 */

import it.lei.day4.service.SystemUserSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class AppSecurityConfigurer extends WebSecurityConfigurerAdapter {
    @Autowired
    private SystemUserSerivce systemUserSerivce;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationProvider authenticationProvider ;
    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;


    @Bean
    public PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }
    @Bean
    public  AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setHideUserNotFoundExceptions(false);
        daoAuthenticationProvider.setUserDetailsService(systemUserSerivce);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        return  daoAuthenticationProvider;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
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
