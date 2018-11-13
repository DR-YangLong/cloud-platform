package site.yanglong.cloud.config.server.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Dr.YangLong
 * @since 2018-08-21
 */
@RestController
@RequestMapping("/applicationConfig")
public class ApplicationConfigController {

    @RequestMapping({"/","index","index.html"})
    public String index(){
        return "hello world!";
    }
}

