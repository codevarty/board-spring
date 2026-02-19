package com.codevarty.board.domain.board.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
	
	@Size(max = 255, message = "제목은 255자 이하만 입력이 가능합니다.")
	@NotBlank(message = "제목은 필수값입니다.")
	private String title;
	
	@NotBlank(message = "내용은 필수값입니다.")
	private String content;
	
	@NotNull(message = "사용자 정보는 필수값입니다.")
	private Long userSeq;

}
