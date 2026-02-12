package com.codevarty.board.domain.board.dto.request;

import com.codevarty.board.domain.common.page.PageRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardSearchRequestDto extends PageRequest {
	private String startDate;
	private String endDate;
	private String content;
}
