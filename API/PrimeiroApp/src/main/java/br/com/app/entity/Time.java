package br.com.app.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Time {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idTime;
	private String nomeTime;
	private String Jogadores;
	public String getNomeTime() {
		return nomeTime;
	}
	public void setNomeTime(String nomeTime) {
		this.nomeTime = nomeTime;
	}
	public String getJogadores() {
		return Jogadores;
	}
	public void setJogadores(String jogadores) {
		Jogadores = jogadores;
	}
	
}