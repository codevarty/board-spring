package com.codevarty.board.domain.board.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BoardUpdateRequestDto {
	private String title;
	private String content;
}
