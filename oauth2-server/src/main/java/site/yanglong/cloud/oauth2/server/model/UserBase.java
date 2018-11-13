package site.yanglong.cloud.oauth2.server.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户基础表
 * </p>
 *
 * @author Dr.YangLong
 * @since 2018-08-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("用户基础表")
@TableName("user_base")
public class UserBase extends Model<UserBase>{

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
	@ApiModelProperty("编号")
	@TableId(value="userId", type= IdType.AUTO)
	private Long userId;
    /**
     * 手机号
     */
	@ApiModelProperty("手机号")
	private String userMobile;
    /**
     * 密码
     */
	@ApiModelProperty("密码")
	private String userPwd;
    /**
     * 姓名
     */
	@ApiModelProperty("姓名")
	private String userName;
    /**
     * 邮编
     */
	@ApiModelProperty("邮编")
	private String userEmail;
    /**
     * 性别
     */
	@ApiModelProperty("性别")
	private String userGender;
    /**
     * 用户状态，0正常，1异常，2锁定，3禁止
     */
	@ApiModelProperty("用户状态，0正常，1异常，2锁定，3禁止")
	private String userStatus;
    /**
     * 创建时间
     */
	@ApiModelProperty("创建时间")
	private LocalDateTime createTime;
    /**
     * 更新时间
     */
	@ApiModelProperty("更新时间")
	private LocalDateTime modifyTime;
    /**
     * 最新登录时间
     */
	@ApiModelProperty("最新登录时间")
	private LocalDateTime loginTime;


	@Override
	protected Serializable pkVal() {
		return this.userId;
	}

}
