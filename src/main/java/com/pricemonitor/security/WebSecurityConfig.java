package com.pricemonitor.security;

import com.pricemonitor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity(debug = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    protected DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userService);
        return daoAuthenticationProvider;
    }

    protected void configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .cors().disable()
                .httpBasic().and()
                /*.authorizeRequests().antMatchers(HttpMethod.POST, "/app/*").hasRole("ADMIN")
                .and()*/
                .authorizeRequests().mvcMatchers(HttpMethod.GET, "/app/merchant/*").hasRole("ADMIN")
                .and()
                .authorizeRequests().mvcMatchers(HttpMethod.POST, "/app/merchant/*").hasRole("ADMIN")
                .and()
                .authorizeRequests().mvcMatchers(HttpMethod.GET, "/app/profiles/*").hasRole("ADMIN")
                .and()
                .authorizeRequests().mvcMatchers(HttpMethod.POST, "/app/profiles/*").hasRole("ADMIN")
                .and()
                .authorizeRequests().mvcMatchers(HttpMethod.GET, "/app/category/*").hasRole("ADMIN")
                .and()
                .authorizeRequests().mvcMatchers(HttpMethod.POST, "/app/category/*").hasRole("ADMIN")
                .and()
                .authorizeRequests().mvcMatchers(HttpMethod.POST, "/app/product/*").hasRole("ADMIN")
                .and()
                .authorizeRequests().mvcMatchers(HttpMethod.GET, "/app/product/*").hasRole("ADMIN")
                .and()
                .authorizeRequests().mvcMatchers(HttpMethod.POST, "/app/merchant/*").hasRole("MERCHANT")
                .and()
                .authorizeRequests().mvcMatchers(HttpMethod.GET, "/app/merchant/*").hasRole("MERCHANT")
                .and()
                .authorizeRequests().mvcMatchers(HttpMethod.POST, "/app/profiles/*").hasRole("USER")
                .and()
                .authorizeRequests().mvcMatchers(HttpMethod.GET, "/app/profiles/*").hasRole("USER")
                .and()
                .authorizeRequests().mvcMatchers(HttpMethod.POST, "/app/product/*").hasRole("USER")
                .and()
                .authorizeRequests().mvcMatchers(HttpMethod.GET, "/app/product/*").hasRole("USER")
                //.anyRequest().authenticated()
                .and().formLogin();  //.defaultSuccessUrl("/app/product/all", true);
    }

}
