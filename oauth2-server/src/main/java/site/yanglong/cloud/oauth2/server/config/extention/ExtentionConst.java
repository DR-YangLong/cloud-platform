package site.yanglong.cloud.oauth2.server.config.extention;

/**
 * functional describe:常量
 *
 * @author DR.YangLong [410357434@163.com]
 * @version 1.0    2018/8/29
 */
public interface ExtentionConst {
    /**
     * 登录（token）类型在请求头中的key
     */
    String TOKEN_TYPE_HEADER="LOGIN-BY";

    /**
     * 图片验证码在请求中的参数名
     */
    String CAPTCHA_PARAM_NAME="captcha";
}
