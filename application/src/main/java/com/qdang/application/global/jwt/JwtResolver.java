package com.qdang.application.global.jwt;

import com.qdang.application.global.exception.ErrorType;
import com.qdang.application.global.exception.UnauthorizedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import org.springframework.stereotype.Component;

@Component
public class JwtResolver {

	private final Key accesskey;
	private final Key refreshKey;

	public JwtResolver(JwtProperty jwtProperty) {
		byte[] accessKeyBytes = jwtProperty.getAccessKey().getBytes(StandardCharsets.UTF_8);
		byte[] refreshKeyBytes = jwtProperty.getRefreshKey().getBytes(StandardCharsets.UTF_8);
		accesskey = Keys.hmacShaKeyFor(accessKeyBytes);
		refreshKey = Keys.hmacShaKeyFor(refreshKeyBytes);
	}

	// JWT 토큰 검증
	public boolean verifyToken(String token) {
		try {
			final Claims claims = getBody(token);
			return true;
		} catch (RuntimeException e) {
			if (e instanceof ExpiredJwtException) {
				throw new UnauthorizedException(ErrorType.TOKEN_TIME_EXPIRED_EXCEPTION);
			}
			return false;
		}
	}

	private Claims getBody(final String token) {
		return Jwts.parserBuilder()
			.setSigningKey(accesskey)
			.build()
			.parseClaimsJws(token)
			.getBody();
	}

	public String getJwtContents(String token) {
		final Claims claims = getBody(token);
		return (String) claims.get("userId");
	}
}
