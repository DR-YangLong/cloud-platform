package site.yanglong.cloud.common.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * functional describe: 无返回值返回模型
 *
 * @author DR.YangLong [410357434@163.com]
 * @version 1.0    2018/9/17
 */
@Data
@ApiModel("无返回值的返回对象")
public class ResultModel {
    @ApiModelProperty("返回码")
    private int code;
    @ApiModelProperty("是否成功")
    private boolean success;
    @ApiModelProperty("提示消息")
    private String msg;


    /**
     * 设置返回码和是否成功以及提示消息
     *
     * @param resultEnum ResultEnum
     */
    public void setResult(ResultEnum resultEnum) {
        if (ResultEnum.SUCCESS.equals(resultEnum)) {
            this.setSuccess(true);
        }
        this.setCode(resultEnum.getCode());
        this.setMsg(resultEnum.getMessage());
    }
}
