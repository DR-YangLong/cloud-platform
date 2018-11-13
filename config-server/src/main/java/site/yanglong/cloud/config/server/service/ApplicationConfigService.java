package site.yanglong.cloud.config.server.service;

import site.yanglong.cloud.config.server.model.ApplicationConfig;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Dr.YangLong
 * @since 2018-08-21
 */
public interface ApplicationConfigService extends IService<ApplicationConfig> {

    /**
     * 获取数据库中<code>application</code>-<code>profile</code>.yml的配置，
     * <code>label</code>可为标识，版本等。
     *
     * @param application 应用名，对应spring.application.name
     * @param profile     激活的profile，对应spring.profiles.active
     * @param label       标识
     * @return 配置对象列表
     */
    List<ApplicationConfig> listConfig(String application, String profile, String label);
}
