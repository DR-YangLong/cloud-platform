package site.yanglong.cloud.oauth2.server.service.impl;

import site.yanglong.cloud.oauth2.server.model.UserBase;
import site.yanglong.cloud.oauth2.server.mapper.UserBaseMapper;
import site.yanglong.cloud.oauth2.server.service.UserBaseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户基础表 服务实现类
 * </p>
 *
 * @author Dr.YangLong
 * @since 2018-08-27
 */
@Service
public class UserBaseServiceImpl extends ServiceImpl<UserBaseMapper, UserBase> implements UserBaseService{

}
