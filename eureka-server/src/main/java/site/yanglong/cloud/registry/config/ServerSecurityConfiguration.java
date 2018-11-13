package site.yanglong.cloud.registry.config;

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
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * functional describe:基于内存的WEB SECURITY配置。
 *
 * @author DR.YangLong [410357434@163.com]
 * @version 1.0    2018/8/22
 */
@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ServerSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Value("${registry-security.username}")
    private String userName;
    @Value("${registry-security.password}")
    private String password;
    @Value("${registry-security.id}")
    private String encoderId;
    @Value("${monitor-security.username}")
    private String monitor_username;
    @Value("${monitor-security.password}")
    private String monitor_password;
    @Value("${monitor-security.id}")
    private String monitor_encoderId;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser(userName).password("{"+encoderId+"}"+password).roles("user")
                .and().withUser(monitor_username).password("{" + monitor_encoderId + "}" + monitor_password).roles("user");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //.exceptionHandling().authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
        http.httpBasic()
                .and()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
        web.ignoring().antMatchers("/fav*");
    }

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
