package it.lei.day5.conponent;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class IpAuthenticationProvider implements AuthenticationProvider {
    final static Map<String , SimpleGrantedAuthority> ipAuthorityMap=new ConcurrentHashMap<>();
    static {
        ipAuthorityMap.put("127.0.0.1",new SimpleGrantedAuthority("ROLE_ADMIN"));
        ipAuthorityMap.put("10.236.69.103",new SimpleGrantedAuthority("ROLE_USER"));
        ipAuthorityMap.put("127.0.0.5",new SimpleGrantedAuthority("ROLE_DBA"));
    }
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //IpAuthenticationToken 是authentication的实现类
        IpAuthenticationToken ipAuthenticationToken = (IpAuthenticationToken) authentication;
        String ip = ipAuthenticationToken.getIp();
        SimpleGrantedAuthority simpleGrantedAuthority = ipAuthorityMap.get(ip);
        if (simpleGrantedAuthority!=null){
          return  new IpAuthenticationToken(ip, Arrays.asList(simpleGrantedAuthority));
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        //只支持IPAuthentication进行认证
        return (IpAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
