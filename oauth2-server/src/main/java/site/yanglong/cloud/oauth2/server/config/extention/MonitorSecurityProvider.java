package site.yanglong.cloud.oauth2.server.config.extention;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.Objects;

/**
 * functional describe: spring boot admin 监控验证。
 * <p>由于使用外部环境变量统一为所有微服务设置监控账号密码，所以提供此验证器单独验证。
 *
 * @author DR.YangLong [410357434@163.com]
 * @version 1.0    2018/9/11
 */
public class MonitorSecurityProvider implements AuthenticationProvider {
    //用户名
    private final String username;
    //密码
    private final String password;
    //权限
    private final String authority;
    //密码验证器
    private final PasswordEncoder passwordEncoder;

    public MonitorSecurityProvider(String username, String password, String authority, PasswordEncoder passwordEncoder) {
        this.username = username;
        this.password = password;
        this.authority = authority;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        String name = token.getPrincipal() == null ? null : token.getPrincipal().toString();
        String pass = token.getCredentials() == null ? null : token.getCredentials().toString();
        if (Objects.equals(name, username) && passwordEncoder.matches(pass, password)) {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, pass, Collections.singletonList(new SimpleGrantedAuthority(authority)));
            authenticationToken.setDetails(token.getDetails());
            return authenticationToken;
        }
        throw new UsernameNotFoundException("bad username and password");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
