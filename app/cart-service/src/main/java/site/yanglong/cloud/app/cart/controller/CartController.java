package site.yanglong.cloud.app.cart.controller;

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
@RequestMapping("cart")
public class CartController {

    @GetMapping("info")
    public String getCart(String cartId){
        return "cart info:"+cartId;
    }
}
