package com.example.basicSecurity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		
		auth
			.inMemoryAuthentication()
			.withUser("admin")
				.password( encoder.encode("adminpass"))
				.roles("ADMIN", "USER")
			.and().withUser("user")
				.password(encoder.encode("userpass"))
				.roles("USER");
	}
	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception{
//		
//		http.formLogin();
//		
//		http
//			.authorizeRequests()
//				.antMatchers("/admins").hasRole("ADMIN")
//				.antMatchers("/users").hasAnyRole("ADMIN", "USER")
//				.antMatchers("/")
//				.permitAll();
//		
//	}
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .anyRequest()
            .authenticated()
            .and()
            .formLogin()
            .loginPage("/login")
            .permitAll()
            .successForwardUrl("/index")
            .and()
            .logout()
            .permitAll()
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .logoutSuccessUrl("/login");
    }
	
	

}
