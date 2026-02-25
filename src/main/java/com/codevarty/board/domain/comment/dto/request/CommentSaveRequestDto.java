package com.codevarty.board.domain.comment.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentSaveRequestDto {
	@NotBlank(message = "내용은 필수 값입니다.")
	private String content;
	
	@NotNull(message = "게시글 정보는 필수 값입니다.")
	private Long boardId;
	
	@NotNull(message = "사용자 종보는 필수 값입니다.")
	private Long userSeq;
}
