package com.qdang.global.resolver;

import com.qdang.global.http.HeaderTokenExtractor;
import com.qdang.global.exception.BusinessException;
import com.qdang.global.exception.ErrorType;
import com.qdang.global.jwt.JwtResolver;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Slf4j
@RequiredArgsConstructor
@Component
public class UserIdResolver implements HandlerMethodArgumentResolver {

	private final JwtResolver jwtResolver;
	private final HeaderTokenExtractor headerTokenExtractor;

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
		String token = headerTokenExtractor.extractAccessToken(request);

		if (!jwtResolver.validateAccessToken(token)) {
			throw new BusinessException(
					ErrorType.INVALID_JWT_TOKEN_EXCEPTION,
					String.format(
							"USER_ID를 가져오지 못했습니다. (%s - %s)",
							parameter.getClass(),
							parameter.getMethod()));
		}
		Long userId = jwtResolver.getUserIdFromJwtToken(token);
		log.info("userId = {}", userId);
		try {
			return userId;
		} catch (NumberFormatException e) {
			throw new RuntimeException(
					String.format(
							"USER_ID를 가져오지 못했습니다. (%s - %s)",
							parameter.getClass(),
							parameter.getMethod()));
		}
	}
}