package site.yanglong.cloud.config.server.config;

import site.yanglong.cloud.config.server.model.ApplicationConfig;
import site.yanglong.cloud.config.server.service.ApplicationConfigService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.config.environment.Environment;
import org.springframework.cloud.config.environment.PropertySource;
import org.springframework.cloud.config.server.environment.EnvironmentRepository;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * functional describe:自定义配置获取仓库，参考{@see org.springframework.cloud.config.server.environment.JdbcEnvironmentRepository}
 *
 * @author DR.YangLong [410357434@163.com]
 * @version 1.0    2018/8/21
 */
@Component
public class JdbcRedisEnvironmentRepository implements EnvironmentRepository, Ordered {
    private static final String ENVIRONMENT_REDIS_KEY = "SPRING:CONFIG:ENV";
    @Autowired
    private ApplicationConfigService applicationConfigService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public synchronized Environment findOne(String application, String profile, String label) {
        String config = application;
        if (StringUtils.isEmpty(label)) {
            label = "master";
        }
        if (StringUtils.isEmpty(profile)) {
            profile = "default";
        }
        if (!profile.startsWith("default")) {
            profile = "default," + profile;
        }
        Environment environment;
        String key = application + ":" + profile + ":" + label;
        Object value = stringRedisTemplate.opsForHash().get(ENVIRONMENT_REDIS_KEY, key);
        if (StringUtils.isEmpty(value)) {
            String[] profiles = StringUtils.commaDelimitedListToStringArray(profile);
            environment = new Environment(application, profiles, label, null,
                    null);
            if (!config.startsWith("application")) {
                config = "application," + config;
            }
            List<String> applications = new ArrayList<String>(new LinkedHashSet<>(
                    Arrays.asList(StringUtils.commaDelimitedListToStringArray(config))));
            List<String> envs = new ArrayList<>(new LinkedHashSet<>(Arrays.asList(profiles)));
            Collections.reverse(applications);
            Collections.reverse(envs);
            for (String app : applications) {
                for (String env : envs) {
                    List<ApplicationConfig> configs = applicationConfigService.listConfig(app, env, label);
                    Map<String, String> next = new HashMap<>();
                    configs.forEach(c -> next.put(c.getPropKey(), c.getPropValue()));
                    if (!next.isEmpty()) {
                        environment.add(new PropertySource(app + "-" + env, next));
                    }
                }
            }
            value = JSONObject.toJSONString(environment);
            stringRedisTemplate.opsForHash().put(ENVIRONMENT_REDIS_KEY, key, value);
        } else {
            JSONObject jsonObject = JSONObject.parseObject(value.toString());
            JSONArray array = jsonObject.getJSONArray("propertySources");
            List<PropertySource> list = JSONObject.parseArray(array.toJSONString(), PropertySource.class);
            environment = JSONObject.parseObject(value.toString(), Environment.class);
            environment.addAll(list);
        }
        return environment;
    }

    @Override
    public int getOrder() {
        return -1000;
    }
}
