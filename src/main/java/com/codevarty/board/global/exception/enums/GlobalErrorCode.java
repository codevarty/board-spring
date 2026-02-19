package com.codevarty.board.global.exception.enums;

import lombok.Getter;

@Getter
public enum GlobalErrorCode {
	NOT_FOUND(404, "GLOBAL", "Not Found Exception");
	
	private int value;
	private String type;
	private String message;
	
	private GlobalErrorCode(int value, String type, String message) {
		this.value = value;
		this.type = type;
		this.message = message;
	} 

}
