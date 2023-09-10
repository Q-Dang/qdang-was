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

	private Claims getAccessTokenBody(final String token) {
		return Jwts.parserBuilder()
				.setSigningKey(accessKey)
				.build()
				.parseClaimsJws(token)
				.getBody();
	}

	public Authentication getAuthentication(String token) {
		UserDetails userDetails =
				principalDetailsService.loadUserByUsername(
						getUserIdFromJwtToken(token).toString());
		return new UsernamePasswordAuthenticationToken(
				userDetails,
				"",
				userDetails.getAuthorities());
	}

	public Long getUserIdFromJwtToken(String token) {
		try {
			Claims claims = getAccessTokenBody(token);
			return Long.parseLong(claims.get("userId").toString());
		} catch (ExpiredJwtException e) {
			throw new UnauthorizedException(ErrorType.EXPIRED_TOKEN_EXCEPTION);
		} catch (Exception e) {
			throw new UnauthorizedException();
		}
	}

	public boolean validateAccessToken(String token) {
		try {
			return !getAccessTokenBody(token)
					.getExpiration()
					.before(new Date());
		} catch (SecurityException | MalformedJwtException | SignatureException e) {
			throw new UnauthorizedException(ErrorType.INVALID_JWT_TOKEN_EXCEPTION);
		} catch (UnsupportedJwtException e) {
			throw new UnauthorizedException(ErrorType.UNSUPPORTED_JWT_TOKEN_EXCEPTION);
		} catch (IllegalArgumentException e) {
			throw new UnauthorizedException(ErrorType.INVALID_JWT_TOKEN_EXCEPTION);
		} catch (ExpiredJwtException e) {
			throw new UnauthorizedException(ErrorType.EXPIRED_TOKEN_EXCEPTION);
		}
	}

	public boolean validateRefreshToken(String refreshToken) {
		try {
			Jwts
					.parserBuilder()
					.setSigningKey(refreshKey)
					.build()
					.parse(refreshToken);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
