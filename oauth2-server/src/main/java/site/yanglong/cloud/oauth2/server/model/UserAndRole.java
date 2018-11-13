package site.yanglong.cloud.oauth2.server.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * functional describe:用户及权限实体
 *
 * @author DR.YangLong [410357434@163.com]
 * @version 1.0    2018/8/28
 */
@Data
@ApiModel("用户及权限")
public class UserAndRole extends UserBase {
    @ApiModelProperty("用户角色列表")
    private List<RoleInfo> userRole;
}
