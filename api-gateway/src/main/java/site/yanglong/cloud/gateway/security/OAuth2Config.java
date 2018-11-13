package site.yanglong.cloud.gateway.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.AntPathMatcher;

/**
 * functional describe:
 *
 * @author DR.YangLong [410357434@163.com]
 * @version 1.0    2018/7/23
 */
@Configuration
@EnableOAuth2Sso
public class OAuth2Config extends WebSecurityConfigurerAdapter {
    @Value("${monitor-security.username}")
    private String monitor_username;
    @Value("${monitor-security.password}")
    private String monitor_password;
    @Value("${monitor-security.id}")
    private String monitor_encoderId;
    public static final String MONITOR_ANT_PATH = "/actuator/**";

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser(monitor_username).password("{" + monitor_encoderId + "}" + monitor_password).roles("user");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(MONITOR_ANT_PATH)
                .authenticated()
                .anyRequest().permitAll()
                .and().csrf().disable()
                .httpBasic();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
        RequestMatcher requestMatcher = (request) -> {
            String path = request.getServletPath();
            AntPathMatcher pathMatcher = new AntPathMatcher();
            return !pathMatcher.match(MONITOR_ANT_PATH, path);
        };
        web.ignoring().requestMatchers(requestMatcher).antMatchers("/fav**");
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
