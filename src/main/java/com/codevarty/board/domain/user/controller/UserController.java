package com.codevarty.board.domain.user.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codevarty.board.domain.user.dto.request.UserSignupRequestDto;
import com.codevarty.board.domain.user.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;
	
	@PostMapping("/sign-up")
	public void signup(@RequestBody @Valid UserSignupRequestDto reuqestDto) {
		userService.signup(reuqestDto);
	}
}
