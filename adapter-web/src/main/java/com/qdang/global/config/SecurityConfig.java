package com.qdang.global.config;

import com.qdang.global.filter.JwtAuthenticationFilter;
import com.qdang.global.filter.JwtExceptionFilter;
import com.qdang.global.http.HeaderTokenExtractor;
import com.qdang.global.jwt.JwtResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final JwtResolver jwtResolver;
	private final HeaderTokenExtractor headerTokenExtractor;
//	private JwtExceptionFilter jwtExceptionFilter;
//	private JwtAuthenticationFilter jwtAuthenticationFilter;

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		JwtExceptionFilter jwtExceptionFilter =
				new JwtExceptionFilter();
		JwtAuthenticationFilter jwtAuthenticationFilter =
				new JwtAuthenticationFilter(headerTokenExtractor, jwtResolver);

		http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		http.addFilterBefore(jwtExceptionFilter, JwtAuthenticationFilter.class);

		http.httpBasic().disable();
		http.csrf().disable();
		http.cors().disable();

//		http.sessionManagement()
//				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.formLogin().disable();

		http.authorizeRequests()
				.antMatchers(("/manager/**"))
				.access("hasAuthority('MANAGER') or hasAuthority('ADMIN')")
//				.hasAuthority("MANAGER")
				.antMatchers(("/admin/**"))
				.access("hasAuthority('ADMIN')")
				.anyRequest().authenticated();
		return http.build();
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer(){
		return web -> {
			web.ignoring()
					.requestMatchers(PathRequest.toStaticResources().atCommonLocations())
					.antMatchers("/v1/auth/login");
		};
	}

}
