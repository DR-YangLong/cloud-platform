package site.yanglong.cloud.oauth2.server.config.extention;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * functional describe:扩展验证的Token
 *
 * @author DR.YangLong [410357434@163.com]
 * @version 1.0    2018/8/28
 */
public class ExtentionAuthenticationToken extends AbstractAuthenticationToken {
    private static final long serialVersionUID = -7318553618785688780L;
    //用户名
    private final Object principal;
    //凭证，可以是短信验证码等
    private Object credentials;
    //此Token的类型
    private String tokenType;

    public ExtentionAuthenticationToken(Object principal, Object credentials, String tokenType) {
        super(null);
        this.principal = principal;
        this.credentials = credentials;
        this.tokenType = tokenType;
        setAuthenticated(false);
    }

    public ExtentionAuthenticationToken(Object principal, Object credentials, String tokenType,
                                        Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        this.tokenType = tokenType;
        super.setAuthenticated(true);
    }

    public Object getCredentials() {
        return this.credentials;
    }

    public Object getPrincipal() {
        return this.principal;
    }

    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException(
                    "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }

        super.setAuthenticated(false);
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        credentials = null;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public boolean verify() {
        return null != principal && null != credentials;
    }
}
