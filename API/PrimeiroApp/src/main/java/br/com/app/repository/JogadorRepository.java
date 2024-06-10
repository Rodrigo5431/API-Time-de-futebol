package br.com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.app.entity.Jogador;

@Repository	
public interface JogadorRepository extends JpaRepository<Jogador, Integer> {

}