package br.com.app.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.app.entity.Treinador;
import br.com.app.exception.ObjectNotFoundException;
import br.com.app.repository.TreinadorRepository;

@Service
public class TreinadorService {
	
	@Autowired
	private TreinadorRepository treinadorRepo;
	
	public Treinador salvar(Treinador treinador) {
		return treinadorRepo.save(treinador);
	}
	
	public List<Treinador> buscarTodosTreinadores(){
		return treinadorRepo.findAll();
	}
	public Treinador buscarTreinadorPorId(Integer id) { 
		Optional<Treinador> treinador = treinadorRepo.findById(id);
		return treinador.orElseThrow(()-> new ObjectNotFoundException
				("Treinador não Encontrado"));
	}
	public Boolean deletarTreinador(Integer id) {
		Treinador treinador = treinadorRepo.findById(id).orElse(null);
		if (treinador != null) { 
			treinadorRepo.deleteById(id);
		return true;	
		}
		return false;
	}
	
	public Treinador updateTreinador(Integer id, Treinador treinador) {
		Treinador treinadorBD = treinadorRepo.findById(id).orElse(null);
		if(treinadorBD !=null) {
		treinadorBD.setNome(treinador.getNome());
		treinadorBD.setExperiencia(treinador.getExperiencia());
		return treinadorRepo.save(treinadorBD);
		}
		return null;
	}
	
	public Page<Treinador> buscarTreinadorPorPaginacao(PageRequest page){
		return treinadorRepo.findAll(page);
	}
} 