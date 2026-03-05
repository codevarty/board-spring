package com.codevarty.board.domain.auth.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codevarty.board.domain.auth.dto.request.LoginRequestDto;
import com.codevarty.board.domain.auth.dto.response.TokenResponseDto;
import com.codevarty.board.domain.user.entity.UserEntity;
import com.codevarty.board.domain.user.mapper.UserMapper;
import com.codevarty.board.global.token.JwtProvider;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	private final UserMapper userMapper;
	private final PasswordEncoder passwordEncoder;
	private final JwtProvider jwtProvider;
	
	@Transactional
	public TokenResponseDto login(LoginRequestDto requestDto) {
		
		UserEntity findUser = userMapper.getUserByUserId(requestDto.getUserId());
		
		// 사용자가 존재하는 지 확인
		if (findUser == null || findUser.getUserSeq() == 0L) {
			throw new IllegalArgumentException("Not found User");
		}
		
		// 비밀번호가 일치하는 지 확인
		if (!passwordEncoder.matches(requestDto.getPassword(), findUser.getPassword())) {
			throw new IllegalArgumentException("invalid password");
		}
		
		// 토큰 생성
		String accessToken = jwtProvider.generateToken(findUser.getUserId(), "", false);
		String refreshToken = jwtProvider.generateToken(findUser.getUserId(), "", true);
		
		// TODO: DB에 refresh token을 저장할 수 있도록 추가 필요.
		
		return new TokenResponseDto(accessToken, refreshToken);
	}
}
