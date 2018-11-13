package site.yanglong.cloud.oauth2.server.config.extention.sms.Exeception;

import org.springframework.security.core.AuthenticationException;

/**
 * functional describe:验证码登录参数错误异常
 *
 * @author DR.YangLong [410357434@163.com]
 * @version 1.0    2018/8/30
 */
public class SMSBadParamException extends AuthenticationException {

    public SMSBadParamException(String msg, Throwable t) {
        super(msg, t);
    }

    public SMSBadParamException(String msg) {
        super(msg);
    }
}
