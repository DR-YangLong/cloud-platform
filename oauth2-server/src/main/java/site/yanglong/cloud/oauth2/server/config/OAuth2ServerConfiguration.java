package site.yanglong.cloud.oauth2.server.config;

import site.yanglong.cloud.oauth2.server.config.extention.sms.granter.SMSTokenGranter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.security.KeyPair;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

/**
 * functional describe: OAuth2服务
 *
 * @author DR.YangLong [410357434@163.com]
 * @version 1.0    2018/8/27
 */
@Slf4j
@Configuration
@EnableAuthorizationServer
public class OAuth2ServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    @Qualifier("customUserDetailService")
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    @Qualifier("oauthClientDetailsServiceImpl")
    private ClientDetailsService clientDetailsService;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security//.addTokenEndpointAuthenticationFilter()
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients();
        //图片验证码核对
        //.addTokenEndpointAuthenticationFilter()

    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //数据库based
        clients.withClientDetails(clientDetailsService);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)//若无，refresh_token会有UserDetailsService is required错误
                .tokenStore(tokenStore())
                //登录异常自定义信息转换
                //.exceptionTranslator()
                .tokenEnhancer(jwtAccessTokenConverter())
                .setClientDetailsService(clientDetailsService);//定义token存储
        endpoints.tokenGranter(tokenGranter(endpoints));
    }

    /**
     * 加入自定义TokenGranter，进行认证
     *
     * @param endpoints
     * @return
     */
    private TokenGranter tokenGranter(final AuthorizationServerEndpointsConfigurer endpoints) {
        //默认的Granter
        List<TokenGranter> granters = new ArrayList<>(Collections.singletonList(endpoints.getTokenGranter()));
        //加入自定义的Granter
        granters.add(new SMSTokenGranter(authenticationManager, endpoints.getTokenServices(), endpoints.getClientDetailsService(), endpoints.getOAuth2RequestFactory()));
        return new CompositeTokenGranter(granters);
    }

    /**
     * 令牌存储实现，JWT不存储令牌
     *
     * @return token存储实现
     */
    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }


    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        return defaultTokenServices;
    }

    /**
     * 令牌转换器，用来生成，解析token
     *
     * @return 令牌转换器
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new CustomJwtAccessTokenConverter();
        KeyStoreKeyFactory storeKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("jwt_key.keystore"), "123456".toCharArray());
        KeyPair keyPair = storeKeyFactory.getKeyPair("jwt_key");
        log.info("public key:\n-----BEGIN PUBLIC KEY-----\n"
                + new String(Base64.getEncoder().encode(keyPair.getPublic().getEncoded())) +
                "\n-----END PUBLIC KEY-----");
        converter.setKeyPair(keyPair);
        return converter;
    }


}
