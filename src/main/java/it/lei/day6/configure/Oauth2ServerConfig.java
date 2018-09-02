package it.lei.day6.configure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

@Configuration
public class Oauth2ServerConfig {
    private static  final String DEMO_RESOURCE_ID="order";
    @Configuration
    @EnableResourceServer
    protected static class ResourceServerConfig extends ResourceServerConfigurerAdapter {

        public ResourceServerConfig() {
            super();
        }

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) throws Exception {

            resources.resourceId(DEMO_RESOURCE_ID).stateless(true);
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                    .and()
                    .requestMatchers().anyRequest()
                    .and()
                    .anonymous()
                    .and()
                    .authorizeRequests()
                    .antMatchers("/getOrderInfo/**").authenticated();
        }
    }
    @Configuration
    @EnableAuthorizationServer
    protected  static  class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter{

        @Autowired
        private PasswordEncoder passwordEncoder;
        @Autowired
        private RedisConnectionFactory redisConnectionFactory;
        @Autowired
        private AuthenticationManager authenticationManager;

        @Bean
        protected  PasswordEncoder passwordEncoder(){
            return  new BCryptPasswordEncoder();
        }
        public AuthorizationServerConfig() {
            super();
        }

        @Override
        /**
         * 配置authorizationServer安全认证的相关信息,创建clientCredentialsTokenEndPointFilter核心过滤器
         */
        public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
            security.allowFormAuthenticationForClients();
        }

        @Override
        /**
         * 配置oauth2的客户端相关信息
         */
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            clients.inMemory().withClient("client1")
                    .resourceIds(DEMO_RESOURCE_ID)
                    .authorizedGrantTypes("client_credentials","refresh_token")
                    .scopes("select")
                    .authorities("ROLE_CLIENT")
                    //系统默认只接受加密的密码
                    .secret(passwordEncoder.encode("123456"))
                    .and().withClient("cilent2")
                    .resourceIds(DEMO_RESOURCE_ID)
                    .authorizedGrantTypes("password","refresh_token")
                    .scopes("select")
                    .authorities("client")
                    //a62d747e-91fb-42c5-8857-b47243179ecd
                    .secret(passwordEncoder.encode("123456"));
        }

        @Override
        /**
         * 配置AuthorizationServerEndpointsConfigurer众多相关类，包括配置身份认证器，配置认证方式，TokenStore，TokenGranter，OAuth2RequestFactory
         */
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
            endpoints.tokenStore(new RedisTokenStore(redisConnectionFactory))
                    .authenticationManager(authenticationManager);
        }

    }
}
