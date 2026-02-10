package com.codevarty.board.domain.common.page;

import java.util.Collections;
import java.util.List;

import lombok.Getter;

@Getter
public class PageResponse<T> {
	private final List<T> content;
	private final int totalCount;
	private final int totalPages;
	private final int page;
	private final int pageSize;
	private final boolean isFirst;
	private final boolean isLast;
	
	public PageResponse(List<T> content, int totalCount, int page, int pageSize) {
		this.content = Collections.unmodifiableList(content); // 수정불가능 collection 처리
		this.totalCount = totalCount;
		this.page = page;
		this.pageSize = pageSize;
		this.totalPages = (totalCount == 0) ? 1 : (int) Math.ceil((double) totalCount / pageSize);
		
		this.isFirst = (page <= 1);
        this.isLast = (page >= totalPages);
	}
}
