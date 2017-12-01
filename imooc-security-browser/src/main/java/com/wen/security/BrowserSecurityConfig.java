/**
 * 
 */
package com.wen.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.wen.security.core.properties.SecurityProperties;

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Autowired
	private SecurityProperties securityProperties;
	protected void configure(HttpSecurity http) throws Exception {  
        http  
        .formLogin()  
        .loginPage("/imooc-signIn.html")//指定登录页是”/login”  
        .loginProcessingUrl("/authentication/form")
        .and()  
        .authorizeRequests()  
        .antMatchers("/imooc-signIn.html",securityProperties.getBrowser().getLoginPage()).permitAll()//访问：/home 无需登录认证权限  
        .anyRequest()
        .authenticated()
        .and()
        .csrf().disable();
    }  
}
