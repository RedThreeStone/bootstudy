package it.lei.day1.action;

import it.lei.day1.entity.Home;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/json")
public class JsonAction {
    @RequestMapping("/getHome")
    public Home getHome(@RequestBody String json){
        System.out.println(json    );
        Home myHome=new Home();
        myHome.setHomeName("我最爱的家");
        myHome.setHomeAge(1);
        return  myHome;
    }
}
