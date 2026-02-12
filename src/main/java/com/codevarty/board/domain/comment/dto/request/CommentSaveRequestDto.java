package com.codevarty.board.domain.comment.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentSaveRequestDto {
	private String content;
	private Long boardId;
	private Long userSeq;
}
