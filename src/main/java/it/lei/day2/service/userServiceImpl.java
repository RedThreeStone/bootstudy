package it.lei.day2.service;

import com.github.pagehelper.Page;
import it.lei.day1.entity.User;
import it.lei.day1.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class userServiceImpl  implements  UserService{
    @Autowired
    private UserMapper userMapper;
    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    public User addUser(User user) {
        userMapper.insertSelective(user);
        //int a=2/0;
        return user;
    }
}
