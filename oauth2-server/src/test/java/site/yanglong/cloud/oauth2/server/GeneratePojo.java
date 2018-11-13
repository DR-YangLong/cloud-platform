package site.yanglong.cloud.oauth2.server;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * functional describe: {@see http://baomidou.oschina.io/mybatis-plus-doc/#/generate-code}
 *
 * @author DR.YangLong [410357434@163.com]
 * @version 1.0    2017/11/1
 */
public class GeneratePojo {
    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir("E://project_java//cloud-platform//oauth2-server//src//test//java")
        .setAuthor("Dr.YangLong")
        .setFileOverride(true)
        .setActiveRecord(true)// 不需要ActiveRecord特性的请改为false
        .setEnableCache(false)// XML 二级缓存
        .setBaseResultMap(true)// XML ResultMap
        .setBaseColumnList(false)// XML columList
        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        .setMapperName("%sMapper")
        .setXmlName("%sMapper")
        .setServiceName("%sService")
        .setServiceImplName("%sServiceImpl")
        .setControllerName("%sController");
        mpg.setGlobalConfig(gc);
        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        dsc.setUrl("jdbc:mysql://10.49.15.244:3306/zentao?characterEncoding=utf8");
        mpg.setDataSource(dsc);
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setEntityLombokModel(true);
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
        strategy.setExclude(new String[]{"application_config"}); // 排除生成的表
        strategy.setRestControllerStyle(true);
        mpg.setStrategy(strategy);
        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("site.yanglong.cloud.oauth2.server.aa")
                .setController("controller")
                .setEntity("model")
                .setService("service")
                .setServiceImpl("service.impl")
                .setMapper("mapper")
                .setXml("mapper.xml");
        mpg.setPackageInfo(pc);
        // 关闭默认 xml 生成，调整生成 至 根目录
        TemplateConfig tc = new TemplateConfig();
        tc.setEntity("/entity.java.vm");
        tc.setService("/service.java.vm");
        tc.setServiceImpl("/serviceImpl.java.vm");
        tc.setController("/controller.java.vm");
        mpg.setTemplate(tc);
        // 执行生成
        mpg.execute();
    }

}
