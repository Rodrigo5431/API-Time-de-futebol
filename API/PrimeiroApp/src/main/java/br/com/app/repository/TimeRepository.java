package br.com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.app.entity.Time;

@Repository	
public interface TimeRepository extends JpaRepository<Time, Integer> {

}