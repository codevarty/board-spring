package com.codevarty.board.domain.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
	@Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
	private String password; // 비밀번호
	
	@Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식이 올바르지 않습니다.")
	@NotBlank(message = "이메일은 필수 값입니다.")
	private String email; // 이메일
	
	private String userTel; // 사용자전화번호
}
