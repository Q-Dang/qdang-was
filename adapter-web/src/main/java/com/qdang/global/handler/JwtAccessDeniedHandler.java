package com.qdang.global.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qdang.global.exception.ErrorType;
import com.qdang.global.response.FailResponse;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

	private final ObjectMapper objectMapper;

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		log.error(accessDeniedException.getMessage());
		log.error("user id = {}", SecurityContextHolder.getContext().getAuthentication().getName());
		log.error("user role = {}", SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString());
		ErrorType errorType = ErrorType.FORBIDDEN_EXCEPTION;
		response.setStatus(errorType.getStatusCode());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setCharacterEncoding("UTF-8");
		response.getWriter()
				.write(objectMapper.writeValueAsString(
						new FailResponse(
								errorType.getStatusCode(),
								errorType.getMessage())));
	}
}
