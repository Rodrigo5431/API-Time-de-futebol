package br.com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.app.entity.Treinador;

@Repository	
public interface TreinadorRepository extends JpaRepository<Treinador, Integer> {

}