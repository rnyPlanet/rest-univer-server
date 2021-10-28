package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	private static final String RESOURCE_ID = "resource_id";

	private static final String ADMIN_ENDPOINT = "/api/v1/admin/**";
	private static final String LOGIN_ENDPOINT = "/api/v1/auth/**";
	private static final String CONSULTATION_ENDPOINT = "/api/v1/consultations/**";
    private static final String SCHEDULER_ENDPOINT = "/api/v1/scheduler/**";
    private static final String TEACHER_ENDPOINT = "/api/v1/teacher/**";
    private static final String CALENDAR_ENDPOINT = "/api/v1/calendar/**";
    private static final String PHOTO_ENDPOINT = "/api/v1/photo/**";

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources.resourceId(RESOURCE_ID).stateless(false);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
        http.
                anonymous().disable()
                .authorizeRequests()
					.antMatchers(LOGIN_ENDPOINT).permitAll()
					.antMatchers(CALENDAR_ENDPOINT).permitAll()
					.antMatchers(CONSULTATION_ENDPOINT).hasAnyRole("ADMIN", "USER", "SCHEDULER", "TEACHER")
					.antMatchers(ADMIN_ENDPOINT).hasRole("ADMIN")
					.antMatchers(PHOTO_ENDPOINT).hasRole("USER")
					.antMatchers(SCHEDULER_ENDPOINT).hasAnyRole("SCHEDULER")
					.antMatchers(TEACHER_ENDPOINT).hasAnyRole("TEACHER")
					.antMatchers("/admin/**").access("hasRole('ADMIN')")
                .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
	}



}