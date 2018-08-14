package it.lei.day3.action;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author  lei
 */
@Controller
public class SecurityController {
    @RequestMapping("/")
    public String index(ModelAndView modelAndView){

        return "index";
    }
    @RequestMapping("/login")
    public String login(ModelAndView modelAndView){
        System.out.println("login");
        return "login";
    }
    @RequestMapping("/myError")
    public String myError(ModelAndView modelAndView){
        System.out.println("myError");
        return "myError";
    }
    @RequestMapping("/admin")
    public String admin(ModelAndView modelAndView){
        System.out.println("admin");
        modelAndView.addObject("user",getUserName());
        modelAndView.addObject("roles",getAuthority());
        return "admin";
    }
    @RequestMapping("/dba")
    public String dba(ModelAndView modelAndView){
        System.out.println("dba");
        modelAndView.addObject("user",getUserName());
        modelAndView.addObject("roles",getAuthority());
        return "dba";
    }
    @RequestMapping("/accessDenied")
    public String accessDenied(ModelAndView modelAndView){
        System.out.println("accessDenied");
        modelAndView.addObject("user",getUserName());
        modelAndView.addObject("roles",getAuthority());
        return "accessDenied";
    }
    @RequestMapping("/logout")
    public String logout(ModelAndView modelAndView, HttpServletRequest request, HttpServletResponse response){
        System.out.println("logout");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication!=null){
            new SecurityContextLogoutHandler().logout(request,response,authentication);
        }
        return "redirect:login?logout=1";
    }
    public String getUserName(){
        String userName= SecurityContextHolder.getContext().getAuthentication().getName();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(principal.toString());
        Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
        System.out.println(details);
        return  userName;
    }
    public String getAuthority(){
        Collection<? extends GrantedAuthority> grantedAuthorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        ArrayList<String> pers = new ArrayList<>();
        for (GrantedAuthority grantedAuthority:grantedAuthorities){
            pers.add(grantedAuthority.getAuthority());
        }
        return  pers.toString();
    }
}
