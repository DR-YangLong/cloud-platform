package site.yanglong.cloud.app.common.security.oauth2;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import java.io.IOException;

/**
 * functional describe:传递认证信息到OAuth2Configuration中
 *
 * @author DR.YangLong [410357434@163.com]
 * @version 1.0    2018/9/13
 */
public class AuthenticationHystrixFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HystrixRequestContext.initializeContext();
        SecurityContext securityContext = SecurityContextHolder.getContext();
        if (null != securityContext) {
            Authentication authentication = securityContext.getAuthentication();
            OAuth2Configuration.getInstance().set(authentication);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
