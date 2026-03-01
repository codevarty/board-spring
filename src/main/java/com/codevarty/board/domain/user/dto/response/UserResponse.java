package com.codevarty.board.domain.user.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
	private Long userSeq;
	private String userId;
	private String username;
	private String email;
	private String userTel;
	private LocalDateTime createdAt;
}
