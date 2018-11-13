package site.yanglong.cloud.oauth2.server.config.extention.sms.provider;

import site.yanglong.cloud.oauth2.server.config.extention.ExtentionAuthenticationToken;
import site.yanglong.cloud.oauth2.server.config.extention.sms.Exeception.SMSBadParamException;
import site.yanglong.cloud.oauth2.server.config.extention.sms.Exeception.SMSCaptchaException;
import site.yanglong.cloud.oauth2.server.service.RedisCacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * functional describe:短信验证器
 *
 * @author DR.YangLong [410357434@163.com]
 * @version 1.0    2018/8/29
 */
@Slf4j
public class SMSAuthenticationProvider implements AuthenticationProvider {
    /**
     * 短信验证码cacheName
     */
    @NotNull
    private String smsCacheName = "SMS_LOGIN";
    /**
     * 支持的登录类型
     */
    public static final String SUPPORT_TYPE = "sms";
    private UserDetailsChecker preAuthenticationChecks = new DefaultPreAuthenticationChecks();
    private MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();
    @NotNull
    private UserDetailsService userDetailsService;
    @NotNull
    private RedisCacheService redisCacheService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        ExtentionAuthenticationToken token = (ExtentionAuthenticationToken) authentication;
        if(token.verify()) {
            //判断是否支持认证
            if (Objects.equals(SUPPORT_TYPE, token.getTokenType())) {
                UserDetails user = userDetailsService.loadUserByUsername(token.getPrincipal().toString());
                preAuthenticationChecks.check(user);
                //获取验证码
                String cachedCode = redisCacheService.get(smsCacheName, user.getUsername(), String.class, false);
                String code = token.getCredentials().toString();
                if (Objects.equals(code, cachedCode)) {
                    //构建已认证token
                    UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(user.getUsername(),
                            authentication.getCredentials(), user.getAuthorities());
                    result.setDetails(authentication.getDetails());
                    //发出通知，清除验证码缓存
                    redisCacheService.del(smsCacheName, user.getUsername());
                    return result;
                }
                log.debug("sms captcha is not equal that in the server side!");
                throw new SMSCaptchaException("captcha is bad");
            }
        }
        log.debug("can't receive the sms loin request parameter!");
        throw new SMSBadParamException("Principal and Credentials is needed");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return ExtentionAuthenticationToken.class.isAssignableFrom(authentication);
    }


    public SMSAuthenticationProvider(@NotNull UserDetailsService userDetailsService, @NotNull RedisCacheService redisCacheService) {
        this.userDetailsService = userDetailsService;
        this.redisCacheService = redisCacheService;
    }

    public SMSAuthenticationProvider(@NotNull String smsCacheName, @NotNull UserDetailsService userDetailsService, @NotNull RedisCacheService redisCacheService) {
        this.smsCacheName = smsCacheName;
        this.userDetailsService = userDetailsService;
        this.redisCacheService = redisCacheService;
    }

    public SMSAuthenticationProvider() {
    }

    public SMSAuthenticationProvider(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    private class DefaultPreAuthenticationChecks implements UserDetailsChecker {
        public void check(UserDetails user) {
            if (!user.isAccountNonLocked()) {
                log.debug("User account is locked");

                throw new LockedException(messages.getMessage(
                        "AbstractUserDetailsAuthenticationProvider.locked",
                        "User account is locked"));
            }

            if (!user.isEnabled()) {
                log.debug("User account is disabled");

                throw new DisabledException(messages.getMessage(
                        "AbstractUserDetailsAuthenticationProvider.disabled",
                        "User is disabled"));
            }

            if (!user.isAccountNonExpired()) {
                log.debug("User account is expired");

                throw new AccountExpiredException(messages.getMessage(
                        "AbstractUserDetailsAuthenticationProvider.expired",
                        "User account has expired"));
            }
        }
    }

    private class DefaultPostAuthenticationChecks implements UserDetailsChecker {
        public void check(UserDetails user) {
            if (!user.isCredentialsNonExpired()) {
                log.debug("User account credentials have expired");

                throw new CredentialsExpiredException(messages.getMessage(
                        "AbstractUserDetailsAuthenticationProvider.credentialsExpired",
                        "User credentials have expired"));
            }
        }
    }

    public String getSmsCacheName() {
        return smsCacheName;
    }

    public void setSmsCacheName(String smsCacheName) {
        this.smsCacheName = smsCacheName;
    }

    public RedisCacheService getRedisCacheService() {
        return redisCacheService;
    }

    public void setRedisCacheService(RedisCacheService redisCacheService) {
        this.redisCacheService = redisCacheService;
    }
}
