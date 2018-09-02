package it.lei.day6.action;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestEndPoint {
    @GetMapping("/getProductInfo/{id}")
    public  String getProductInfo(@PathVariable String id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getDetails());
        System.out.println(authentication.getPrincipal());
        return  "product id:" +id;
    }

    @GetMapping("/getOrderInfo/{id}")
    public  String getOrderInfo(@PathVariable String id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getDetails());
        System.out.println(authentication.getPrincipal());
        return  "order id:" +id;
    }
}
