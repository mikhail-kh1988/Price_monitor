package com.pricemonitor;

import com.pricemonitor.repositories.*;
import com.pricemonitor.repositories.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@PropertySource("classpath:hibernate.properties")
public class ContextConfigurationTest {

    @Bean
    public ICategoryRepository categoryRepository(){
        return new CategoryRepository();
    }

    @Bean
    public IUserRepository userRepository(){
        return new UserRepository();
    }

    @Bean
    public IProfileRepository profileRepository(){
        return new ProfileRepository();
    }

    @Bean
    public IRoleRepository roleRepository(){
        return new RoleRepository();
    }

    @Bean
    public IProductRepository productRepository(){
        return new ProductRepository();
    }

    @Bean
    public IMerchantRepository merchantRepository(){
        return new MerchantRepository();
    }
}
