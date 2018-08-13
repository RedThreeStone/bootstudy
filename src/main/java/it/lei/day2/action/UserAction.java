package it.lei.day2.action;

import it.lei.day1.entity.User;
import it.lei.day2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller("mybatisUserAction")
@RequestMapping("/newUserAction")
public class UserAction {
    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping("/addUser")
    public Map addUser(@RequestBody  User user){
        Map resultMap=new HashMap();
        userService.addUser(user);
        System.out.println(66666666);
        resultMap.put("user",user);
        resultMap.put("flag",true);
        return  resultMap;
    }
}
