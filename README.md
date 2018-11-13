# 云平台说明
依靠Spring Cloud为基础开发云平台，微服务架构。

## 概述
### 结构综述
项目使用Maven为构建工具。目录结构为maven目录结构。      
* eureka-server为注册发现服务项目，使用eureka server。
* config-server为配置管理项目，使用spring config server。
* monitor为监控中心项目，使用spring sleuth，zipkin，spring boot admin。
* api-gateway为gateway服务项目，使用zuul。
* oauth2-server为认证授权服务项目，使用spring security OAuth2。
* app项目为业务微服务项目总目录，业务微服务项目均在此目录下。

### 架构
![架构图](architecture.png)

### 项目间依赖
eureka server为独立服务，不依赖其他服务；config server仅依赖eureka server服务。
其他服务均依赖eureka server服务和config server服务。

### 涉及依赖
* Spring cloud
* Netflix eureka
* Netflix zuul
* Netflix Feign
* Netflix Hystrix
* Netflix Ribbon
* Redis
* MySQL
* RabbitMQ
* zipkin
* spring boot admin
* druid
* fastjson
* mybatis
* mybatis plus
* logback      
...

## 启动
### 初始化
1. 创建数据库并使用[初始化脚本](init.sql)导入初始化数据。默认所有项目使用账号密码均为root。
如果修改账号密码，需要同时修改**表application_config**中相关的配置。如何加密数据库密码详见druid使用说明。
2. 修改config-server项目中**application.yml**文件数据库配置。
3. 设置通用启动参数到环境变量（不包含“--”）。
4. 启动redis，并配置config-server中redis相关配置，同时修改数据库中有关配置。
###  通用启动参数
```text
--config-security.username=config
--config-security.password=123456
--config-security.id=noop
--registry-security.username=test
--registry-security.password=123456
--registry-security.id=noop
--monitor-security.username=monitor
--monitor-security.password=123456
--monitor-security.id=noop
```

### 启动Eureka Server
使用已配置profile或使用自定义配置分别启动eureka server集群。
> 注意如果使用自定义配置，请保持所有的项目的eureka项目配置与自定义的配置一致。

### 启动config server
自定义多个profile，使用相同配置在不用机器上启动config server组成配置集群。     
在同一机器上，定义多个profile使用不同端口启动，组成配置集群。

### 启动认证授权中心
启动多个实例，使用postman用post方法，访问某个服务的以下链接：
> /oauth/token?username=133556&password=2222&grant_type=password&scope=all

如果返回类似以下数据，则表示正确配置。可以启动多个组成集群。
```json
{
    "access_token": "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiMSIsIjIiLCIzIl0sInVzZXJfbmFtZSI6IjEzMzU1NiIsInNjb3BlIjpbImFsbCJdLCJleHAiOjE1MzY3MjI3NzQsImF1dGhvcml0aWVzIjpbImFkbWluIiwidXNlciIsInNhbSJdLCJqdGkiOiI2MWM5YjdlZS05YzdkLTQzYzctODIwMy0zZDZiOGViMzg2ZjYiLCJjbGllbnRfaWQiOiJhcHAifQ.q6IH-J2kVn_6y790aOziESES4XGeYYiQqup-re2HaUGmWWb6UbxmNptGEV51xRAYO8O481l2Tu8ffCMOTY-LHB2jFlyU7iqJwjfCkGp0QEaydKdTl4qHVoWiKp-kiWoDnk_V6shfhFIUQVaXJELz8MARFHANc5zgeHTle-CjCi4",
    "token_type": "bearer",
    "refresh_token": "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiMSIsIjIiLCIzIl0sInVzZXJfbmFtZSI6IjEzMzU1NiIsInNjb3BlIjpbImFsbCJdLCJhdGkiOiI2MWM5YjdlZS05YzdkLTQzYzctODIwMy0zZDZiOGViMzg2ZjYiLCJleHAiOjE1MzY3MjQ1NzQsImF1dGhvcml0aWVzIjpbImFkbWluIiwidXNlciIsInNhbSJdLCJqdGkiOiIxYjllODNlMy01MDk3LTRjMGYtOWYwMC1kOTM0ZWYwYjViYTAiLCJjbGllbnRfaWQiOiJhcHAifQ.X5qfFEOYxSi3hIq74SqxbSBnxkUfWMmN7egJEMHzQNN-xw1QlI0D8uTQp8auD8UOTFwFIcSD9F-Ols0vFitC7PwDN0Te4ff-Zd4fLeph5lDl9EGQXZbrCYoMbn3wpc7B4d1CV-Zfk38WTtRwx13YYzuvEXNd0Zy5LuWWfZG3tVw",
    "expires_in": 3599,
    "scope": "all",
    "jti": "61c9b7ee-9c7d-43c7-8203-3d6b8eb386f6"
}
```

### 启动api-gateway
启动一个api gateway，启动后，使用以下链接访问
> /oauth/oauth/token?username=133556&password=2222&grant_type=password&scope=all

如果返回认证信息，则表示正确。可以启动多个组成集群。

### 启动监控中心
启动一个监控中心，访问首页，/admin页面，如有相关监控信息，说明配置正确。

### 启动业务服务
