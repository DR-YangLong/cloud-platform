package site.yanglong.cloud.oauth2.server.service.impl;

import site.yanglong.cloud.oauth2.server.model.RoleInfo;
import site.yanglong.cloud.oauth2.server.model.UserAndRole;
import site.yanglong.cloud.oauth2.server.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * functional describe:加载用户详情
 *
 * @author DR.YangLong [410357434@163.com]
 * @version 1.0    2018/8/27
 */
@Service("customUserDetailService")
public class CustomUserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRoleService userRoleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //查询用户信息和权限，构造User返回,username可能为手机号，可能为邮箱
        if (StringUtils.isEmpty(username)) throw new UsernameNotFoundException("userName must not empty");
        UserAndRole userAndRole = userRoleService.findUser(username);
        User user;
        if (null != userAndRole) {
            user = new User(username, userAndRole.getUserPwd(),
                    Objects.equals("0", userAndRole.getUserStatus()), true, true,
                    !Objects.equals("2", userAndRole.getUserStatus()),
                    CollectionUtils.isEmpty(userAndRole.getUserRole()) ? Collections.EMPTY_LIST :
                            userAndRole.getUserRole().stream()
                                    .map(RoleInfo::getRoleName)
                                    .map(SimpleGrantedAuthority::new)
                                    .collect(Collectors.toList()));
        } else {
            throw new UsernameNotFoundException("user not exists");
        }
        return user;
    }
}
