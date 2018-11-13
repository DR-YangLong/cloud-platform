package site.yanglong.cloud.app.order.controller;

import site.yanglong.cloud.app.order.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * functional describe:
 *
 * @author DR.YangLong [410357434@163.com]
 * @version 1.0    2018/9/13
 */
@RestController
@RequestMapping("order")
public class OrderController {
    @Autowired
    private CartService cartService;

    @GetMapping("add")
    public String create() {
        String cart = cartService.getCart("from order");
        return cart;
    }
}
