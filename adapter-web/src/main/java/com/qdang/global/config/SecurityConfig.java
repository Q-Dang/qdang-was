package com.qdang.global.config;

import com.qdang.global.filter.JwtAuthenticationFilter;
import com.qdang.global.filter.JwtExceptionFilter;
import com.qdang.global.handler.JwtAccessDeniedHandler;
import com.qdang.global.http.HeaderTokenExtractor;
import com.qdang.global.jwt.JwtResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final JwtExceptionFilter jwtExceptionFilter;
	private final JwtAccessDeniedHandler customAccessDeniedHandler;
	private final HeaderTokenExtractor headerTokenExtractor;
	private final JwtResolver jwtResolver;

	private static final String[] SwaggerPatterns = {
			"/swagger-resources/**",
			"/swagger-ui.html",
			"/swagger-ui/**",
			"/v3/api-docs/**",
			"/api-docs/**",
			"/webjars/**",
	};

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		JwtAuthenticationFilter jwtAuthenticationFilter =
				new JwtAuthenticationFilter(headerTokenExtractor, jwtResolver);
		http
				.addFilterBefore(jwtAuthenticationFilter,
						UsernamePasswordAuthenticationFilter.class)
				.addFilterBefore(jwtExceptionFilter,
						JwtAuthenticationFilter.class)
				.exceptionHandling()
				.accessDeniedHandler(customAccessDeniedHandler);

		http.httpBasic().disable();
		http.csrf().disable();
		http.cors().disable();

		http.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.authorizeHttpRequests()
				.requestMatchers(new AntPathRequestMatcher("/manager/**"))
				.hasAnyAuthority("MANAGER", "ADMIN")
				.requestMatchers(new AntPathRequestMatcher("/admin/**"))
				.hasAuthority("ADMIN")
				.anyRequest()
				.authenticated();
		return http.build();
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return web -> {
			web.ignoring()
					.antMatchers(SwaggerPatterns)
					.antMatchers("/actuator/**")
					.requestMatchers(
			PathRequest.toStaticResources().atCommonLocations())
					.requestMatchers(
							new AntPathRequestMatcher("/v1/auth/login", HttpMethod.POST.name()),
							new AntPathRequestMatcher("/v1/auth/signup", HttpMethod.POST.name()),
							new AntPathRequestMatcher("/v1/matches/{matchId}", HttpMethod.GET.name()),
							new AntPathRequestMatcher("/v1/users/search", HttpMethod.GET.name()),
							new AntPathRequestMatcher("/v1/users/validation/username", HttpMethod.GET.name())
					);
		};
	}
}
