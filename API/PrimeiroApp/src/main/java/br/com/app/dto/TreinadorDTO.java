package br.com.app.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

public class TreinadorDTO {

	@Getter
	@Setter
	private Integer idTreinador;
	
	@Getter
	@Setter
	@NotNull
	private String nomeC;
	
	@Getter
	@Setter
	@NotNull
	private String experiencia;

}