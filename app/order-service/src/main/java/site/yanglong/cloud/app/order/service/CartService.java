package site.yanglong.cloud.app.order.service;

import site.yanglong.cloud.app.order.service.impl.CartServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * functional describe:调用Cart service
 *
 * @author DR.YangLong [410357434@163.com]
 * @version 1.0    2018/9/13
 */
@FeignClient(value = "cart-service",fallback = CartServiceFallback.class)
public interface CartService {

    @RequestMapping(value = "/cart/info",method = RequestMethod.GET)
    String getCart(@RequestParam("cartId") String cartId);
}
