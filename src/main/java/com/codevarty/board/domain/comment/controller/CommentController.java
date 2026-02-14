package com.codevarty.board.domain.comment.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codevarty.board.domain.comment.dto.request.CommentSaveRequestDto;
import com.codevarty.board.domain.comment.dto.request.CommentSearchRequestDto;
import com.codevarty.board.domain.comment.dto.request.CommentUpdateRequestDto;
import com.codevarty.board.domain.comment.dto.response.CommentResponseDto;
import com.codevarty.board.domain.comment.service.CommentService;
import com.codevarty.board.domain.common.page.PageResponse;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
	
	private final CommentService commentService;
	
	@GetMapping("/list")
	public PageResponse<CommentResponseDto> getCommentList(CommentSearchRequestDto requestDto) {
		return commentService.getCommentList(requestDto);
	}
	
	@PostMapping("/save")
	public void saveComment(@RequestBody CommentSaveRequestDto requestDto) {
		commentService.saveComment(requestDto);
	}
	
	@PutMapping("/update/{commentId}")
	public void updateComment(
			@PathVariable(name = "commentId") Long commentId,
			@RequestBody CommentUpdateRequestDto requestDto) {
		commentService.updateComment(commentId, requestDto);
	}
	
	@DeleteMapping("/delete/{commentId}")
	public void deleteComment(
			@PathVariable(name = "commentId") Long commentId ) {
		commentService.deleteComment(commentId);
	}
	
}
