package site.yanglong.cloud.common.model;

/**
 * functional describe:返回常量定义
 *
 * @author DR.YangLong [410357434@163.com]
 * @version 1.0    2018/9/17
 */
public enum ResultEnum {
    SUCCESS(200, "操作成功"),
    SERVER_ERROR(500, "服务器内部错误"),
    NOT_FOUND(404, "页面不存在"),
    BAD_REQUEST(400, "错误的请求"),
    NOT_AUTHENTICATION(401, "未获授权"),
    FORBIDDEN(403, "无此权限"),
    NO_DATA(600, "数据不存在"),
    USER_EXISTS(601, "用户已存在"),
    LOGIN_PASSWORD_ERROR(602, "用户名或密码错误"),
    LOGIN_SMS_ERROR(603, "验证码错误");
    private int code;
    private String message;

    ResultEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
