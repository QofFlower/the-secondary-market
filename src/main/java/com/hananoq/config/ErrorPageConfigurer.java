package com.hananoq.config;

import org.springframework.boot.web.server.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * @author :花のQ
 * @since 2020/7/12 10:51
 * 自定义跳转错误页面
 **/
@Configuration
public class ErrorPageConfigurer {
    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer() {
        return factory -> {
            ErrorPage errorPage404 = new ErrorPage(HttpStatus.NOT_FOUND, "/errorpages/404.html");
            factory.addErrorPages(errorPage404);
        };
    }
}
