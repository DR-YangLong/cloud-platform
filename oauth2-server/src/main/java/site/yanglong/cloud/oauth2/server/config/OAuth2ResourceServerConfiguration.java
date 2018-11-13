package site.yanglong.cloud.oauth2.server.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.util.StringUtils;

/**
 * functional describe: OAuth2 server对外提供服务时，作为资源服务器
 *
 * @author DR.YangLong [410357434@163.com]
 * @version 1.0    2018/8/27
 */
@Configuration
@EnableResourceServer
public class OAuth2ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
    @Value("${spring.application.name}")
    private String resourceId;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        super.configure(resources);
        resources.resourceId(resourceId);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.requestMatcher((request) -> {
                    String auth = request.getHeader("Authorization");
                    return auth != null &&
                            auth.toLowerCase().contains("bearer") ||
                            !StringUtils.isEmpty(request.getParameter("access_token"));
                }).authorizeRequests().anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
        //.exceptionHandling()
        //自定义返回无权限信息
        //.accessDeniedHandler();
    }
}
