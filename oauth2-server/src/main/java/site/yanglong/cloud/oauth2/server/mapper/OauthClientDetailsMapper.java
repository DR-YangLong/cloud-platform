package site.yanglong.cloud.oauth2.server.mapper;

import site.yanglong.cloud.oauth2.server.model.OauthClientDetails;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * OAuth2客户端信息表 Mapper 接口
 * </p>
 *
 * @author Dr.YangLong
 * @since 2018-08-27
 */
@Mapper
public interface OauthClientDetailsMapper extends BaseMapper<OauthClientDetails> {

}
