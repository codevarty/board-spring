package com.codevarty.board.domain.comment.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codevarty.board.domain.comment.dto.request.CommentSaveRequestDto;
import com.codevarty.board.domain.comment.dto.request.CommentSearchRequestDto;
import com.codevarty.board.domain.comment.dto.request.CommentUpdateRequestDto;
import com.codevarty.board.domain.comment.dto.response.CommentResponseDto;
import com.codevarty.board.domain.comment.mapper.CommentMapper;
import com.codevarty.board.domain.common.page.PageResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {
	private final CommentMapper commentMapper;
	
	public PageResponse<CommentResponseDto> getCommentList(CommentSearchRequestDto requestDto) {
		int totalCount = commentMapper.getTotalCount(requestDto);
		List<CommentResponseDto> commentList = commentMapper.getCommentList(requestDto);
		
		return new PageResponse<CommentResponseDto>(commentList, totalCount, requestDto.getPage(), requestDto.getPageSize());
	}
	
	public void saveComment(CommentSaveRequestDto requestDto) {
		commentMapper.saveComment(requestDto);
	}
	
	@Transactional
	public void updateComment(Long commentId, CommentUpdateRequestDto requestDto) {
		commentMapper.updateComment(commentId, requestDto);
	}
	
	public void deleteComment(Long commentId) {
		commentMapper.deleteComment(commentId);
	}
}
