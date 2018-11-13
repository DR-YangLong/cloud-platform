package site.yanglong.cloud.oauth2.server.service;

import site.yanglong.cloud.oauth2.server.model.UserAndRole;
import site.yanglong.cloud.oauth2.server.model.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Dr.YangLong
 * @since 2018-08-27
 */
public interface UserRoleService extends IService<UserRole> {

    /**
     * 用手机号或邮箱查询用户
     * @param account 手机号或邮箱
     * @return 用户及角色
     */
    UserAndRole findUser(String account);
}
