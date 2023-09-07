/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chh.configs;

import com.chh.authencation.CustomAccessDeniedHandler;
import com.chh.authencation.JwtAuthenticationTokenFilter;
import com.chh.authencation.RestAuthenticationEntryPoint;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author huynh
 */
@Configuration
@EnableWebSecurity
@EnableTransactionManagement
@ComponentScan(basePackages = {
    "com.chh.repository",
        "com.chh.service"
})
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    private UserDetailsService userDetailsService;
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
       return new BCryptPasswordEncoder();
    }
    
    @Bean
    public CustomAccessDeniedHandler customAccessDeniedHandler(){
        return new CustomAccessDeniedHandler();
    }
    
    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() throws Exception {
        JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter = new JwtAuthenticationTokenFilter();
        jwtAuthenticationTokenFilter.setAuthenticationManager(authenticationManager());
        return jwtAuthenticationTokenFilter;
  }
    
    @Bean
    public RestAuthenticationEntryPoint restServicesEntryPoint(){
        return new RestAuthenticationEntryPoint();
    }
    
    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
    
    @Bean
    public Cloudinary cloudinary() {
        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
        "cloud_name", "dx3qnjfg0",
        "api_key", "955269338579183",
        "api_secret", "moI4B6ncVjVEOihk2UE8YeOXTYw",
        "secure", true));
        return cloudinary;
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
        .passwordEncoder(passwordEncoder());
}

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().ignoringAntMatchers("/api/**");
        http.authorizeRequests().antMatchers("/api/login**").permitAll()
        .antMatchers("/api/register**").permitAll();
        
        http.antMatcher("/api/**").httpBasic().authenticationEntryPoint(restServicesEntryPoint()).and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
                
        .antMatchers(HttpMethod.GET, "/api/shippers/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_SHIPPER')")
        .antMatchers(HttpMethod.GET, "/api/users/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
        
        .antMatchers(HttpMethod.POST, "/api/shippers/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_SHIPPER')")
        .antMatchers(HttpMethod.POST, "/api/users/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
        .antMatchers(HttpMethod.POST, "/api/products/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
        .antMatchers(HttpMethod.POST, "/api/auctions/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_SHIPPER')")
                
        .antMatchers(HttpMethod.DELETE, "/api/**").access("hasRole('ROLE_ADMIN')").and()
        .addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class)
        .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler());
        
    }
 
}
