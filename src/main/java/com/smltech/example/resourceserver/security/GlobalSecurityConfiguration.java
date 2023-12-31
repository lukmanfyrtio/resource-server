package com.smltech.example.resourceserver.security;

import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

@EnableWebSecurity
public class GlobalSecurityConfiguration extends KeycloakWebSecurityConfigurerAdapter {

	@Autowired
	private CustomAccessDeniedHandler customAccessDeniedHandler;

	@Autowired
	private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

	@Bean
	@Override
	protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
		return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) {
		KeycloakAuthenticationProvider keycloakAuthenticationProvider = keycloakAuthenticationProvider();
		keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(new SimpleAuthorityMapper()); // prefix = "ROLE_"
		auth.authenticationProvider(keycloakAuthenticationProvider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		super.configure(http);
		// Configure unauthorized and forbidden handling
		http.exceptionHandling().authenticationEntryPoint(customAuthenticationEntryPoint)
				.accessDeniedHandler(customAccessDeniedHandler); // Use the custom AccessDeniedHandler

		http.authorizeRequests().antMatchers("/api/admin/**").hasRole("admin").antMatchers("/api/user/**")
				.hasRole("user").anyRequest().authenticated().and().httpBasic()
				.authenticationEntryPoint(customAuthenticationEntryPoint);
	}

}
