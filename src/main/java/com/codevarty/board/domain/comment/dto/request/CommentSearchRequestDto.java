package com.codevarty.board.domain.comment.dto.request;

import com.codevarty.board.domain.common.page.PageRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentSearchRequestDto extends PageRequest {
	private Long boardId;
}
