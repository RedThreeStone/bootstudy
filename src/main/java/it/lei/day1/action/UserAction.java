package it.lei.day1.action;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller()
@RequestMapping("/user")
public class UserAction {
    @CrossOrigin(origins = "http://localhost:5666")
    @RequestMapping("/login.do")
    @ResponseBody
    public String login(Map map, HttpServletResponse response){
        map.put("success",true);
        return JSONObject.toJSONString(map);
    }
}
