# 认证中心
从配置中心获取配置，启动后作为认证服务器。

## 参考配置
```yaml
server:
  port: 5001
spring:
  application:
    name: oauth-server
  datasource:
    druid:
      url: jdbc:mysql://10.49.15.244:3306/zentao?autoReconnect=true&useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true
      username: root
      password: fA0iTfSAyZ9YT5WkDfvVoAAHA41gkeYYsdMNqQJFR2eWooxXjg9GWEt9BkaKLt0NkKZfv8/PiQYDpJ9pTSI3NA==
      driver-class-name: com.mysql.cj.jdbc.Driver
      initial-size: 3
      max-active: 20
      min-idle: 3
      max-wait: 60000
      time-between-eviction-runs-millis: 60000
      min-evictable-idle-time-millis: 300000
      validation-query: select 'x'
      validation-query-timeout: 10000
      test-while-idle: true
      test-on-borrow: true
      test-on-return: true
      max-open-prepared-statements: 200
      max-pool-prepared-statement-per-connection-size: 20
      connect-properties:
        config.decrypt: true
        config.decrypt.key: MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBALJUQV+coCweLv6wq+oavcy17n+XYOTo3jBKu3tfKE+1REum9Aihi/SPYLd88eXcdfjq5yawdMK2wJeZxiIVQv0CAwEAAQ==
      filter:
        config:
          enabled: true
        stat:
          db-type: mysql
          log-slow-sql: true
          slow-sql-millis: 2000
          enabled: true
      stat-view-servlet:
        enabled: true
        url-pattern: "/druid/*"
        login-username: yanglong
        login-password: 13626878988
        allow:
        deny:
        reset-enable: true
  redis:
    host: 10.49.15.244
    database: 0
    port: 6379
    password:
#mybatis-plus
mybatis-plus:
  #映射文件
  mapper-locations: classpath:/mapper/*Mapper.xml
  #实体类包，数组
  typeAliasesPackage: site.yanglong.cloud.oauth2.server.model
  global-config:
    db-config:
      #关闭全局字符串like查询，使用需单独在字段使用@TableField(condition=SqlCondition.LIKE_RIGHT)
      column-like: false
      id-type: auto
      field-strategy: ignored
      column-underline: false
      capital-mode: false
      logic-delete-value: 1
      logic-not-delete-value: 0
    refresh: true
    #sql-injector: com.baomidou.mybatisplus.extension.injector.LogicSqlInjector
  configuration:
    #如果数据库表**字段**本身采用下划线且POJO中为驼峰，此属性设为true。
    map-underscore-to-camel-case: false
    #是否开启mapper二级缓存
    cache-enabled: false
```