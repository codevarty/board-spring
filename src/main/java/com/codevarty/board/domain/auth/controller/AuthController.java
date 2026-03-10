package com.codevarty.board.domain.auth.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
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
		
		// 추가로 memory에 token정보를 가지고 있도록 처리를 해야 함.
		
		// header에 토큰을 담아서 처리
		headers.set("Authorization", "Bearer " + tokenDto.getAccessToken());
		headers.set("Authorization-refresh", "Bearer " + tokenDto.getRefreshToken());
		
		return ResponseEntity.ok()
				.headers(headers)
				.body("success");
	}

}
