package com.codevarty.board.domain.board.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codevarty.board.domain.board.dto.request.BoardSaveRequestDto;
import com.codevarty.board.domain.board.dto.request.BoardSearchRequestDto;
import com.codevarty.board.domain.board.dto.request.BoardUpdateRequestDto;
import com.codevarty.board.domain.board.dto.response.BoardDetailResponseDto;
import com.codevarty.board.domain.board.dto.response.BoardResponseDto;
import com.codevarty.board.domain.board.mapper.BoardMapper;
import com.codevarty.board.domain.common.page.PageResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	private final BoardMapper boardMapper;
	
	public PageResponse<BoardResponseDto> getBoardList(BoardSearchRequestDto requestDto) {
		int totalCount = boardMapper.getBoardTotalCount(requestDto);
		List<BoardResponseDto> boardList =  boardMapper.getBoardList(requestDto);
		
		return new PageResponse<>(boardList, totalCount, requestDto.getPage(), requestDto.getPageSize());
	} 
	
	@Transactional
	public BoardDetailResponseDto getBoardDetail(Long boardId) {
		boardMapper.updateBoardViewCount(boardId);
		return boardMapper.getBoardDetail(boardId);
	}
	
	@Transactional
	public Long saveBoard(BoardSaveRequestDto requestDto) {
		boardMapper.saveBoard(requestDto);
		return requestDto.getBoardId();
	}
	
	@Transactional
	public Long updateBoard(Long boardId, BoardUpdateRequestDto reuqestDto) {
		BoardDetailResponseDto findBoard = boardMapper.getBoardDetail(boardId);
		
		if (findBoard == null || findBoard.getBoardId() == 0L) {
			throw new IllegalArgumentException("Not found Board");
		}
		
		boardMapper.updateBoard(boardId, reuqestDto);
		
		return findBoard.getBoardId();
	}
	
	public void deleteBoard(Long boardId) {
		boardMapper.deleteBoard(boardId);
	}
}
