package br.com.app.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

public class TimeDTO {

	@Getter
	@Setter
	private Integer idTime;
	
	@Getter
	@Setter
	@NotNull
	private String nomeTime;
	
	@Getter
	@Setter
	@NotNull
	private String jogadores;

}