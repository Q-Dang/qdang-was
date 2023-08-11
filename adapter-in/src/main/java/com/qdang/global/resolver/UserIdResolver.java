package com.qdang.global.resolver;

import com.qdang.global.exception.BusinessException;
import com.qdang.global.exception.ErrorType;
import com.qdang.global.jwt.JwtResolver;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Slf4j
@RequiredArgsConstructor
@Component
public class UserIdResolver implements HandlerMethodArgumentResolver {

	private final JwtResolver jwtResolver;
	private static final String HEADER_PREFIX = "Bearer ";

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasParameterAnnotation(UserId.class)
				&& Long.class.equals(parameter.getParameterType());
	}

	@Override
	public Object resolveArgument(
			@NotNull MethodParameter parameter,
			ModelAndViewContainer modelAndViewContainer,
			@NotNull NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) {

		final HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
		final String bearerHeader = request.getHeader("Authorization");
		log.info("bearerHeader = {}", bearerHeader);

		if (!StringUtils.hasText(bearerHeader) || !bearerHeader.startsWith(HEADER_PREFIX)) {
			throw new BusinessException(ErrorType.INVALID_JWT_TOKEN_EXCEPTION);
		}
		String token = bearerHeader.substring(HEADER_PREFIX.length());
		if (!jwtResolver.verifyToken(token)) {
			throw new BusinessException(
					ErrorType.INVALID_JWT_TOKEN_EXCEPTION,
					String.format("USER_ID를 가져오지 못했습니다. (%s - %s)", parameter.getClass(),
							parameter.getMethod()));
		}
		final String tokenContents = jwtResolver.getJwtContents(token);
		log.info("tokenContents = {}", tokenContents);
		try {
			return Long.parseLong(tokenContents);
		} catch (NumberFormatException e) {
			throw new RuntimeException(
					String.format("USER_ID를 가져오지 못했습니다. (%s - %s)", parameter.getClass(),
							parameter.getMethod()));
		}
	}
}