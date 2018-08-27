package it.lei.day5.conponent;

import it.lei.day3.component.AppAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class AppSecurityConfiger extends WebSecurityConfigurerAdapter {
    @Autowired
    private  IpAuthenticationProvider ipAuthenticationProvider;
    @Autowired
    private LoginUrlAuthenticationEntryPoint loginUrlAuthenticationEntryPoint;
    @Autowired
    private AppAuthenticationSuccessHandler appAuthenticationSuccessHandler;
    @Bean
    public  IpAuthenticationProvider ipAuthenticationProvider(){
        return  new IpAuthenticationProvider();
    }

    /**
     * 这里会覆盖configure中的SuccessHandler和异常的配置
     * @param authenticationManager
     * @return
     */
    IpAuthenticationProcessingFilter ipAuthenticationProcessingFilter(AuthenticationManager authenticationManager){
        IpAuthenticationProcessingFilter ipAuthenticationProcessingFilter = new IpAuthenticationProcessingFilter();
        //添加认证管理器
        ipAuthenticationProcessingFilter.setAuthenticationManager(authenticationManager);
        //添加成功处理器
        ipAuthenticationProcessingFilter.setAuthenticationSuccessHandler(appAuthenticationSuccessHandler);
        //添加失败处理器
        ipAuthenticationProcessingFilter.setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler("/myError"));
        return  ipAuthenticationProcessingFilter;
    }
    @Bean
    LoginUrlAuthenticationEntryPoint loginUrlAuthenticationEntryPoint(){
        LoginUrlAuthenticationEntryPoint loginUrlAuthenticationEntryPoint = new LoginUrlAuthenticationEntryPoint("/IpLogin");
        return  loginUrlAuthenticationEntryPoint;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(ipAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("开始配置权限...............5");
        //过滤静态登录页面和静态资源,这里的/路径都是指的是controller请求路径并不是指静态页面路径
        http.authorizeRequests()
                .antMatchers("/ipLogin","/js/**").permitAll()
                .antMatchers("/","/home").hasRole("USER")
                .antMatchers("/admin/**").hasAnyRole("ADMIN","DBA")
                .antMatchers("/dba/**").hasAnyRole("DBA")
                .anyRequest().authenticated()
                .and().csrf().disable()
                .formLogin()
                .loginPage("/ipLogin")
               //.successHandler(appAuthenticationSuccessHandler)
               .failureForwardUrl("/myError")
                .and()
                .logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/accessDenied");
        //这里的得注意插入过滤器的位置
        http.addFilterBefore(ipAuthenticationProcessingFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class);
    }
}
