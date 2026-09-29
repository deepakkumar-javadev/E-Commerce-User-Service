package com.deepak.UserService.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

	private final JwtAuthenticationFilter jwtAuthenticationFilter;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http

				// =========================
				// CSRF
				// =========================
				.csrf(csrf -> csrf.disable())

				// =========================
				// SESSION
				// =========================
				.sessionManagement(session -> session
						.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

				// =========================
				// AUTHORIZATION
				// =========================
				.authorizeHttpRequests(auth -> auth

						// =========================
						// PUBLIC APIs
						// No JWT required
						// =========================
						.requestMatchers(
								"/auth/register",
								"/auth/registerAdmin",
								"/auth/login",
								"/actuator/**",
								"/swagger-ui/**",
							    "/swagger-ui.html",
							    "/v3/api-docs/**"
						).permitAll()

						// =========================
						// CORS PREFLIGHT
						// =========================
						.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

						// =========================
						// ADMIN ONLY
						// Existing ADMIN can create
						// another ADMIN
						// =========================
						.requestMatchers("/auth/create-admin")
						.hasRole("ADMIN")

						// =========================
						// LOGGED-IN USER
						// notification feign call
						// =========================
						.requestMatchers("/auth/getuser/**")
						.permitAll()

						// =========================
						// ADMIN ONLY
						// =========================
						.requestMatchers("/auth/UserDetails/**")
						.hasRole("ADMIN")

						// =========================
						// ALL OTHER APIs
						// =========================
						.anyRequest().authenticated())

				// =========================
				// JWT FILTER
				// =========================
				.addFilterBefore(
						jwtAuthenticationFilter,
						UsernamePasswordAuthenticationFilter.class
				);

		return http.build();
	}

	// =========================
	// PASSWORD ENCODER
	// =========================
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}