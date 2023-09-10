package com.qdang.global.jwt;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import org.springframework.stereotype.Service;

@Service
public class JwtProvider {

	private final Key accesskey;
	private final Key refreshKey;
	private final Integer accessExpired;
	private final Integer refreshExpired;

	public JwtProvider(JwtProperty jwtProperty) {
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
}
