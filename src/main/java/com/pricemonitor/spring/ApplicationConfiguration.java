package com.pricemonitor.spring;

import com.pricemonitor.repositories.IProfileRepository;
import com.pricemonitor.repositories.IUserRepository;
import com.pricemonitor.repositories.impl.ProfileRepository;
import com.pricemonitor.repositories.impl.UserRepository;
import com.pricemonitor.tools.LoggerInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.servlet.MultipartConfigElement;


@Configuration
@ComponentScan(basePackages = "com.pricemonitor")
public class ApplicationConfiguration extends WebMvcConfigurationSupport {

    @Bean
    public LoggerInfo loggerInfo(){
        return new LoggerInfo(getApplicationContext().getClass());
    }

    @Bean
    public CommonsMultipartResolver commonsMultipartResolver(){
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(1000000);
        return multipartResolver;
    }

    @Bean
    public StandardServletMultipartResolver multipartResolver(){
        return new StandardServletMultipartResolver();
    }

}
