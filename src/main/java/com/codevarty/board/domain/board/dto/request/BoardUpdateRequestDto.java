package com.codevarty.board.domain.board.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BoardUpdateRequestDto {
	
	@Max( value = 255)
	@NotBlank(message = "제목은 필수 값입니다.")
	private String title;
	
	@NotBlank(message = "내용은 필수 값입니다.")
	private String content;
}
