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
@TableName("user_role")
public class UserRole extends Model<UserRole>{

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
	@ApiModelProperty("编号")
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 用户id
     */
	@ApiModelProperty("用户id")
	private Long userId;
    /**
     * 角色id
     */
	@ApiModelProperty("角色id")
	private Long roleId;
    /**
     * 0启用，1禁用
     */
	@ApiModelProperty("0启用，1禁用")
	private String enabled;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
