package com.codevarty.board.domain.common.page;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class PageRequest {
	private static final int DEFAULT_PAGE_SIZE = 20;
	private static final int DEFAULT_PAGE = 1;

	private int page;	// 현재 페이지
	private int pageSize;	// 페이지 크기
	
	public PageRequest() {
		this(DEFAULT_PAGE, DEFAULT_PAGE_SIZE);
	}
	
	public void setPage(int page) {
		this.page = (page <= 0) ? DEFAULT_PAGE : page;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = (pageSize <= 0) ? 20: DEFAULT_PAGE_SIZE;
	}
	
	public int getOffset() {
		return (this.page - 1) * this.pageSize;
	}
}
