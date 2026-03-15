package com.codevarty.board.global.filter.auth;

import java.io.IOException;
import java.util.Collections;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

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
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = getToken(request);
		
		if (token != null && jwtProvider.validToken(token)) {
			Long userSeq = jwtProvider.getUserSeqByToken(token);
			
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userSeq, null, Collections.emptyList());
			
			// Security Context에 인증 정보 저장
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		
		filterChain.doFilter(request, response);
	}
	
	private String getToken(HttpServletRequest request) {
		String bearToken = request.getHeader("Authorization");
		
		if (StringUtils.hasText(bearToken) && bearToken.startsWith("Bearer ")) {
			return bearToken.substring(7);
		}
		
		return null;
	}
	
}
