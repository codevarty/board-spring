package com.codevarty.board.domain.board.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardResponseDto {
	private Long boardId;
	private String title;
	private Long userSeq;
	private String userName;
	private Long viewCount;
	private String createdAt;
	private String updatedAt;
}
