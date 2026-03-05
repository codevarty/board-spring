package com.codevarty.board.domain.user.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.codevarty.board.domain.user.entity.UserEntity;

@Mapper
public interface UserMapper {
	
	/**
	 * 사용자 정보를 등록한다.
	 * 
	 * @param requestDto 사용자 정보
	 */
	void saveUser(UserEntity requestDto);
	
	/**
	 * 사용자 id를 통해 사용자 정보를 조회한다.
	 * 
	 * @param userId 사용자 id
	 * @return 사용자 정보 dto
	 */
	UserEntity getUserByUserId(String userId);
	
	/**
	 * 사용자 email을 통해 사용자 정보를 조회한다.
	 * 
	 * @param email 사용자 email
	 * @return 사용자 정보 dto
	 */
	UserEntity getUserByEmail(String email);
}
