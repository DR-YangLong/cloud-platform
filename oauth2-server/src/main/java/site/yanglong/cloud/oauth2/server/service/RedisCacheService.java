package site.yanglong.cloud.oauth2.server.service;

import org.springframework.util.StringUtils;

/**
 * functional describe: Redis缓存服务，按业务存取数据
 *
 * @author DR.YangLong [410357434@163.com]
 * @version 1.0    2018/8/29
 */
public interface RedisCacheService {
    /**
     * string类型存入
     *
     * @param cacheName redis key
     * @param key       业务key
     * @param o         值
     */
    void put(String cacheName, String key, Object o, long expire);

    /**
     * string类型取出
     *
     * @param cacheName redis key
     * @param key       业务key
     * @param clazz     要转换的类型
     * @param del       是否删除
     */
    <T> T get(String cacheName, String key, Class<T> clazz, boolean del);

    /**
     * 删除多个string类型缓存。
     * 如果没有业务key，会删除key为cacheName的redis存储元素：包括string,list,hash,set,zset。
     *
     * @param cacheName 缓存名
     * @param keys 业务key集合
     * @return 已删除数量
     */
    int del(String cacheName,String... keys);

    /**
     * Set结构存入
     *
     * @param cacheName redis key
     * @param key       业务key
     * @param o         值
     */
    void putForSet(String cacheName, String key, Object o);

    /**
     * Set结构取出
     *
     * @param cacheName redis key
     * @param key       业务key
     * @param clazz     要转换类型
     * @param del       是否删除
     */
    <T> T getForSet(String cacheName, String key, Class<T> clazz, boolean del);

    /**
     * Hash结构存入
     *
     * @param cacheName redis key
     * @param key       hash key
     * @param o         hash value
     */
    void putForMap(String cacheName, String key, Object o);

    /**
     * Hash结构取出
     *
     * @param cacheName redis key
     * @param key       hash key
     * @param clazz     hash value java type
     * @param del       是否删除
     */
    <T> T getForMap(String cacheName, String key, Class<T> clazz, boolean del);

    /**
     * String 类型，存取数据时构造用于redis的key
     *
     * @param cacheName 缓存名
     * @param key       业务key
     * @return redis存储时的key
     */
    default String stringKeyGenerator(String cacheName, String key) {
        if (StringUtils.isEmpty(key)) {
            return cacheName;
        }
        return cacheName + ":" + key;
    }

    /**
     * 生成SET结构的值
     *
     * @param key   业务key
     * @param value 业务值
     * @return 真实存入redis的值
     */
    default String setValueGenerator(String key, String value) {
        if (StringUtils.isEmpty(key)) {
            return value;
        }
        return key + ":" + value;
    }
}
