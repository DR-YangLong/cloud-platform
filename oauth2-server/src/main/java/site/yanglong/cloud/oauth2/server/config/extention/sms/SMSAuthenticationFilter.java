package site.yanglong.cloud.oauth2.server.config.extention.sms;

import site.yanglong.cloud.oauth2.server.config.extention.ExtentionAuthenticationToken;
import site.yanglong.cloud.oauth2.server.config.extention.ExtentionConst;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * functional describe:从请求中获取登录方式，并构建Token。适用Spring security单独部署时使用。
 * 如果含有{@see ExtentionConst.TOKEN_TYPE_HEADER}，则构建{@see ExtentionAuthenticationToken}
 * <p>HttpSecurity.addFilterBefore(new SMSAuthenticationFilter(),UsernamePasswordAuthenticationFilter.class)。</p>
 *
 * @author DR.YangLong [410357434@163.com]
 * @version 1.0    2018/8/29
 */
public class SMSAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    private static final String SECURITY_FORM_USERNAME_KEY = "mobile";
    private static final String SECURITY_FORM_SMS_KEY = "validate";

    //手机号参数名
    private String usernameParameter = SECURITY_FORM_USERNAME_KEY;
    //验证码参数名
    private String smsParameter = SECURITY_FORM_SMS_KEY;
    //登录类型在header中的key
    private String loginTypeHeader = ExtentionConst.TOKEN_TYPE_HEADER;
    //仅支持post
    private boolean postOnly = true;

    public SMSAuthenticationFilter() {
        this(new AntPathRequestMatcher("/login", "POST"));
    }

    public SMSAuthenticationFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }

    public SMSAuthenticationFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
        super(requiresAuthenticationRequestMatcher);
    }

    public SMSAuthenticationFilter(String defaultFilterProcessesUrl, String usernameParameter, String smsParameter, String loginTypeHeader, boolean postOnly) {
        super(defaultFilterProcessesUrl);
        this.usernameParameter = usernameParameter;
        this.smsParameter = smsParameter;
        this.loginTypeHeader = loginTypeHeader;
        this.postOnly = postOnly;
    }

    public SMSAuthenticationFilter(RequestMatcher requiresAuthenticationRequestMatcher, String usernameParameter, String smsParameter, String loginTypeHeader, boolean postOnly) {
        super(requiresAuthenticationRequestMatcher);
        this.usernameParameter = usernameParameter;
        this.smsParameter = smsParameter;
        this.loginTypeHeader = loginTypeHeader;
        this.postOnly = postOnly;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        if (postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }
        String loginType = request.getHeader(loginTypeHeader);
        if (StringUtils.isEmpty(loginType)) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported normal login");
        }
        String username = request.getParameter(usernameParameter);
        String sms = request.getParameter(smsParameter);
        if (username == null) {
            username = "";
        }
        if (sms == null) {
            sms = "";
        }
        username = username.trim();
        ExtentionAuthenticationToken authRequest = new ExtentionAuthenticationToken(
                username, sms, loginType);
        setDetails(request, authRequest);

        return this.getAuthenticationManager().authenticate(authRequest);
    }


    private void setDetails(HttpServletRequest request,
                            ExtentionAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }


    public String getUsernameParameter() {
        return usernameParameter;
    }

    public void setUsernameParameter(String usernameParameter) {
        this.usernameParameter = usernameParameter;
    }

    public String getSmsParameter() {
        return smsParameter;
    }

    public void setSmsParameter(String smsParameter) {
        this.smsParameter = smsParameter;
    }

    public String getLoginTypeHeader() {
        return loginTypeHeader;
    }

    public void setLoginTypeHeader(String loginTypeHeader) {
        this.loginTypeHeader = loginTypeHeader;
    }

    public boolean isPostOnly() {
        return postOnly;
    }

    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }
}
