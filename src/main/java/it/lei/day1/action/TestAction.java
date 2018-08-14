package it.lei.day1.action;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class TestAction {
    @RequestMapping("/index.do")
    public ModelAndView index(ModelAndView modelAndView){
        System.out.println("要去index啦");
        modelAndView.addObject("message","hello");
        modelAndView.addObject("value",1);
        List<String> messages=new LinkedList<>();
        messages.add("小明");
        messages.add("小梁");
        messages.add("蔡师傅");
        modelAndView.addObject("messageList",messages);
        modelAndView.setViewName("index");
        modelAndView.addObject("money",13.66);
        modelAndView.addObject("now",new Date());
        return modelAndView;
    }
    @PostMapping("/login")
    public ModelAndView login(ModelAndView modelAndView, RedirectAttributes redirectAttributes){
        modelAndView.setViewName("redirect:/main");
        modelAndView.addObject("name","磊子");
        //modelAndView就好像request对象,转发之后就没有了modealAndView ,所以需要下面这个对象存到session中
        redirectAttributes.addFlashAttribute("name2","磊2222");
        return  modelAndView;
    }
    @RequestMapping("/main")
    public ModelAndView main(ModelAndView modelAndView){
        modelAndView.addObject("message","helloEverybody");
        modelAndView.setViewName("main");
        return modelAndView;
    }
    @RequestMapping("/home")
    public ModelAndView home(ModelAndView modelAndView){
        modelAndView.addObject("message","helloEverybody");
        modelAndView.setViewName("home");
        return modelAndView;
    }
}
