package com.qdang.global.config;

import com.qdang.global.filter.JwtAuthenticationFilter;
import com.qdang.global.filter.JwtExceptionFilter;
import com.qdang.global.handler.JwtAccessDeniedHandler;
import com.qdang.global.http.HeaderTokenExtractor;
import com.qdang.global.jwt.JwtResolver;
import java.util.Arrays;
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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

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
				.accessDeniedHandler(customAccessDeniedHandler)
				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.httpBasic().disable()
				.csrf().disable()
				.cors().configurationSource(corsConfigurationSource());

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
					.requestMatchers(PathRequest.toStaticResources().atCommonLocations())
					.requestMatchers(
							new AntPathRequestMatcher(
									"/v1/auth/login",
									HttpMethod.POST.name()),
							new AntPathRequestMatcher(
									"/v1/auth/signup",
									HttpMethod.POST.name()),
							new AntPathRequestMatcher(
									"/v1/matches/{matchId}",
									HttpMethod.GET.name()),
							new AntPathRequestMatcher(
									"/v1/users/search",
									HttpMethod.GET.name()),
							new AntPathRequestMatcher(
									"/v1/users/validation/username",
									HttpMethod.GET.name())
					);
		};
	}

	@Bean
	protected CorsConfigurationSource corsConfigurationSource() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", getDefaultCorsConfiguration());
		return source;
	}

	private CorsConfiguration getDefaultCorsConfiguration() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("https://api.q-dang.com"));
		configuration.setAllowedMethods(
				Arrays.asList(
						HttpMethod.GET.name(),
						HttpMethod.POST.name(),
						HttpMethod.PUT.name(),
						HttpMethod.PATCH.name(),
						HttpMethod.DELETE.name()));
		configuration.setAllowedHeaders(Arrays.asList("*"));
		configuration.setExposedHeaders(Arrays.asList("Refresh-Token"));
		configuration.setAllowCredentials(true);
		configuration.setMaxAge(3600L);
		return configuration;
	}
}
