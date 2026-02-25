package com.codevarty.board.domain.comment.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseDto {
	private Long commentId;
	private String content;
	private Long userId;
	private String userName;
	private String createdAt;
	private String updatedAt;
}
