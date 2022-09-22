package com.nuwa.miaosha.common.db.multiple;//package com.nuwa.common.db.multiple;
//
//import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.core.io.support.ResourcePatternResolver;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//
//import javax.sql.DataSource;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @author jijunhui
// * @version 1.0.0
// * @date 2021/1/30 14:24
// * @description 多数据源配置
// */
//@Configuration
//public class MultipleDataSourceConfig {
//
//    @Autowired
//    private DynamicDataSource dynamicDataSource;
//    /**
//     * 读配置
//     *
//     * @return
//     */
//    @Bean("readDataSource")
//    @ConfigurationProperties("spring.datasource.read")
//    public DataSource readDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    /**
//     * 写配置
//     *
//     * @return
//     */
//    @Bean("writeDataSource")
//    @ConfigurationProperties("spring.datasource.write")
//    public DataSource writeDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
////    /**
////     * 动态数据源配置
////     * @return
////     */
////    @Bean
////    @Primary
////    public DataSource multipleDataSource(@Qualifier("readDataSource") DataSource readDataSource, @Qualifier("writeDataSource") DataSource writeDataSource) {
////        Map< Object, Object > targetDataSources = new HashMap<>();
////        targetDataSources.put("readDataSource", readDataSource);
////        targetDataSources.put("writeDataSource", writeDataSource);
////        //添加数据源
////        dynamicDataSource.setTargetDataSources(targetDataSources);
////        //设置默认数据源
////        dynamicDataSource.setDefaultTargetDataSource(writeDataSource());
////        return dynamicDataSource;
////    }
//
//    /**
//     * 多数据源需要自己设置sqlSessionFactory
//     */
//    @Bean("sqlSessionFactory")
//    public SqlSessionFactory sqlSessionFactory() throws Exception {
//
//        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
//        sqlSessionFactory.setDataSource(dynamicDataSource);
//
////        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
////        bean.setDataSource(dynamicDataSource);
////        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        // 实体类对应的位置
////        bean.setTypeAliasesPackage(resolver);
//        // mybatis的XML的配置
////        bean.setMapperLocations(resolver.getResources(mapperLocation));
////        bean.setConfigLocation(resolver.getResource(configLocation));
//        return sqlSessionFactory.getObject();
//    }
//
//    /**
//     * 设置事务，事务需要知道当前使用的是哪个数据源才能进行事务处理
//     */
//    @Bean
//    public DataSourceTransactionManager dataSourceTransactionManager() {
//        return new DataSourceTransactionManager(dynamicDataSource);
//    }
//}
