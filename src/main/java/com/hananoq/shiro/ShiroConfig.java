package com.hananoq.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

/**
 * @author :花のQ
 * @since 2020/7/7 9:22
 * shiro的配置类
 **/
@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("manager") DefaultWebSecurityManager manager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        //配置安全管理器
        factoryBean.setSecurityManager(manager);

        LinkedHashMap<String, String> filterMap = FilterMap.getFilterMap();

        //配置拦截map
        factoryBean.setFilterChainDefinitionMap(filterMap);
        factoryBean.setLoginUrl("/user/login");

        return factoryBean;
    }

    @Bean("manager")
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(userRealm);
        return manager;
    }

    @Bean("userRealm")
    public UserRealm userRealm() {
        return new UserRealm();
    }

    /**
     * 配置shiroDialect，用于thymeleaf和shiro标签配合使用
     */
    @Bean("shiroDialect")
    public ShiroDialect getShiroDialect() {
        return new ShiroDialect();
    }

}
