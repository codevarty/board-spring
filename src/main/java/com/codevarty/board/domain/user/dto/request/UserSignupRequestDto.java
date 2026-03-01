package com.codevarty.board.domain.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserSignupRequestDto {
	
	@NotBlank(message = "사용자ID는 필수 값입니다.")
	private String userId; // 사용자 Id
	
	@NotBlank(message = "사용자명은 필수 값입니다.")
	private String username; // 사용자명
	
	@NotBlank(message = "비밀번호는 필수 값입니다.")
	private String password; // 비밀번호
	
	@NotBlank(message = "이메일은 필수 값입니다.")
	private String email; // 이메일
	
	private String userTel; // 사용자전화번호
}
