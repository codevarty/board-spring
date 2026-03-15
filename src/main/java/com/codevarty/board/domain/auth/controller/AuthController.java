package com.codevarty.board.domain.auth.controller;

import java.net.http.HttpRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codevarty.board.domain.auth.dto.request.LoginRequestDto;
import com.codevarty.board.domain.auth.dto.response.TokenResponseDto;
import com.codevarty.board.domain.auth.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
	private final AuthService authService;
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody @Valid LoginRequestDto requestDto) {
		TokenResponseDto tokenDto = authService.login(requestDto);
		HttpHeaders headers = new HttpHeaders();
		
		// header에 토큰을 담아서 처리
		headers.set("Authorization", "Bearer " + tokenDto.getAccessToken());
		headers.set("Authorization-refresh", "Bearer " + tokenDto.getRefreshToken());
		
		return ResponseEntity.ok()
				.headers(headers)
				.body("success");
	}
	
	@GetMapping("/logout")
	public ResponseEntity<String> logout(HttpRequest request) {
		
		// 인증 처리 부분 이후 확인
		Long userSeq = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName());
		
		authService.logout(userSeq);
		
		SecurityContextHolder.clearContext();
		
		return ResponseEntity.ok("success");
	}

}
