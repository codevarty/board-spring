package com.codevarty.board.domain.board.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardSaveRequestDto {
	
	private Long boardId;
	
	@Size(min = 1, max = 255)
	@NotBlank(message = "제목은 필수값입니다.")
	private String title;
	
	@NotBlank(message = "내용은 필수값입니다.")
	private String content;
	
	private Long userSeq;

}
