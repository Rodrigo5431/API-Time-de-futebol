package br.com.app.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.app.entity.Jogador;
import br.com.app.exception.ObjectNotFoundException;
import br.com.app.repository.JogadorRepository;

@Service
public class JogadorService {
	
	@Autowired
	private JogadorRepository jogadorRepo;
	
	public Jogador salvar(Jogador jogador) {
		return jogadorRepo.save(jogador);
	}
	
	public List<Jogador> buscarTodosJogadors() {
		return jogadorRepo.findAll();
	}
	
	public Jogador buscarJogadorPorId(Integer id) {
		Optional<Jogador> jogador = jogadorRepo.findById(id);
		return jogador.orElseThrow(()-> new ObjectNotFoundException
				("Jogador não encontrado"));
	}
	
	public Boolean deletarJogador(Integer id) {
		Jogador jogador = jogadorRepo.findById(id).orElse(null);
		if (jogador != null) {
			jogadorRepo.deleteById(id);
			return true;
		}
		return false;
	}
	
	public Jogador updateJogador(Integer id, Jogador jogador) {
		Jogador jogadorBD = jogadorRepo.findById(id).orElse(null);
		if (jogadorBD != null) {
			jogadorBD.setNome(jogador.getNome());
			jogadorBD.setNumeroCamisa(jogador.getNumeroCamisa());
			return jogadorRepo.save(jogadorBD);
		}
		return null;
	}
	
	public Page<Jogador> buscarJogadorPorPaginacao(PageRequest page) {
		return jogadorRepo.findAll(page);
	}

}

