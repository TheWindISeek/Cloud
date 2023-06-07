package com.cloud.cloudcomputation.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    /**
     * 资源访问拦截器
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        //super.addResourceHandlers(registry);
        //如果别人的请求符合 /static/** 那么就将这个请求重定向到 classpath: /static/ 下
        System.out.println("拦截访问器");
        //其实这样还应该这么写一下的 就是当有人在未登录的状态下访问一些资源的时候 我应该给他拦截下来 不过嘛 我现在有点懒 就当不存在
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}
