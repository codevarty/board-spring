package com.codevarty.board.domain.comment.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codevarty.board.domain.comment.dto.response.CommentResponseDto;
import com.codevarty.board.domain.comment.service.CommentService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
	
	private final CommentService commentService;
	
	@GetMapping("/list/{boardId}")
	public List<CommentResponseDto> getCommentList(
			@PathVariable(name = "boardId") Long boardId) {
		return commentService.getCommentList(boardId);
	}
	
}
