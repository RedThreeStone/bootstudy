package it.lei.day3.component;
/**
 * @author lei
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


public class AppSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        System.out.println("开始认证角色..............");
        //这里设置的角色 系统会自动加上role_前缀 既ROLE_ADMIN
        auth.inMemoryAuthentication().passwordEncoder(new MyPasswordEncoder()).withUser("huangLei").password("a7633050").roles("ADMIN","DBA","USE");
        auth.inMemoryAuthentication().passwordEncoder(new MyPasswordEncoder()).withUser("renling").password("huangl").roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("开始配置权限...............3");
        //过滤静态登录页面和静态资源,这里的/路径都是指的是controller请求路径并不是指静态页面路径
        http.authorizeRequests()
                .antMatchers("/login","/js/**").permitAll()
                //配置各个访问url的权限
        .antMatchers("/","/home").hasRole("USER")
        .antMatchers("/admin/**").hasAnyRole("ADMIN","DBA")
        .antMatchers("/dba/**").hasAnyRole("DBA")
                //其他页面为需要登录之后
        .anyRequest().authenticated()
                //关闭防御跨站伪造请求,在使用thymeleaf中使用form表单发起请求,如果没有使用th:action则不会报错误,而使用action则会报错,关闭则都不会报错
        .and().csrf().disable()
        .formLogin()
                //登录节点配置
        .loginPage("/login")
                //登录成功后负责跳转设置重定向路径的配置
        .successHandler(authenticationSuccessHandler)
                //失败后跳转的页面
                .failureForwardUrl("/myError")
                //配置登录参数
        .usernameParameter("loginname").passwordParameter("password")
        .and()
        .logout().permitAll()
        .and()
                //配置由权限不足等引起的拒绝跳转的url
        .exceptionHandling().accessDeniedPage("/accessDenied");
    }
}
