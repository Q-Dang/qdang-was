package com.qdang.global.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qdang.global.exception.BusinessException;
import com.qdang.global.exception.ErrorType;
import com.qdang.global.response.FailResponse;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtExceptionFilter extends OncePerRequestFilter {

	private final ObjectMapper objectMapper;

	@Override
	protected void doFilterInternal(
			HttpServletRequest request,
			HttpServletResponse response,
			FilterChain filterChain) throws ServletException, IOException {
		try {
			filterChain.doFilter(request, response);
		} catch (BusinessException e) {
			responseError(
					response,
					e.getErrorType(),
					e.getMessage());
			log.error(e.getMessage());
		} catch (Exception e) {
			responseError(
					response,
					ErrorType.INTERNAL_SERVER_ERROR,
					ErrorType.INTERNAL_SERVER_ERROR.getMessage());
			log.error(e.getMessage());
		}
	}

	private void responseError(
			HttpServletResponse response,
			ErrorType errorType,
			String message)
			throws IOException {
		response.setStatus(errorType.getStatusCode());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setCharacterEncoding("UTF-8");
		response.getWriter()
				.write(objectMapper.writeValueAsString(
						new FailResponse(
								errorType.getStatusCode(),
								message)));
	}
}
