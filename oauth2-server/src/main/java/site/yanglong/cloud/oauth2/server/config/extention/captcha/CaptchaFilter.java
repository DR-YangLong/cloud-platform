package site.yanglong.cloud.oauth2.server.config.extention.captcha;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * functional describe:验证码过滤器，在需要验证图片验证码的时候验证图片验证码。放在BasicAuthenticationFilter之前。
 * 1.client使用设备码申请UUID（UUID存入client缓存，登录请求必须带此标识，APP会原样返回设备码做UUID）。
 * 2.server记录UUID键值对。
 * 3.此filter过滤/oauth/token且参数中有username,password或mobile,validate的请求。
 * 检查登录错误次数。如果超过3次，则核对验证码。
 * 4.如果验证码对，继续执行filter chain，如果不对，中断client请求。
 * <p>
 * 请求成功失败publish event，listener处理相应操作。
 *
 * @author DR.YangLong [410357434@163.com]
 * @version 1.0    2018/8/29
 */
public class CaptchaFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //获取客户端标识
        //获取客户端登录失败次数
        //判断是否需要验证图片验证码
        //获取图片验证码，对比
    }
}
