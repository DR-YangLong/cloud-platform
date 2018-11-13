package site.yanglong.cloud.oauth2.server.model;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * OAuth2客户端信息表
 * </p>
 *
 * @author Dr.YangLong
 * @since 2018-08-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("OAuth2客户端信息表")
@TableName("oauth_client_details")
public class OauthClientDetails extends Model<OauthClientDetails> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
	@ApiModelProperty("编号")
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * client名称
     */
	@ApiModelProperty("client名称")
	private String clientId;
    /**
     * client密码
     */
	@ApiModelProperty("client密码")
	private String clientSecret;
    /**
     * 权限范围列表
     */
	@ApiModelProperty("权限范围列表")
	private String scope;
    /**
     * 资源id列表
     */
	@ApiModelProperty("资源id列表")
	private String resourceIds;
    /**
     * 自动授权的类型
     */
	@ApiModelProperty("自动授权的类型")
	private String authorizedGrantTypes;
    /**
     * web应用重定向列表
     */
	@ApiModelProperty("web应用重定向列表")
	private String webServerRedirectUri;
    /**
     * 权限列表
     */
	@ApiModelProperty("权限列表")
	private String authorities;
    /**
     * token有效期
     */
	@ApiModelProperty("token有效期")
	private Integer accessTokenValidity;
    /**
     * refreshToken有效期
     */
	@ApiModelProperty("refreshToken有效期")
	private Integer refreshTokenValidity;
    /**
     * 附加信息
     */
	@ApiModelProperty("附加信息")
	private String additionalInformation;
    /**
     * 是否自动授权
     */
	@ApiModelProperty("是否自动授权")
	private String autoapprove;
    /**
     * 是否启用
     */
	@ApiModelProperty("是否启用,0启用，1不启用")
	private String enabled;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}
}
