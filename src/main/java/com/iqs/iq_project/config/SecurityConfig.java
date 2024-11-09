package com.iqs.iq_project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.iqs.iq_project.filters.JwtAuthFilter;
import com.iqs.iq_project.services.impl.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	private final UserDetailsServiceImpl userDetailsServiceImpl;
	private final JwtAuthFilter jwtAuthFilter;
	private static final String SUPER = "SUPERUSER";
	private  static final String ADMIN = "ADMIN";
	private static final String USER = "USER";
	
	public SecurityConfig(UserDetailsServiceImpl userDetailsServiceImpl, JwtAuthFilter jwtAuthFilter) {
		super();
		this.userDetailsServiceImpl = userDetailsServiceImpl;
		this.jwtAuthFilter = jwtAuthFilter;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
				.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(
						req->req.requestMatchers(
                                "/actuator/metrics",
                                "/actuator/health",
                                "/actuator/metrics/**",
                                "/v2/api-docs",
                                "/v3/api-docs",
                                "/v3/api-docs/**",
                                "/swagger-resources",
                                "/swagger-resources/**",
                                "/configuration/ui",
                                "/configuration/security",
                                "/swagger-ui/**",
                                "/webjars/**",
                                "/swagger-ui.html",
								"api/rest/**",
								"/api/login/**",
								"/api/register/**",
								"/api/signin/**",
								"/api/signup/**")
						.permitAll()
						.requestMatchers("/api/users/**").hasAuthority(SUPER)
						.requestMatchers("/api/students/**").hasAnyAuthority(SUPER,ADMIN)
						.requestMatchers("/api/student/{id}/get/**", "/api/student/{id}/edit/**","/api/student/{id}/delete/**", "/api/token", "/api/student/{userId}/add/**", "/api/student/search/**", "/api/user/**", "/api/{userId}/students/**").hasAnyAuthority(SUPER,ADMIN,USER)
						.anyRequest()
						.authenticated())
				.userDetailsService(userDetailsServiceImpl)
				.sessionManagement(session->session
						.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
	  
}
