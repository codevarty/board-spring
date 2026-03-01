package com.codevarty.board.domain.user.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codevarty.board.domain.user.dto.request.UserSignupRequestDto;
import com.codevarty.board.domain.user.dto.response.UserResponse;
import com.codevarty.board.domain.user.entity.UserEntity;
import com.codevarty.board.domain.user.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserMapper userMapper;
	private final PasswordEncoder passwordEncoder;
	
	@Transactional
	public void signup(UserSignupRequestDto requestDto) {
		
		// 사용자 id 중복 검사
		if (userMapper.getUserByUserId(requestDto.getUserId()) != null) {
			throw new IllegalArgumentException("duplicated userId");
		}
		
		// 사용자 email 중복 검사
		if (userMapper.getUserByEmail(requestDto.getEmail()) != null) {
			throw new IllegalArgumentException("duplicated email");
		}
		
		// 사용자 entity 정의
		UserEntity user = UserEntity.builder()
			.userId(requestDto.getUserId())
			.password(passwordEncoder.encode(requestDto.getPassword()))
			.username(requestDto.getUsername())
			.email(requestDto.getEmail())
			.userTel(requestDto.getUserTel())
			.build();
		
		// 사용자 등록
		userMapper.saveUser(user);
	}
	
	public UserResponse getUserByuserId(String UserId) {
		return userMapper.getUserByUserId(UserId);
	}

}
