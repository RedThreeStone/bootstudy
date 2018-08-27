package it.lei.day5.conponent;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class IpAuthenticationToken extends AbstractAuthenticationToken {
    /**
     *  对比UsernamePasswordAuthenticationToken 中的账号principal 密码credentials
     */
    private  String ip;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public  IpAuthenticationToken(String ip){
        super(null);
        this.ip=ip;
        //认证前
        super.setAuthenticated(false);
    }
    public  IpAuthenticationToken(String ip, Collection< ? extends GrantedAuthority> authorities){
        super(authorities);
        this.ip=ip;
        //认证后
        super.setAuthenticated(true);
    }
    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this.ip;
    }
}
