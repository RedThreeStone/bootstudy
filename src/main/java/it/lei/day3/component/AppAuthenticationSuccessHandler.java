package it.lei.day3.component;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class AppAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("开始根据权限判断将要跳转的页面...............");
        String url=determineTargetUrl(authentication);
        System.out.println(url);
        redirectStrategy.sendRedirect(request,response,url);
    }
    protected String determineTargetUrl(Authentication authentication){
        String url="";
        Collection<? extends GrantedAuthority> grantedAuthorities = authentication.getAuthorities();
        List<String> roles=new ArrayList<>();
        for(GrantedAuthority grantedAuthority:grantedAuthorities){
            roles.add(grantedAuthority.getAuthority());
        }
        //这里返回路径重定向,同样返回的是controller请求路径,并不是指静态文件的路径
        if(roles.contains("ROLE_ADMIN")){
            return  "/admin";
        }else if(roles.contains("ROLE_DBA")){
            return  "/dba";
        }else if (roles.contains("ROLE_USER")){
            return "/home";
        }else {
            return  "/accessDenied";
        }
    }
}
