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
import com.codevarty.board.domain.auth.dto.response.LoginResponseDto;
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
	public ResponseEntity<LoginResponseDto> login(@RequestBody @Valid LoginRequestDto requestDto) {
		LoginResponseDto response = authService.login(requestDto);
		HttpHeaders headers = new HttpHeaders();
		
		// header에 토큰을 담아서 처리
		// 나중에 리펙토링 필요 이유: AuthController에서 token에 대한 정보를 알고 있어야 함.
		// 결합도 문제가 있음.
		headers.set("Authorization", "Bearer " + response.getToken().getAccessToken());
		headers.set("Authorization-refresh", "Bearer " + response.getToken().getRefreshToken());
		
		return ResponseEntity.ok()
				.headers(headers)
				.body(response);
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
