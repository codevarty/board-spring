package com.codevarty.board.domain.auth.dto.response;

import com.codevarty.board.domain.user.dto.response.UserResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDto {
	private TokenResponseDto token;
	private UserResponse userInfo;
}
