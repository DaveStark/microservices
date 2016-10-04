package com.dave.microservices.auth;

import javax.inject.Inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Inject
	private UserDetailsService userDetailsService;//Custom user service which allow to find the user in a database or any place(UserDetailsServiceApp)

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// @formatter:off
		http
			.authorizeRequests().anyRequest().authenticated()
//			.and()
//		.formLogin()
//			.loginPage("/login")//this is used to specify what will be the url to receive a request to authenticate
//			.usernameParameter("username").passwordParameter("password")//those parameters which will be received in the request(However these are the default name values)
//			//TODO refere to:
//			//http://docs.spring.io/spring-security/site/docs/3.2.x/guides/form.html
//			//and 
//			//TODO refer to OAUTH to:
//			//http://projects.spring.io/spring-security-oauth/docs/oauth2.html
//			.permitAll()
//			.and()
//		.logout()
//			.permitAll()
			.and()
		.csrf().disable();
		// @formatter:on
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
				.passwordEncoder(new BCryptPasswordEncoder());//Configure the password which the OAuth service is going to match or create the password
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}

//Access tokens are now available from our new Authorization Server in /oauth/token url thanks to 'spring-security-oauth2'
//http://projects.spring.io/spring-security-oauth/docs/oauth2.html