package com.codevarty.board.domain.comment.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentUpdateRequestDto {
	
	@NotBlank(message = "내용은 필수 값입니다.")
	private String content;
}
