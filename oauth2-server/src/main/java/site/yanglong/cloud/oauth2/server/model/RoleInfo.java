package site.yanglong.cloud.oauth2.server.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Dr.YangLong
 * @since 2018-08-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("")
@TableName("role_info")
public class RoleInfo extends Model<RoleInfo>{

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
	@ApiModelProperty("编号")
	@TableId(value="roleId", type= IdType.AUTO)
	private Long roleId;
    /**
     * 角色名称
     */
	@ApiModelProperty("角色名称")
	private String roleName;
    /**
     * 中文备注
     */
	@ApiModelProperty("中文备注")
	private String roleRemark;
    /**
     * 是否启用，0启用，1禁用
     */
	@ApiModelProperty("是否启用，0启用，1禁用")
	private String enabled;


	@Override
	protected Serializable pkVal() {
		return this.roleId;
	}

}
