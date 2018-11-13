package site.yanglong.cloud.oauth2.server;

import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

/**
 * OAuth2认证服务器
 */
@RefreshScope
@EnableEurekaClient
@SpringBootApplication(exclude = {FreeMarkerAutoConfiguration.class,RedisAutoConfiguration.class})
public class OAuth2Server {
    public static void main(String[] args) {
        SpringApplication.run(OAuth2Server.class, args);
    }

    @Bean
    public LogicSqlInjector logicSqlInjector(){
        return new LogicSqlInjector();
    }
}
