package com.codevarty.board.global.token;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtProvider {

	@Value("${jwt.secret.key}")
	private String secretKey;					// secretKey
	
	@Value("${jwt.access-token-expiration}")
	private Long accessTokenExpiration;			// accessToken 만료시간
	
	@Value("${jwt.refresh-token-expiration}")
	private Long refreshTokenExpiration;		// refreshToken 만료시간
	
	private Key key;
	
	@PostConstruct
	protected void init() {
		byte[] keyBytes = Base64.getEncoder().encode(secretKey.getBytes());
        this.key = Keys.hmacShaKeyFor(keyBytes);
	}
	
	/**
	 * 토큰을 생성한다.
	 * 
	 * @param userId 사용자 Id
	 * @param role 사용자 역할
	 * @param isAccessToken accessToken 여부
	 * @return 생성된 토큰
	 */
	public String generateToken(String userId, String role, boolean isAccessToken) {
		long expireTime = isAccessToken ? accessTokenExpiration : refreshTokenExpiration;
		Date now = new Date();
        Date validity = new Date(now.getTime() + expireTime);
        
        Claims claims = Jwts.claims().setSubject(userId);
        claims.put("role", role);
		
		return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
	}
	
	/**
	 * 토큰에서 사용자 id 추출
	 * 
	 * @param token 토큰
	 * @return 사용자 id
	 */
	public String getUserIdByToken(String token) {
		return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
	}
	
	/**
	 * 토큰 검증
	 * 
	 * @param token 토큰
	 * @return 적합한 토큰 true / 아닐 경우 false
	 */
	public boolean validToken(String token) {
		try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            log.error("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            log.error("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            log.error("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            log.error("JWT 토큰이 잘못되었습니다.");
        }
        return false;
	}
}
