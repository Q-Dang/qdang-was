package com.qdang.global.jwt;

import com.qdang.global.exception.ErrorType;
import com.qdang.global.exception.UnauthorizedException;
import com.qdang.global.security.PrincipalDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtResolver {

	private final PrincipalDetailsService principalDetailsService;
	private final JwtProperty jwtProperty;
	private Key accessKey;
	private Key refreshKey;

	@PostConstruct
	public void init() {
		byte[] accessKeyBytes = jwtProperty.getAccessKey().getBytes(StandardCharsets.UTF_8);
		byte[] refreshKeyBytes = jwtProperty.getRefreshKey().getBytes(StandardCharsets.UTF_8);
		accessKey = Keys.hmacShaKeyFor(accessKeyBytes);
		refreshKey = Keys.hmacShaKeyFor(refreshKeyBytes);
	}

	private Claims getAccessTokenBody(String accessToken) {
		return Jwts.parserBuilder()
				.setSigningKey(accessKey)
				.build()
				.parseClaimsJws(accessToken)
				.getBody();
	}

	private Claims getRefreshTokenBody(String refreshToken) {
		return Jwts.parserBuilder()
				.setSigningKey(refreshKey)
				.build()
				.parseClaimsJws(refreshToken)
				.getBody();
	}

	public Authentication getAuthenticationFromAccessToken(String accessToken) {
		UserDetails userDetails =
				principalDetailsService.loadUserByUsername(
						getUserIdFromAccessToken(accessToken).toString());
		return new UsernamePasswordAuthenticationToken(
				userDetails,
				"",
				userDetails.getAuthorities());
	}

	public Long getUserIdFromAccessToken(String accessToken) {
		try {
			Claims claims = getAccessTokenBody(accessToken);
			return Long.parseLong(claims.get("userId").toString());
		} catch (ExpiredJwtException e) {
			throw new UnauthorizedException(ErrorType.EXPIRED_ACCESS_TOKEN_EXCEPTION);
		} catch (Exception e) {
			throw new UnauthorizedException();
		}
	}

	public Long getUserIdFromRefreshToken(String refreshToken) {
		try {
			Claims claims = getRefreshTokenBody(refreshToken);
			return Long.parseLong(claims.get("userId").toString());
		} catch (ExpiredJwtException e) {
			throw new UnauthorizedException(ErrorType.EXPIRED_REFRESH_TOKEN_EXCEPTION);
		} catch (Exception e) {
			throw new UnauthorizedException();
		}
	}

	public boolean validateAccessToken(String accessToken) {
		try {
			return !getAccessTokenBody(accessToken)
					.getExpiration()
					.before(new Date());
		} catch (SecurityException | MalformedJwtException | SignatureException e) {
			throw new UnauthorizedException(ErrorType.INVALID_ACCESS_TOKEN_EXCEPTION);
		} catch (UnsupportedJwtException e) {
			throw new UnauthorizedException(ErrorType.UNSUPPORTED_JWT_TOKEN_EXCEPTION);
		} catch (IllegalArgumentException e) {
			throw new UnauthorizedException(ErrorType.INVALID_ACCESS_TOKEN_EXCEPTION);
		} catch (ExpiredJwtException e) {
			throw new UnauthorizedException(ErrorType.EXPIRED_ACCESS_TOKEN_EXCEPTION);
		}
	}

	public boolean validateRefreshToken(String refreshToken) {
		try {
			return !getRefreshTokenBody(refreshToken)
					.getExpiration()
					.before(new Date());
		} catch (SecurityException | MalformedJwtException | SignatureException e) {
			throw new UnauthorizedException(ErrorType.INVALID_REFRESH_TOKEN_EXCEPTION);
		} catch (UnsupportedJwtException e) {
			throw new UnauthorizedException(ErrorType.UNSUPPORTED_JWT_TOKEN_EXCEPTION);
		} catch (IllegalArgumentException e) {
			throw new UnauthorizedException(ErrorType.INVALID_REFRESH_TOKEN_EXCEPTION);
		} catch (ExpiredJwtException e) {
			throw new UnauthorizedException(ErrorType.EXPIRED_REFRESH_TOKEN_EXCEPTION);
		}
	}
}
