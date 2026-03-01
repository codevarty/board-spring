package com.codevarty.board.domain.user.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
	private Long userSeq;
	private String userId;
	private String username;
	private String password;
	private String email;
	private String userTel;
	private LocalDateTime createdAt;
}
