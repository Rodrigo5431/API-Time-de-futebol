package br.com.app.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

public class JogadorDTO {

	@Getter
	@Setter
	private Integer id;
	
	@Getter
	@Setter
	@NotNull
	private String Nome;
	
	@Getter
	@Setter
	@NotNull
	private String NumeroCamisa;

}