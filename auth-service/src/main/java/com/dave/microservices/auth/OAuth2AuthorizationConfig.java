package com.dave.microservices.auth;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
@EnableAuthorizationServer 
class OAuth2AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

	private TokenStore tokenStore = new InMemoryTokenStore(); //The default InMemoryTokenStore but also exists  JdbcTokenStore

	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;

	@Inject
	private UserDetailsService userDetailsService;

	@Autowired
	private Environment env;

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

		// TODO persist clients details

		// @formatter:off
		clients.inMemory()
				.withClient("browser")
				.authorizedGrantTypes("refresh_token", "password")
				.scopes("ui")
		.and()
				.withClient("metrics-service")//This is one of the name of the clients, you can change it for another different one
				.secret(env.getProperty("0987"))
				//.secret(env.getProperty("METRICS_SERVICE_PASSWORD"))
				.authorizedGrantTypes("client_credentials", "refresh_token")
				.scopes("server")
		.and()
				.withClient("statistics-service")
				.secret(env.getProperty("STATISTICS_SERVICE_PASSWORD"))
				.authorizedGrantTypes("client_credentials", "refresh_token")
				.scopes("server")
		.and()
				.withClient("notification-service")
				.secret(env.getProperty("NOTIFICATION_SERVICE_PASSWORD"))
				.authorizedGrantTypes("client_credentials", "refresh_token")
				.scopes("server");
		// @formatter:on
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints
				.tokenStore(tokenStore)
				.authenticationManager(authenticationManager)
				.userDetailsService(userDetailsService);
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		oauthServer
				.tokenKeyAccess("permitAll()")
				.checkTokenAccess("isAuthenticated()");
				//.checkTokenAccess("isAuthenticated() \\ hasRole('ROLE_NAME')"); //Use this to assign access to a specific role
	}
}