package site.yanglong.cloud.config.server.model;


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
 * 
 * </p>
 *
 * @author Dr.YangLong
 * @since 2018-08-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("app配置管理")
@TableName("application_config")
public class ApplicationConfig extends Model<ApplicationConfig>{

    private static final long serialVersionUID = 1L;

    /**
     * 配置编号
     */
	@ApiModelProperty("配置编号")
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 配置所属应用名
     */
	@ApiModelProperty("配置所属应用名")
	private String appName;
    /**
     * profile名称
     */
	@ApiModelProperty("profile名称")
	private String profileName;
    /**
     * 标签/分支名
     */
	@ApiModelProperty("标签/分支名")
	private String label;
    /**
     * 属性key
     */
	@ApiModelProperty("属性key")
	private String propKey;
    /**
     * 属性值
     */
	@ApiModelProperty("属性值")
	private String propValue;
    /**
     * 是否生效，0生效，1不生效
     */
	@ApiModelProperty("是否生效，0生效，1不生效")
	private String enabled;
    /**
     * 操作人
     */
	@ApiModelProperty("操作人")
	private Long operator;
    /**
     * 操作时间
     */
	@ApiModelProperty("操作时间")
	private LocalDateTime operateTime;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
