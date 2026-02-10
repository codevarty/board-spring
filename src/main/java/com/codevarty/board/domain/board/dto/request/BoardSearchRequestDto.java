package com.codevarty.board.domain.board.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardSearchRequestDto {
	private String startDate;
	private String endDate;
	private String content;
	// 페이징 처리시 필요
	private int page;
	private int pageSize;

}
