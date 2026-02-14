package com.codevarty.board.domain.board.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codevarty.board.domain.board.dto.request.BoardSaveRequestDto;
import com.codevarty.board.domain.board.dto.request.BoardSearchRequestDto;
import com.codevarty.board.domain.board.dto.request.BoardUpdateRequestDto;
import com.codevarty.board.domain.board.dto.response.BoardDetailResponseDto;
import com.codevarty.board.domain.board.dto.response.BoardResponseDto;
import com.codevarty.board.domain.board.service.BoardService;
import com.codevarty.board.domain.common.page.PageResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
	private final BoardService boardService;
	
	@GetMapping("/list")
	public PageResponse<BoardResponseDto> getBoardList(BoardSearchRequestDto requestDto) {
		return boardService.getBoardList(requestDto);
	}
	
	@GetMapping("/{boardId}")
	public BoardDetailResponseDto getBoardDetail(@PathVariable(name = "boardId") Long boardId) {
		return boardService.getBoardDetail(boardId);
	}
	
	@PostMapping("/save")
	public Long saveBoard(@RequestBody BoardSaveRequestDto requestDto) {
		return boardService.saveBoard(requestDto);
	}
	
	@PutMapping("/update/{boardId}")
	public Long updateBoard(
			@PathVariable(name = "boardId") Long boardId,
			@RequestBody BoardUpdateRequestDto requestDto) {
		return boardService.updateBoard(boardId, requestDto);
	}
	
	@DeleteMapping("/delete/{boardId}")
	public void deleteBoard(@PathVariable(name = "boardId") Long boardId) {
		boardService.deleteBoard(boardId);
	}
}
