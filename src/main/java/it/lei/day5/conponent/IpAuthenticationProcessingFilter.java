package it.lei.day5.conponent;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class IpAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {
     IpAuthenticationProcessingFilter(){
        super(new AntPathRequestMatcher("/ipVerify"));
    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
         String hostIp=request.getRemoteHost();
         return getAuthenticationManager().authenticate(new IpAuthenticationToken(hostIp));
    }
}
