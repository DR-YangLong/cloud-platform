/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE=''NO_AUTO_VALUE_ON_ZERO'' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*Table structure for table `application_config` */

DROP TABLE IF EXISTS `application_config`;

CREATE TABLE `application_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT ''配置编号'',
  `appName` varchar(64) DEFAULT ''application'' COMMENT ''配置所属应用名'',
  `profileName` varchar(32) DEFAULT ''default'' COMMENT ''profile名称'',
  `label` varchar(32) DEFAULT ''master'' COMMENT ''标签/分支名'',
  `propKey` varchar(256) DEFAULT NULL COMMENT ''属性key'',
  `propValue` varchar(2048) DEFAULT NULL COMMENT ''属性值'',
  `enabled` char(1) DEFAULT ''0'' COMMENT ''是否生效，0生效，1不生效'',
  `operator` bigint(20) DEFAULT NULL COMMENT ''操作人'',
  `operateTime` datetime DEFAULT NULL COMMENT ''操作时间'',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=123 DEFAULT CHARSET=utf8;

/*Data for the table `application_config` */

insert  into `application_config`(`id`,`appName`,`profileName`,`label`,`propKey`,`propValue`,`enabled`,`operator`,`operateTime`) values
(1,''application'',''default'',''master'',''config-security.username'',''config'',''0'',NULL,NULL),
(2,''application'',''default'',''master'',''config-security.password'',''123456'',''0'',NULL,NULL),
(3,''application'',''default'',''master'',''config-security.id'',''noop'',''0'',NULL,NULL),
(4,''application'',''default'',''master'',''registry-security.username'',''test'',''0'',NULL,NULL),
(5,''application'',''default'',''master'',''registry-security.password'',''123456'',''0'',NULL,NULL),
(6,''application'',''default'',''master'',''registry-security.id'',''noop'',''0'',NULL,NULL),
(7,''auth-server'',''default'',''master'',''spring.datasource.url'',''jdbc:mysql://10.49.15.244:3306/zentao?autoReconnect=true&useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true'',''0'',NULL,NULL),
(8,''auth-server'',''default'',''master'',''spring.datasource.username'',''root'',''0'',NULL,NULL),
(9,''auth-server'',''default'',''master'',''spring.datasource.password'',''fA0iTfSAyZ9YT5WkDfvVoAAHA41gkeYYsdMNqQJFR2eWooxXjg9GWEt9BkaKLt0NkKZfv8/PiQYDpJ9pTSI3NA=='',''0'',NULL,NULL),
(10,''auth-server'',''default'',''master'',''spring.datasource.driver-class-name'',''com.mysql.cj.jdbc.Driver'',''0'',NULL,NULL),
(11,''auth-server'',''default'',''master'',''spring.datasource.druid.initial-size'',''3'',''0'',NULL,NULL),
(12,''auth-server'',''default'',''master'',''spring.datasource.druid.max-active'',''20'',''0'',NULL,NULL),
(13,''auth-server'',''default'',''master'',''spring.datasource.druid.min-idle'',''3'',''0'',NULL,NULL),
(14,''auth-server'',''default'',''master'',''spring.datasource.druid.max-wait'',''60000'',''0'',NULL,NULL),
(15,''auth-server'',''default'',''master'',''spring.datasource.druid.time-between-eviction-runs-millis'',''60000'',''0'',NULL,NULL),
(16,''auth-server'',''default'',''master'',''spring.datasource.druid.min-evictable-idle-time-millis'',''300000'',''0'',NULL,NULL),
(17,''auth-server'',''default'',''master'',''spring.datasource.druid.validation-query'',''select \''x\'''',''0'',NULL,NULL),
(18,''auth-server'',''default'',''master'',''spring.datasource.druid.validation-query-timeout'',''10000'',''0'',NULL,NULL),
(19,''auth-server'',''default'',''master'',''spring.datasource.druid.test-while-idle'',''true'',''0'',NULL,NULL),
(20,''auth-server'',''default'',''master'',''spring.datasource.druid.test-on-borrow'',''true'',''0'',NULL,NULL),
(21,''auth-server'',''default'',''master'',''spring.datasource.druid.test-on-return'',''true'',''0'',NULL,NULL),
(22,''auth-server'',''default'',''master'',''spring.datasource.druid.max-open-prepared-statements'',''200'',''0'',NULL,NULL),
(23,''auth-server'',''default'',''master'',''spring.datasource.druid.max-pool-prepared-statement-per-connection-size'',''20'',''0'',NULL,NULL),
(24,''auth-server'',''default'',''master'',''spring.datasource.druid.connect-properties.config.decrypt'',''true'',''0'',NULL,NULL),
(25,''auth-server'',''default'',''master'',''spring.datasource.druid.connect-properties.config.decrypt.key'',''MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBALJUQV+coCweLv6wq+oavcy17n+XYOTo3jBKu3tfKE+1REum9Aihi/SPYLd88eXcdfjq5yawdMK2wJeZxiIVQv0CAwEAAQ=='',''0'',NULL,NULL),
(26,''auth-server'',''default'',''master'',''spring.datasource.druid.filter.config.enabled'',''true'',''0'',NULL,NULL),
(27,''auth-server'',''default'',''master'',''spring.datasource.druid.filter.stat.db-type'',''mysql'',''0'',NULL,NULL),
(28,''auth-server'',''default'',''master'',''spring.datasource.druid.filter.stat.log-slow-sql'',''true'',''0'',NULL,NULL),
(29,''auth-server'',''default'',''master'',''spring.datasource.druid.filter.stat.slow-sql-millis'',''2000'',''0'',NULL,NULL),
(30,''auth-server'',''default'',''master'',''spring.datasource.druid.filter.stat.enabled'',''true'',''0'',NULL,NULL),
(31,''auth-server'',''default'',''master'',''spring.datasource.druid.stat-view-servlet.enabled'',''true'',''0'',NULL,NULL),
(32,''auth-server'',''default'',''master'',''spring.datasource.druid.stat-view-servlet.url-pattern'',''/druid/*'',''0'',NULL,NULL),
(33,''auth-server'',''default'',''master'',''spring.datasource.druid.stat-view-servlet.login-username'',''yanglong'',''0'',NULL,NULL),
(34,''auth-server'',''default'',''master'',''spring.datasource.druid.stat-view-servlet.login-password'',''13626878988'',''0'',NULL,NULL),
(35,''auth-server'',''default'',''master'',''spring.datasource.druid.stat-view-servlet.allow'',NULL,''0'',NULL,NULL),
(36,''auth-server'',''default'',''master'',''spring.datasource.druid.stat-view-servlet.deny'',NULL,''0'',NULL,NULL),
(37,''auth-server'',''default'',''master'',''spring.datasource.druid.stat-view-servlet.reset-enable'',''true'',''0'',NULL,NULL),
(38,''auth-server'',''default'',''master'',''spring.redis.host'',''10.49.15.244'',''0'',NULL,NULL),
(39,''auth-server'',''default'',''master'',''spring.redis.database'',''0'',''0'',NULL,NULL),
(40,''auth-server'',''default'',''master'',''spring.redis.port'',''6379'',''0'',NULL,NULL),
(41,''auth-server'',''default'',''master'',''spring.redis.password'',NULL,''0'',NULL,NULL),
(42,''auth-server'',''default'',''master'',''mybatis-plus.mapper-locations'',''classpath:/mapper/*Mapper.xml'',''0'',NULL,NULL),
(43,''auth-server'',''default'',''master'',''mybatis-plus.typeAliasesPackage'',''site.yanglong.cloud.oauth2.server.model'',''0'',NULL,NULL),
(44,''auth-server'',''default'',''master'',''mybatis-plus.global-config.db-config.column-like'',''false'',''0'',NULL,NULL),
(45,''auth-server'',''default'',''master'',''mybatis-plus.global-config.db-config.id-type'',''auto'',''0'',NULL,NULL),
(46,''auth-server'',''default'',''master'',''mybatis-plus.global-config.db-config.field-strategy'',''ignored'',''0'',NULL,NULL),
(47,''auth-server'',''default'',''master'',''mybatis-plus.global-config.db-config.column-underline'',''false'',''0'',NULL,NULL),
(48,''auth-server'',''default'',''master'',''mybatis-plus.global-config.db-config.capital-mode'',''false'',''0'',NULL,NULL),
(49,''auth-server'',''default'',''master'',''mybatis-plus.global-config.db-config.logic-delete-value'',''1'',''0'',NULL,NULL),
(50,''auth-server'',''default'',''master'',''mybatis-plus.global-config.db-config.logic-not-delete-value'',''0'',''0'',NULL,NULL),
(51,''auth-server'',''default'',''master'',''mybatis-plus.global-config.refresh'',''true'',''0'',NULL,NULL),
(52,''auth-server'',''default'',''master'',''mybatis-plus.configuration.map-underscore-to-camel-case'',''false'',''0'',NULL,NULL),
(53,''auth-server'',''default'',''master'',''mybatis-plus.configuration.cache-enabled'',''false'',''0'',NULL,NULL),
(54,''auth-server'',''first'',''master'',''server.port'',''5001'',''0'',NULL,NULL),
(55,''auth-server'',''second'',''master'',''server.port'',''5002'',''0'',NULL,NULL),
(56,''auth-server'',''third'',''master'',''server.port'',''5003'',''0'',NULL,NULL),
(57,''api-gateway'',''second'',''master'',''server.port'',''9002'',''0'',NULL,NULL),
(58,''api-gateway'',''third'',''master'',''server.port'',''9003'',''0'',NULL,NULL),
(59,''api-gateway'',''first'',''master'',''server.port'',''9001'',''0'',NULL,NULL),
(60,''application'',''monitor'',''master'',''spring.sleuth.messaging.rabbit.enabled'',''true'',''0'',NULL,NULL),
(64,''api-gateway'',''default'',''master'',''zuul.routes.api-order.path'',''/api-order/**'',''0'',NULL,NULL),
(65,''api-gateway'',''default'',''master'',''zuul.routes.api-order.serviceId'',''order-service'',''0'',NULL,NULL),
(66,''api-gateway'',''default'',''master'',''zuul.sensitive-headers'',''Cookie,Set-Cookie'',''0'',NULL,NULL),
(67,''api-gateway'',''default'',''master'',''security.oauth2.resource.jwt.key-value'',''-----BEGIN PUBLIC KEY-----\r\nMIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCxhSSHOicbcI44fDkg1iYxtnLm\r\n9+X8fkHO9tTPecZfU3rsx3OYEqWu2zPQCKtNvS8fgvsuhXZbqAM7L5MRCT+e8mXC\r\nLmEITayOIWEyKtc0TQqLju5otVAqr3SF+CZ0u0NC0wWwTAFH2cFTs+e6YZCcmpb4\r\n54EPc8+FTRhgLVv54QIDAQAB\r\n-----END PUBLIC KEY-----'',''0'',NULL,NULL),
(68,''api-gateway'',''default'',''master'',''zuul.routes.oauth.path'',''/oauth/**'',''0'',NULL,NULL),
(69,''api-gateway'',''default'',''master'',''zuul.routes.oauth.serviceId'',''auth-server'',''0'',NULL,NULL),
(70,''api-gateway'',''default'',''master'',''zuul.routes.oauth.sensitiveHeaders'',''Cookie,Set-Cookie'',''0'',NULL,NULL),
(71,''api-gateway'',''default'',''master'',''zuul.routes.api-order.path.sensitiveHeaders'',''Cookie,Set-Cookie'',''0'',NULL,NULL),
(72,''application'',''monitor'',''master'',''spring.sleuth.web.client.enable'',''false'',''0'',NULL,NULL),
(73,''application'',''monitor'',''master'',''spring.sleuth.sampler.probability'',''1.0'',''0'',NULL,NULL),
(74,''application'',''monitor'',''master'',''spring.zipkin.discovery-client-enabled'',''true'',''0'',NULL,NULL),
(75,''application'',''monitor'',''master'',''spring.zipkin.base-url'',''http://monitor-server/'',''0'',NULL,NULL),
(76,''monitor-server'',''first'',''master'',''server.port'',''7101'',''0'',NULL,NULL),
(77,''monitor-server'',''default'',''master'',''spring.datasource.url'',''jdbc:mysql://10.49.15.244:3306/zentao?autoReconnect=true&useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true'',''0'',NULL,NULL),
(78,''monitor-server'',''default'',''master'',''spring.datasource.username'',''root'',''0'',NULL,NULL),
(79,''monitor-server'',''default'',''master'',''spring.datasource.password'',''fA0iTfSAyZ9YT5WkDfvVoAAHA41gkeYYsdMNqQJFR2eWooxXjg9GWEt9BkaKLt0NkKZfv8/PiQYDpJ9pTSI3NA=='',''0'',NULL,NULL),
(80,''monitor-server'',''default'',''master'',''spring.datasource.driver-class-name'',''com.mysql.cj.jdbc.Driver'',''0'',NULL,NULL),
(81,''monitor-server'',''default'',''master'',''spring.datasource.druid.initial-size'',''3'',''0'',NULL,NULL),
(82,''monitor-server'',''default'',''master'',''spring.datasource.druid.max-active'',''20'',''0'',NULL,NULL),
(83,''monitor-server'',''default'',''master'',''spring.datasource.druid.min-idle'',''3'',''0'',NULL,NULL),
(84,''monitor-server'',''default'',''master'',''spring.datasource.druid.max-wait'',''60000'',''0'',NULL,NULL),
(85,''monitor-server'',''default'',''master'',''spring.datasource.druid.time-between-eviction-runs-millis'',''60000'',''0'',NULL,NULL),
(86,''monitor-server'',''default'',''master'',''spring.datasource.druid.min-evictable-idle-time-millis'',''300000'',''0'',NULL,NULL),
(87,''monitor-server'',''default'',''master'',''spring.datasource.druid.validation-query'',''select \''x\'''',''0'',NULL,NULL),
(88,''monitor-server'',''default'',''master'',''spring.datasource.druid.validation-query-timeout'',''10000'',''0'',NULL,NULL),
(89,''monitor-server'',''default'',''master'',''spring.datasource.druid.test-while-idle'',''true'',''0'',NULL,NULL),
(90,''monitor-server'',''default'',''master'',''spring.datasource.druid.test-on-borrow'',''true'',''0'',NULL,NULL),
(91,''monitor-server'',''default'',''master'',''spring.datasource.druid.test-on-return'',''true'',''0'',NULL,NULL),
(92,''monitor-server'',''default'',''master'',''spring.datasource.druid.max-open-prepared-statements'',''200'',''0'',NULL,NULL),
(93,''monitor-server'',''default'',''master'',''spring.datasource.druid.max-pool-prepared-statement-per-connection-size'',''20'',''0'',NULL,NULL),
(94,''monitor-server'',''default'',''master'',''spring.datasource.druid.connect-properties.config.decrypt'',''true'',''0'',NULL,NULL),
(95,''monitor-server'',''default'',''master'',''spring.datasource.druid.connect-properties.config.decrypt.key'',''MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBALJUQV+coCweLv6wq+oavcy17n+XYOTo3jBKu3tfKE+1REum9Aihi/SPYLd88eXcdfjq5yawdMK2wJeZxiIVQv0CAwEAAQ=='',''0'',NULL,NULL),
(96,''monitor-server'',''default'',''master'',''spring.datasource.druid.filter.config.enabled'',''true'',''0'',NULL,NULL),
(97,''monitor-server'',''default'',''master'',''spring.datasource.druid.filter.stat.db-type'',''mysql'',''0'',NULL,NULL),
(98,''monitor-server'',''default'',''master'',''spring.datasource.druid.filter.stat.log-slow-sql'',''true'',''0'',NULL,NULL),
(99,''monitor-server'',''default'',''master'',''spring.datasource.druid.filter.stat.slow-sql-millis'',''2000'',''0'',NULL,NULL),
(100,''monitor-server'',''default'',''master'',''spring.datasource.druid.filter.stat.enabled'',''true'',''0'',NULL,NULL),
(101,''monitor-server'',''default'',''master'',''spring.datasource.druid.stat-view-servlet.enabled'',''true'',''0'',NULL,NULL),
(102,''monitor-server'',''default'',''master'',''spring.datasource.druid.stat-view-servlet.url-pattern'',''/druid/*'',''0'',NULL,NULL),
(103,''monitor-server'',''default'',''master'',''spring.datasource.druid.stat-view-servlet.login-username'',''yanglong'',''0'',NULL,NULL),
(104,''monitor-server'',''default'',''master'',''spring.datasource.druid.stat-view-servlet.login-password'',''13626878988'',''0'',NULL,NULL),
(105,''monitor-server'',''default'',''master'',''spring.datasource.druid.stat-view-servlet.allow'',NULL,''0'',NULL,NULL),
(106,''monitor-server'',''default'',''master'',''spring.datasource.druid.stat-view-servlet.deny'',NULL,''0'',NULL,NULL),
(107,''monitor-server'',''default'',''master'',''spring.datasource.druid.stat-view-servlet.reset-enable'',''true'',''0'',NULL,NULL),
(108,''application'',''default'',''master'',''spring.rabbitmq.host'',''127.0.0.1'',''0'',NULL,NULL),
(109,''application'',''default'',''master'',''spring.rabbitmq.username'',''springcloud'',''0'',NULL,NULL),
(110,''application'',''default'',''master'',''spring.rabbitmq.password'',''springcloud'',''0'',NULL,NULL),
(111,''application'',''default'',''master'',''spring.rabbitmq.port'',''5672'',''0'',NULL,NULL),
(112,''application'',''monitor'',''master'',''spring.sleuth.web.skipPattern'',''(^cleanup.*|.+favicon.*)'',''0'',NULL,NULL),
(113,''monitor-server'',''default'',''master'',''management.metrics.web.server.auto-time-requests'',''false'',''0'',NULL,NULL),
(114,''application'',''monitor'',''master'',''spring.zipkin.sender.type'',''rabbit'',''0'',NULL,NULL),
(115,''application'',''monitor'',''master'',''spring.zipkin.rabbitmq.queue'',''zipkin'',''0'',NULL,NULL),
(116,''monitor-server'',''default'',''master'',''zipkin.collector.rabbitmq.addresses'',''127.0.0.1:5672'',''0'',NULL,NULL),
(117,''monitor-server'',''default'',''master'',''zipkin.collector.rabbitmq.username'',''springcloud'',''0'',NULL,NULL),
(118,''monitor-server'',''default'',''master'',''zipkin.collector.rabbitmq.password'',''springcloud'',''0'',NULL,NULL),
(119,''monitor-server'',''default'',''master'',''zipkin.collector.rabbitmq.queue'',''zipkin'',''0'',NULL,NULL),
(120,''application'',''default'',''master'',''management.endpoints.web.exposure.include'',''*'',''0'',NULL,NULL),
(121,''api-gateway'',''default'',''master'',''zuul.routes.cart.path'',''/api-cart/**'',''0'',NULL,NULL),
(122,''api-gateway'',''default'',''master'',''zuul.routes.cart.serviceId'',''cart-service'',''0'',NULL,NULL);

/*Table structure for table `oauth_client_details` */

DROP TABLE IF EXISTS `oauth_client_details`;

CREATE TABLE `oauth_client_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT ''编号'',
  `clientId` varchar(32) DEFAULT NULL COMMENT ''client名称'',
  `clientSecret` varchar(256) DEFAULT NULL COMMENT ''client密码'',
  `scope` varchar(256) DEFAULT NULL COMMENT ''权限范围列表'',
  `autoapprove` varchar(256) DEFAULT NULL COMMENT ''自动授权的权限'',
  `resourceIds` varchar(256) DEFAULT NULL COMMENT ''资源id列表'',
  `authorizedGrantTypes` varchar(256) DEFAULT NULL COMMENT ''自动授权的类型'',
  `webServerRedirectUri` varchar(1024) DEFAULT NULL COMMENT ''web应用重定向列表'',
  `authorities` varchar(256) DEFAULT NULL COMMENT ''权限列表'',
  `accessTokenValidity` int(11) DEFAULT NULL COMMENT ''token有效期'',
  `refreshTokenValidity` int(11) DEFAULT NULL COMMENT ''refreshToken有效期'',
  `additionalInformation` varchar(256) DEFAULT NULL COMMENT ''附加信息'',
  `enabled` char(1) DEFAULT ''0'' COMMENT ''是否启用'',
  PRIMARY KEY (`id`),
  UNIQUE KEY `un_clients_id` (`clientId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT=''OAuth2客户端信息表'';

/*Data for the table `oauth_client_details` */

insert  into `oauth_client_details`(`id`,`clientId`,`clientSecret`,`scope`,`autoapprove`,`resourceIds`,`authorizedGrantTypes`,`webServerRedirectUri`,`authorities`,`accessTokenValidity`,`refreshTokenValidity`,`additionalInformation`,`enabled`) values
(1,''app'',''{noop}app'',''all'',''true'',''order-service,cart-service,api-gateway,auth-server'',''sms,implicit,password,client_credentials,authorization_code,refresh_token'',NULL,NULL,3600,5400,NULL,''0'');

/*Table structure for table `role_info` */

DROP TABLE IF EXISTS `role_info`;

CREATE TABLE `role_info` (
  `roleId` bigint(20) NOT NULL AUTO_INCREMENT COMMENT ''编号'',
  `roleName` varchar(32) DEFAULT NULL COMMENT ''角色名称'',
  `roleRemark` varchar(128) DEFAULT NULL COMMENT ''中文备注'',
  `enabled` char(1) DEFAULT ''0'' COMMENT ''是否启用，0启用，1禁用'',
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `role_info` */

insert  into `role_info`(`roleId`,`roleName`,`roleRemark`,`enabled`) values
(1,''user'',NULL,''0''),
(2,''admin'',NULL,''0''),
(3,''manager'',NULL,''0''),
(4,''kill'',NULL,''0''),
(5,''sam'',NULL,''0''),
(6,''alibaba'',NULL,''0''),
(7,''test'',NULL,''0'');

/*Table structure for table `user_base` */

DROP TABLE IF EXISTS `user_base`;

CREATE TABLE `user_base` (
  `userId` bigint(20) NOT NULL AUTO_INCREMENT COMMENT ''编号'',
  `userMobile` varchar(11) DEFAULT NULL COMMENT ''手机号'',
  `userPwd` varchar(128) DEFAULT NULL COMMENT ''密码'',
  `userName` varchar(64) DEFAULT NULL COMMENT ''姓名'',
  `userEmail` varchar(64) DEFAULT NULL COMMENT ''邮编'',
  `userGender` char(1) DEFAULT NULL COMMENT ''性别'',
  `userStatus` char(1) DEFAULT ''0'' COMMENT ''用户状态，0正常，1异常，2锁定，3禁止'',
  `createTime` datetime DEFAULT NULL COMMENT ''创建时间'',
  `modifyTime` datetime DEFAULT NULL COMMENT ''更新时间'',
  `loginTime` datetime DEFAULT NULL COMMENT ''最新登录时间'',
  PRIMARY KEY (`userId`),
  UNIQUE KEY `un_user_account_phone` (`userMobile`),
  UNIQUE KEY `un_user_account_email` (`userEmail`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT=''用户基础表'';

/*Data for the table `user_base` */

insert  into `user_base`(`userId`,`userMobile`,`userPwd`,`userName`,`userEmail`,`userGender`,`userStatus`,`createTime`,`modifyTime`,`loginTime`) values
(1,''133556'',''{noop}2222'',''1111'',''11111'',''1'',''0'',''2018-08-28 09:52:03'',''2018-08-28 09:52:06'',''2018-08-28 09:52:08'');

/*Table structure for table `user_role` */

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT ''编号'',
  `userId` bigint(20) DEFAULT NULL COMMENT ''用户id'',
  `roleId` bigint(20) DEFAULT NULL COMMENT ''角色id'',
  `enabled` char(1) DEFAULT ''0'' COMMENT ''0启用，1禁用'',
  PRIMARY KEY (`id`),
  KEY `fk_user_role_user` (`userId`),
  KEY `fk_user_role_role` (`roleId`),
  KEY `index_user_role_enabled` (`enabled`),
  CONSTRAINT `fk_user_role_role` FOREIGN KEY (`roleId`) REFERENCES `role_info` (`roleId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_user_role_user` FOREIGN KEY (`userId`) REFERENCES `user_base` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `user_role` */

insert  into `user_role`(`id`,`userId`,`roleId`,`enabled`) values
(1,1,1,''0''),
(2,1,2,''0''),
(3,1,5,''0'');

/*Table structure for table `zipkin_annotations` */

DROP TABLE IF EXISTS `zipkin_annotations`;

CREATE TABLE `zipkin_annotations` (
  `trace_id_high` bigint(20) NOT NULL DEFAULT ''0'' COMMENT ''If non zero, this means the trace uses 128 bit traceIds instead of 64 bit'',
  `trace_id` bigint(20) NOT NULL COMMENT ''coincides with zipkin_spans.trace_id'',
  `span_id` bigint(20) NOT NULL COMMENT ''coincides with zipkin_spans.id'',
  `a_key` varchar(255) NOT NULL COMMENT ''BinaryAnnotation.key or Annotation.value if type == -1'',
  `a_value` blob COMMENT ''BinaryAnnotation.value(), which must be smaller than 64KB'',
  `a_type` int(11) NOT NULL COMMENT ''BinaryAnnotation.type() or -1 if Annotation'',
  `a_timestamp` bigint(20) DEFAULT NULL COMMENT ''Used to implement TTL; Annotation.timestamp or zipkin_spans.timestamp'',
  `endpoint_ipv4` int(11) DEFAULT NULL COMMENT ''Null when Binary/Annotation.endpoint is null'',
  `endpoint_ipv6` binary(16) DEFAULT NULL COMMENT ''Null when Binary/Annotation.endpoint is null, or no IPv6 address'',
  `endpoint_port` smallint(6) DEFAULT NULL COMMENT ''Null when Binary/Annotation.endpoint is null'',
  `endpoint_service_name` varchar(255) DEFAULT NULL COMMENT ''Null when Binary/Annotation.endpoint is null'',
  UNIQUE KEY `trace_id_high` (`trace_id_high`,`trace_id`,`span_id`,`a_key`,`a_timestamp`) COMMENT ''Ignore insert on duplicate'',
  KEY `trace_id_high_2` (`trace_id_high`,`trace_id`,`span_id`) COMMENT ''for joining with zipkin_spans'',
  KEY `trace_id_high_3` (`trace_id_high`,`trace_id`) COMMENT ''for getTraces/ByIds'',
  KEY `endpoint_service_name` (`endpoint_service_name`) COMMENT ''for getTraces and getServiceNames'',
  KEY `a_type` (`a_type`) COMMENT ''for getTraces'',
  KEY `a_key` (`a_key`) COMMENT ''for getTraces'',
  KEY `trace_id` (`trace_id`,`span_id`,`a_key`) COMMENT ''for dependencies job''
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPRESSED;

/*Data for the table `zipkin_annotations` */

/*Table structure for table `zipkin_dependencies` */

DROP TABLE IF EXISTS `zipkin_dependencies`;

CREATE TABLE `zipkin_dependencies` (
  `day` date NOT NULL,
  `parent` varchar(255) NOT NULL,
  `child` varchar(255) NOT NULL,
  `call_count` bigint(20) DEFAULT NULL,
  `error_count` bigint(20) DEFAULT NULL,
  UNIQUE KEY `day` (`day`,`parent`,`child`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPRESSED;

/*Data for the table `zipkin_dependencies` */

/*Table structure for table `zipkin_spans` */

DROP TABLE IF EXISTS `zipkin_spans`;

CREATE TABLE `zipkin_spans` (
  `trace_id_high` bigint(20) NOT NULL DEFAULT ''0'' COMMENT ''If non zero, this means the trace uses 128 bit traceIds instead of 64 bit'',
  `trace_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `debug` bit(1) DEFAULT NULL,
  `start_ts` bigint(20) DEFAULT NULL COMMENT ''Span.timestamp(): epoch micros used for endTs query and to implement TTL'',
  `duration` bigint(20) DEFAULT NULL COMMENT ''Span.duration(): micros used for minDuration and maxDuration query'',
  UNIQUE KEY `trace_id_high` (`trace_id_high`,`trace_id`,`id`) COMMENT ''ignore insert on duplicate'',
  KEY `trace_id_high_2` (`trace_id_high`,`trace_id`,`id`) COMMENT ''for joining with zipkin_annotations'',
  KEY `trace_id_high_3` (`trace_id_high`,`trace_id`) COMMENT ''for getTracesByIds'',
  KEY `name` (`name`) COMMENT ''for getTraces and getSpanNames'',
  KEY `start_ts` (`start_ts`) COMMENT ''for getTraces ordering and range''
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPRESSED;

/*Data for the table `zipkin_spans` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;