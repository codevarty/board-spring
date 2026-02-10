package com.codevarty.board.domain.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.codevarty.board.domain.board.dto.request.BoardSaveRequestDto;
import com.codevarty.board.domain.board.dto.request.BoardSearchRequestDto;
import com.codevarty.board.domain.board.dto.request.BoardUpdateRequestDto;
import com.codevarty.board.domain.board.dto.request.BoardUpdateRequestDto;
import com.codevarty.board.domain.board.dto.response.BoardDetailResponseDto;
import com.codevarty.board.domain.board.dto.response.BoardResponseDto;

@Mapper
public interface BoardMapper {
	
	/**
	 * 게시글 목록울 조회한다.
	 * 
	 * @param requestDto 게시글 검색 조건 dto
	 * @return 게시글응답dtoList
	 */
	List<BoardResponseDto> getBoardList(BoardSearchRequestDto requestDto);

	/**
	 * 게시글 전체 개수를 조회한다.
	 * 
	 * @param requestDto 게시글 검색 조건 dto
	 * @return 게시글 전체 개수
	 */
	int getBoardTotalCount(BoardSearchRequestDto requestDto);

	/**
	 * 게시글 상세정보를 조회한다.
	 * 
	 * @param boardId 게시글 id
	 * @return 게시글 상세정보dto
	 */
	BoardDetailResponseDto getBoardDetail(Long boardId);

	/**
	 * 게시글을 등록한다.
	 * 
	 * @param requestDto 게시글 저장 정보
	 * @return 저장한 게시글 id
	 */
	Long saveBoard(BoardSaveRequestDto requestDto);

	/**
	 * 게시글 내용을 수정한다.
	 * 
	 * @param boardId 게시글 id
	 * @param requestDto 게시글 수정 내용dto
	 */
	void updateBoard(Long boardId, BoardUpdateRequestDto requestDto);

	/**
	 * 게시글의 조회수를 수정한다.
	 * 
	 * @param boardId 게시글 id
	 */
	void updateBoardViewCount(Long boardId);

	/**
	 * 게시글을 삭제한다.
	 * 
	 * @param boardId 게시글 id
	 */
	void deleteBoard(Long boardId);
}
