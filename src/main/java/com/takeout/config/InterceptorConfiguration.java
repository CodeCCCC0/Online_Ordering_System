package com.takeout.config;

import com.takeout.interceptor.Interceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        InterceptorRegistration registration = registry.addInterceptor(new Interceptor());
        registration.addPathPatterns("/**");
        registration.excludePathPatterns(
                "/updatepassword",
                "/retrieve*",
                "/register*",
                "/detail*",
                "/del*",
                "/sign*",
                "/email*",
                "/error*",
                "/login*",
                "/search*",
                "/index*",
                "/is*",
                "/get*",
                "/img/**",
                "/css/**",
                "/js/**"
                );
    }
}
