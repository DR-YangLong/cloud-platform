package site.yanglong.cloud.oauth2.server.mapper;

import site.yanglong.cloud.oauth2.server.model.UserAndRole;
import site.yanglong.cloud.oauth2.server.model.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Dr.YangLong
 * @since 2018-08-27
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {

   UserAndRole selectUserWithinRole(String account);
}
