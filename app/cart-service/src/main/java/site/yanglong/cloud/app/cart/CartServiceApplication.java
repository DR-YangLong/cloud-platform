package site.yanglong.cloud.app.cart;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * 购物车服务
 */
@ComponentScan(basePackages = "site.yanglong.cloud.app")
@EnableFeignClients
@SpringCloudApplication
public class CartServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CartServiceApplication.class, args);
    }
}
