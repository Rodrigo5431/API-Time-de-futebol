package br.com.app.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Treinador {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idTreinador;
	private String nome;
	private String Experiencia;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getExperiencia() {
		return Experiencia;
	}
	public void setExperiencia(String experiencia) {
		Experiencia = experiencia;
	}
	
	
	
}