package site.yanglong.cloud.oauth2.server.config;

import site.yanglong.cloud.oauth2.server.config.extention.MonitorSecurityProvider;
import site.yanglong.cloud.oauth2.server.config.extention.sms.provider.SMSAuthenticationProvider;
import site.yanglong.cloud.oauth2.server.service.RedisCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * functional describe: security配置
 *
 * @author DR.YangLong [410357434@163.com]
 * @version 1.0    2018/8/27
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class OAuth2WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Value("${monitor-security.username}")
    private String monitor_username;
    @Value("${monitor-security.password}")
    private String monitor_password;
    @Value("${monitor-security.id}")
    private String monitor_encoderId;

    @Autowired
    @Qualifier("customUserDetailService")
    private UserDetailsService userDetailsService;
    @Autowired
    private RedisCacheService redisCacheService;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //用于生成AuthenticationManager，因为OAuth2也共用，所以这里设置的authenticationProvider在OAuth2中也使用
        auth.authenticationProvider(new SMSAuthenticationProvider(userDetailsService, redisCacheService))
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder())
                .and()
                .authenticationProvider(new MonitorSecurityProvider(monitor_username, "{" + monitor_encoderId + "}" + monitor_password, "user", passwordEncoder()));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //自定义filter
        http//.addFilterBefore(new SMSAuthenticationFilter(),UsernamePasswordAuthenticationFilter.class)
                .httpBasic()
                .and()
                .formLogin().permitAll()
                .and()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .csrf().disable();
        //.exceptionHandling()
        //自定义返回无权限信息
        //.accessDeniedHandler();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
        web.ignoring().antMatchers("/fav*");
    }

    /**
     * 将此AuthenticationManager注入到OAuth2使用，使认证互通
     *
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}