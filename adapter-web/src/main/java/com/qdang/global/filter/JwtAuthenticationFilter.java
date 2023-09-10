package com.qdang.global.filter;

import com.qdang.global.exception.ErrorType;
import com.qdang.global.exception.NotFoundException;
import com.qdang.global.exception.UnauthorizedException;
import com.qdang.global.http.HeaderTokenExtractor;
import com.qdang.global.jwt.JwtResolver;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final HeaderTokenExtractor headerTokenExtractor;
	private final JwtResolver jwtResolver;

	@Override
	protected void doFilterInternal(
			HttpServletRequest request,
			HttpServletResponse response,
			FilterChain filterChain) throws ServletException, IOException {
		String jwtToken = headerTokenExtractor.extractAccessToken(request);
		if (StringUtils.hasText(jwtToken)) {
			if (jwtResolver.validateAccessToken(jwtToken)) {
				try {
					Authentication authentication = jwtResolver.getAuthentication(jwtToken);
					SecurityContextHolder.getContext().setAuthentication(authentication);
				} catch (NotFoundException e) {
					throw new UnauthorizedException(ErrorType.INVALID_JWT_TOKEN_EXCEPTION);
				}
			}
		} else {
			log.warn("JWT Token is null : [{}]", jwtToken);
			throw new UnauthorizedException(ErrorType.INVALID_JWT_TOKEN_EXCEPTION);
		}
		filterChain.doFilter(request, response);
	}
}
