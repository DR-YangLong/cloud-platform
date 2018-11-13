package site.yanglong.cloud.oauth2.server.config.extention.sms.granter;

import site.yanglong.cloud.oauth2.server.config.extention.ExtentionAuthenticationToken;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import site.yanglong.cloud.oauth2.server.config.extention.sms.provider.SMSAuthenticationProvider;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * functional describe:当请求token时，带有grant_type为sms时，进行短信登录，并授权。
 *
 * @author DR.YangLong [410357434@163.com]
 * @version 1.0    2018/8/30
 */
public class SMSTokenGranter extends AbstractTokenGranter {
    private final AuthenticationManager authenticationManager;
    private static final String GRANT_TYPE = SMSAuthenticationProvider.SUPPORT_TYPE;
    //手机号参数名
    private String mobileParameter="mobile";
    //验证码参数名
    private String smsCodeParameter="validate";

    public SMSTokenGranter(AuthenticationManager authenticationManager,
                           AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory) {
        this(authenticationManager, tokenServices, clientDetailsService, requestFactory, GRANT_TYPE);
    }

    private SMSTokenGranter(AuthenticationManager authenticationManager, AuthorizationServerTokenServices tokenServices,
                              ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory, String grantType) {
        super(tokenServices, clientDetailsService, requestFactory, grantType);
        this.authenticationManager = authenticationManager;
    }

    public SMSTokenGranter(AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory, String grantType, AuthenticationManager authenticationManager, String mobileParameter, String smsCodeParameter) {
        super(tokenServices, clientDetailsService, requestFactory, grantType);
        this.authenticationManager = authenticationManager;
        this.mobileParameter = mobileParameter;
        this.smsCodeParameter = smsCodeParameter;
    }

    @Override
    protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {

        Map<String, String> parameters = new LinkedHashMap<String, String>(tokenRequest.getRequestParameters());
        String mobile = parameters.get(mobileParameter);
        String validate = parameters.get(smsCodeParameter);

        Authentication userAuth = new ExtentionAuthenticationToken(mobile, validate, SMSAuthenticationProvider.SUPPORT_TYPE);
        ((AbstractAuthenticationToken) userAuth).setDetails(parameters);
        try {
            userAuth = authenticationManager.authenticate(userAuth);
        } catch (AccountStatusException | BadCredentialsException ase) {
            throw new InvalidGrantException(ase.getMessage());
        }
        if (userAuth == null || !userAuth.isAuthenticated()) {
            throw new InvalidGrantException("Could not authenticate user: " + mobile);
        }

        OAuth2Request storedOAuth2Request = getRequestFactory().createOAuth2Request(client, tokenRequest);
        return new OAuth2Authentication(storedOAuth2Request, userAuth);
    }

}
