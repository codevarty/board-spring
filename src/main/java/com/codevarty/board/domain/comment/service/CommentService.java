package com.codevarty.board.domain.comment.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.codevarty.board.domain.comment.dto.request.CommentSaveRequestDto;
import com.codevarty.board.domain.comment.dto.request.CommentUpdateRequestDto;
import com.codevarty.board.domain.comment.dto.response.CommentResponseDto;
import com.codevarty.board.domain.comment.mapper.CommentMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {
	private final CommentMapper commentMapper;
	
	public List<CommentResponseDto> getCommentList(Long boardId) {
		return commentMapper.getCommentList(boardId);
	}
	
	public void saveComment(CommentSaveRequestDto requestDto) {
		commentMapper.saveComment(requestDto);
	}
	
	public void updateComment(Long commentId, CommentUpdateRequestDto requestDto) {
		commentMapper.updateComment(commentId, requestDto);
	}
	
	public void deleteComment(Long commentId) {
		commentMapper.deleteComment(commentId);
	}
}
