package com.insurance.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	 @Autowired
	 UserDetailsService userDetailsService;

     @Override
	 protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//    	 auth.userDetailsService(userDetailsService);
    	 auth.inMemoryAuthentication()
    	 		.withUser("admin")
    	 		.password("pass")
    	 		.roles("ADMIN");
    	 		
	 }

     @Override
     public void configure(WebSecurity web) throws Exception {    
         web.ignoring().antMatchers("/v2/api-docs/**");
         web.ignoring().antMatchers("/swagger.json");
         web.ignoring().antMatchers("/swagger-ui.html");
         web.ignoring().antMatchers("/swagger-resources/**");
         web.ignoring().antMatchers("/webjars/**");
     }
     
	 @Override
	 protected void configure(HttpSecurity http) throws Exception {
		 http.authorizeRequests()
	                .antMatchers("/**/admin").hasRole("ADMIN")
	                .antMatchers("**/handler").hasAnyRole("ADMIN","HANDLER")
	                .antMatchers("**/user").hasAnyRole("ADMIN","HANDLER","USER")
	                .antMatchers("/").permitAll()
	                .and().formLogin();
		 http.authorizeRequests()
	        .antMatchers(
	            "/v2/api-docs", 
	            "/swagger-resources/**",  
	            "/swagger-ui.html", 
	            "/webjars/**" ,
	            "/swagger.json")
	        .permitAll();
	 }

	 @Bean
	 public PasswordEncoder getPasswordEncoder() {
		 return NoOpPasswordEncoder.getInstance();
	 }
}
