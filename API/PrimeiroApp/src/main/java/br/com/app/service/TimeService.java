package br.com.app.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.app.entity.Time;
import br.com.app.exception.ObjectNotFoundException;
import br.com.app.repository.TimeRepository;

@Service
public class TimeService {
	
	@Autowired
	private TimeRepository timeRepo;
	
	public Time salvar(Time time) {
		return timeRepo.save(time);
	}
	
	public List<Time> buscarTodosTimes(){
		return timeRepo.findAll();
	}
	public Time buscarTimePorId(Integer id) { 
		Optional<Time> time = timeRepo.findById(id);
		return time.orElseThrow(()-> new ObjectNotFoundException
				("Time não Encontrado"));
	}
	public Boolean deletarTime(Integer id) {
		Time time = timeRepo.findById(id).orElse(null);
		if (time != null) { 
			timeRepo.deleteById(id);
		return true;	
		}
		return false;
	}
	
	public Time updateTime(Integer id, Time time) {
		Time timeBD = timeRepo.findById(id).orElse(null);
		if(timeBD !=null) {
		timeBD.setNomeTime(time.getNomeTime());
		timeBD.setJogadores(time.getJogadores());
		return timeRepo.save(timeBD);
		}
		return null;
	}
	
	public Page<Time> buscarTimePorPaginacao(PageRequest page){
		return timeRepo.findAll(page);
	}
}