package com.codevarty.board.domain.comment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.codevarty.board.domain.comment.dto.request.CommentSaveRequestDto;
import com.codevarty.board.domain.comment.dto.request.CommentSearchRequestDto;
import com.codevarty.board.domain.comment.dto.request.CommentUpdateRequestDto;
import com.codevarty.board.domain.comment.dto.response.CommentResponseDto;

@Mapper
public interface CommentMapper {

	/**
	 * 게시글의 댓글 목록을 조회한다.
	 * 
	 * @param boardId 게시글 id
	 * @return 게시글 목록 dtoList
	 */
	List<CommentResponseDto> getCommentList(CommentSearchRequestDto requestDto);
	
	/**
	 * 게시글의 댓글 총 개수를 조회한다.
	 * 
	 * @param boardId 게시글 id
	 * @return 게시글 총 개수
	 */
	int getTotalCount(CommentSearchRequestDto requestDto);
	
	/**
	 * 게시글의 댓글을 저장한다.
	 * 
	 * @param requestDto 댓글 저장 내용
	 */
	void saveComment(CommentSaveRequestDto requestDto);
	
	/**
	 * 댓글을 수정한다.
	 * 
	 * @param commentId 댓글 id
	 * @param requestDto 수정 내용
	 */
	void updateComment(
			@Param("commentId") Long commentId, 
			@Param("requestDto")CommentUpdateRequestDto requestDto);
	
	/**
	 * 댓글을 삭제한다.
	 * 
	 * @param commentId 게시글 id
	 */
	void deleteComment(Long commentId);
}
