package site.yanglong.cloud.oauth2.server.mapper;

import site.yanglong.cloud.oauth2.server.model.UserBase;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户基础表 Mapper 接口
 * </p>
 *
 * @author Dr.YangLong
 * @since 2018-08-27
 */
@Mapper
public interface UserBaseMapper extends BaseMapper<UserBase> {

}
