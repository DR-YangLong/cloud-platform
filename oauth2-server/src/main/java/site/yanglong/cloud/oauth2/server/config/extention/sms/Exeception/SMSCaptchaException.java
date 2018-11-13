package site.yanglong.cloud.oauth2.server.config.extention.sms.Exeception;

import org.springframework.security.core.AuthenticationException;

/**
 * functional describe:验证码错误异常
 *
 * @author DR.YangLong [410357434@163.com]
 * @version 1.0    2018/8/30
 */
public class SMSCaptchaException extends AuthenticationException {
    public SMSCaptchaException(String msg, Throwable t) {
        super(msg, t);
    }

    public SMSCaptchaException(String msg) {
        super(msg);
    }
}
