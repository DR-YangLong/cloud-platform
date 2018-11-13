package site.yanglong.cloud.config.server.service.impl;

import site.yanglong.cloud.config.server.model.ApplicationConfig;
import site.yanglong.cloud.config.server.mapper.ApplicationConfigMapper;
import site.yanglong.cloud.config.server.service.ApplicationConfigService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Dr.YangLong
 * @since 2018-08-21
 */
@Service
public class ApplicationConfigServiceImpl extends ServiceImpl<ApplicationConfigMapper, ApplicationConfig> implements ApplicationConfigService {

    @Override
    public List<ApplicationConfig> listConfig(String application, String profile, String label) {
        QueryWrapper<ApplicationConfig> wrapper = new QueryWrapper<>();
        wrapper.eq("appName", application).eq("profileName", profile).eq("label", label).eq("enabled","0");
        return this.selectList(wrapper);
    }
}
