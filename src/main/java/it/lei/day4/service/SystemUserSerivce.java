package it.lei.day4.service;


import it.lei.day1.entity.Permission;
import it.lei.day1.entity.User;
import it.lei.day1.mapper.PermissionMapper;
import it.lei.day1.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SystemUserSerivce implements UserDetailsService {
    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User myUser =userMapper.selectUserByUsername(s);
        List<Permission> permissions = permissionMapper.selectPermissionsByUserName(s);
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for(Permission permission:permissions){
            String permissionCode = permission.getPermissionCode();
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permissionCode);
            grantedAuthorities.add(grantedAuthority);
        }
        return new org.springframework.security.core.userdetails.User(s,myUser.getPassword(),grantedAuthorities);
    }
}
