package site.yanglong.cloud.common.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * functional describe:带有返回值的返回结果
 *
 * @author DR.YangLong [410357434@163.com]
 * @version 1.0    2018/9/17
 */
@Data
@ApiModel("带返回值的返回对象")
public class ResultModelData<T> extends ResultModel {
    @ApiModelProperty("返回数据")
    private T data;
}
