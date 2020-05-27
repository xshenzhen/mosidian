package com.mosidian.web.config;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;


public class CodeGenerator {

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator autoGenerator = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir("D:/IDEA Demos/demo/src/main/java");
        gc.setAuthor("shenzhen");
        gc.setFileOverride(true);
        gc.setIdType(IdType.AUTO);
        gc.setServiceName("%sService");
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(true);

        autoGenerator.setGlobalConfig(gc);
        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/mosidian?characterEncoding=utf-8&useSSL=true&serverTimezone=GMT%2b8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("admin");

        autoGenerator.setDataSource(dsc);
        //策略配置
        StrategyConfig sc=new StrategyConfig();
        sc.setCapitalMode(true);
        sc.setNaming(NamingStrategy.underline_to_camel);
        sc.setTablePrefix("sys_");
        sc.setInclude("sys_contact_us");
        sc.setSuperControllerClass("com.mosidian.web.controller.BaseController");
        sc.setEntityLombokModel(true);
        sc.setRestControllerStyle(true);
        autoGenerator.setStrategy(sc);
        //包名策略配置
        PackageConfig pc=new PackageConfig();
         pc.setParent("com.mosidian.web")
           .setMapper("dao.sys")
           .setService("service.sys")
           .setController("controller.sys")
           .setEntity("model.sys")
           .setXml("mapper");

        autoGenerator.setPackageInfo(pc);

        autoGenerator.execute();

    }

}
