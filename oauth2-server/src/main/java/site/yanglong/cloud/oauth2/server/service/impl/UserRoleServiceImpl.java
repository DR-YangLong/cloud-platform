package site.yanglong.cloud.oauth2.server.service.impl;

import site.yanglong.cloud.oauth2.server.model.UserAndRole;
import site.yanglong.cloud.oauth2.server.model.UserRole;
import site.yanglong.cloud.oauth2.server.mapper.UserRoleMapper;
import site.yanglong.cloud.oauth2.server.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Dr.YangLong
 * @since 2018-08-27
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    @Override
    public UserAndRole findUser(String account) {
        return baseMapper.selectUserWithinRole(account);
    }
}
