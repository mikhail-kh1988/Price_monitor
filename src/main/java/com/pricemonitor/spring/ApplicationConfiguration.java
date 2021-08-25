package com.pricemonitor.spring;

import com.pricemonitor.repositories.IProfileRepository;
import com.pricemonitor.repositories.IUserRepository;
import com.pricemonitor.repositories.impl.ProfileRepository;
import com.pricemonitor.repositories.impl.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


@Configuration
@ComponentScan(basePackages = "com.pricemonitor")
public class ApplicationConfiguration extends WebMvcConfigurationSupport {


}
