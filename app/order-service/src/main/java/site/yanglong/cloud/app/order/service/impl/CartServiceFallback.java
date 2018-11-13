package site.yanglong.cloud.app.order.service.impl;

import site.yanglong.cloud.app.order.service.CartService;
import org.springframework.stereotype.Component;

/**
 * functional describe:降级实现
 *
 * @author DR.YangLong [410357434@163.com]
 * @version 1.0    2018/9/13
 */
@Component
public class CartServiceFallback implements CartService {

    @Override
    public String getCart(String cartId) {
        return "fallback";
    }
}
