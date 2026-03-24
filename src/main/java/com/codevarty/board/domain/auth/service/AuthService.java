package com.codevarty.board.domain.auth.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codevarty.board.domain.auth.dto.request.LoginRequestDto;
import com.codevarty.board.domain.auth.dto.response.LoginResponseDto;
import com.codevarty.board.domain.auth.dto.response.TokenResponseDto;
import com.codevarty.board.domain.user.dto.response.UserResponse;
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
	public LoginResponseDto login(LoginRequestDto requestDto) {
		
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
		String accessToken = jwtProvider.generateToken(findUser.getUserSeq(), "", false);
		String refreshToken = jwtProvider.generateToken(findUser.getUserSeq(), "", true);

		// 리프레쉬 토큰 수정
		userMapper.updateRefreshToken(findUser.getUserSeq(), refreshToken);
		
		// 토큰 response
		TokenResponseDto token = TokenResponseDto.builder()
									.accessToken(accessToken)
									.refreshToken(refreshToken)
									.build();
		
		// 사용자 정보
		UserResponse user = UserResponse.builder()
								.userSeq(findUser.getUserSeq())
								.userId(findUser.getUserId())
								.email(findUser.getEmail())
								.username(findUser.getUsername())
								.userTel(findUser.getUserTel())
								.build();
		
		return LoginResponseDto.builder()
							.token(token)
							.userInfo(user)
							.build();
	}
	
	public void logout(Long userSeq) {
		
		// refresh token 제거
		userMapper.updateRefreshToken(userSeq, null);
	}
}
