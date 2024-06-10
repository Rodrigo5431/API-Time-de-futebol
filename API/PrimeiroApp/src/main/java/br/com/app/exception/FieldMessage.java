package br.com.app.exception;

import lombok.Data;

@Data
public class FieldMessage {

	private String fildName;
	private String message;
	
	public FieldMessage(String fildName, String message) {
		super();
		this.fildName = fildName;
		this.message = message;
	}
	
	
}