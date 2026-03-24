package com.codevarty.board.global.filter.auth;

import java.io.IOException;
import java.util.Collections;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.codevarty.board.domain.user.mapper.UserMapper;
import com.codevarty.board.global.token.JwtProvider;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JwtProvider jwtProvider;
	private final UserMapper userMapper;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String acessToken = getToken(request, "Authorization");
		String refreshToken = getToken(request, "Authorization-refresh");
		
		if (acessToken != null) {
			if (jwtProvider.validToken(acessToken)) {
				this.setAuthentication(acessToken);
			}
			
			// accessToken 만료 시 refreshToken 확인 후 새로운 토큰 재발급
		} else if (refreshToken != null && jwtProvider.validToken(refreshToken)) {
			Long userSeq = jwtProvider.getUserSeqByToken(refreshToken);
			
			String newAccessToken = jwtProvider.generateToken(userSeq, "", true);
			String newRefreshToken = jwtProvider.generateToken(userSeq, "", false);
			
			// DB 업데이트
			userMapper.updateRefreshToken(userSeq, newRefreshToken);
			
			// 토큰 재발급
			response.setHeader("Authorization", "Bearer " + newAccessToken);
            response.setHeader("Authorization-refresh", newRefreshToken);
            response.setHeader("Access-Control-Expose-Headers", "Authorization, Authorization-refresh");
			
			// 현재 요청에 따른 인증 처리          
            this.setAuthentication(newAccessToken);
		}
		
		filterChain.doFilter(request, response);
	}
	
	private String getToken(HttpServletRequest request, String headerName) {
		String bearToken = request.getHeader(headerName);
		
		if (StringUtils.hasText(bearToken) && bearToken.startsWith("Bearer ")) {
			return bearToken.substring(7);
		}
		
		return null;
	}
	
	private void setAuthentication(String token) {
        Long userSeq = jwtProvider.getUserSeqByToken(token);
        UsernamePasswordAuthenticationToken authentication = 
            new UsernamePasswordAuthenticationToken(userSeq, null, Collections.emptyList());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
	
}
