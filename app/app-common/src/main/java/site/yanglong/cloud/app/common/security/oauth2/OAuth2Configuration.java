package site.yanglong.cloud.app.common.security.oauth2;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestVariableDefault;
import feign.RequestInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

import javax.servlet.Filter;

/**
 * functional describe: 使用Feign时，传递OAuth2认证信息。
 *
 * @author DR.YangLong [410357434@163.com]
 * @version 1.0    2018/9/13
 */
@EnableConfigurationProperties
@Configuration
public class OAuth2Configuration {
    private static final Logger logger = LoggerFactory.getLogger(OAuth2Configuration.class);
    /**
     * HystrixRequestVariable
     */
    private static final HystrixRequestVariableDefault<Authentication> authentication = new HystrixRequestVariableDefault<>();

    /**
     * HystrixRequestVariable
     *
     */
    static HystrixRequestVariableDefault<Authentication> getInstance() {
        return authentication;
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            Authentication token = OAuth2Configuration.getInstance().get();
            if (null != token) {
                logger.debug("Get Authorization from Filter:" + token);
                requestTemplate.header(OAuth2FeignRequestInterceptor.AUTHORIZATION, OAuth2FeignRequestInterceptor.BEARER + " " + ((OAuth2AuthenticationDetails) token.getDetails()).getTokenValue());
            } else {
                logger.debug("Can't Get Authorization.");
            }
        };
    }


    @Bean
    @RefreshScope
    @ConfigurationProperties(prefix = "security.oauth2.client")
    public ClientCredentialsResourceDetails clientCredentialsResourceDetails() {
        return new ClientCredentialsResourceDetails();
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AuthenticationHystrixFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(0);
        return registrationBean;
    }


    @Bean
    public OAuth2RestTemplate oAuth2RestTemplate() {
        return new OAuth2RestTemplate(clientCredentialsResourceDetails());
    }
}
