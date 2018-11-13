package site.yanglong.cloud.oauth2.server.service.impl;

import site.yanglong.cloud.oauth2.server.service.RedisCacheService;
import org.springframework.stereotype.Service;

/**
 * functional describe: Redis缓存封装
 *
 * @author DR.YangLong [410357434@163.com]
 * @version 1.0    2018/8/30
 */
@Service
public class RedisCacheServiceImpl implements RedisCacheService {

    @Override
    public void put(String cacheName, String key, Object o, long expire) {

    }

    @Override
    public <T> T get(String cacheName, String key, Class<T> clazz, boolean del) {
        return null;
    }

    @Override
    public int del(String cacheName, String... keys) {
        return 0;
    }

    @Override
    public void putForSet(String cacheName, String key, Object o) {

    }

    @Override
    public <T> T getForSet(String cacheName, String key, Class<T> clazz, boolean del) {
        return null;
    }

    @Override
    public void putForMap(String cacheName, String key, Object o) {

    }

    @Override
    public <T> T getForMap(String cacheName, String key, Class<T> clazz, boolean del) {
        return null;
    }
}
