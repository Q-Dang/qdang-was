package qdang.group.was.global.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import qdang.group.was.global.exception.ErrorType;
import qdang.group.was.global.exception.UnauthorizedException;

@Service
public class JwtService {

	private final Key accesskey;
	private final Key refreshKey;
	private final Integer accessExpired;
	private final Integer refreshExpired;

	public JwtService(JwtProperty jwtProperty) {
		byte[] accessKeyBytes = jwtProperty.getAccessKey().getBytes(StandardCharsets.UTF_8);
		byte[] refreshKeyBytes = jwtProperty.getRefreshKey().getBytes(StandardCharsets.UTF_8);
		accesskey = Keys.hmacShaKeyFor(accessKeyBytes);
		refreshKey = Keys.hmacShaKeyFor(refreshKeyBytes);
		accessExpired = jwtProperty.getAccessExpiredMin();
		refreshExpired = jwtProperty.getRefreshExpiredDay();
	}

	public String createAccessToken(TokenInfo tokenInfo) {
		return Jwts.builder()
			.setSubject("access_token")
			.setHeaderParam(Header.TYPE , Header.JWT_TYPE)
			.setClaims(tokenInfo.getPayload())
			.setExpiration(Date.from(Instant.now().plus(accessExpired, ChronoUnit.MINUTES)))
			.signWith(accesskey)
			.compact();
	}

	public String createRefreshToken() {
		return Jwts.builder()
			.setSubject("refresh_token")
			.setHeaderParam(Header.TYPE , Header.JWT_TYPE)
			.setExpiration(Date.from(Instant.now().plus(refreshExpired, ChronoUnit.DAYS)))
			.signWith(refreshKey)
			.compact();
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

	// JWT 토큰 내용 확인
	public String getJwtContents(String token) {
		final Claims claims = getBody(token);
		return (String) claims.get("userId");
	}
}