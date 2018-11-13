package site.yanglong.cloud.config.server;

import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

/**
 * config server:使用数据库管理集群配置。配置更新时先入redis hash并异步入数据库，成功后通知bus。
 */
@EnableConfigServer
@EnableEurekaClient
@SpringBootApplication(exclude = {FreeMarkerAutoConfiguration.class})
public class CloudConfigServer {
    public static void main(String[] args) {
        SpringApplication.run(CloudConfigServer.class,args);
    }

    @Bean
    public LogicSqlInjector logicSqlInjector(){
        return new LogicSqlInjector();
    }
}

