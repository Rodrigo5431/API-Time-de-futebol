package br.com.app.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.commons.ExampleValuesJogador;
import br.com.app.dto.JogadorDTO;
import br.com.app.entity.Jogador;
import br.com.app.service.JogadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;

import jakarta.validation.Valid;

@RestController
@RequestMapping("estudantes")
public class JogadorResource {
	
	@Autowired
	private JogadorService estudanteService;
	@Autowired
	private ModelMapper mapper;
	
	@GetMapping
	@Operation(description = "Retorna todos os estudantes")
	public ResponseEntity<List<JogadorDTO>> buscarTodosJogadors() {
		List<Jogador> listaJogadors = estudanteService.buscarTodosJogadors();
		List<JogadorDTO> listaJogadorDTO = listaJogadors.stream().map(
				estudante -> mapper.map(estudante, JogadorDTO.class)).collect(
				Collectors.toList());
		return ResponseEntity.ok().body(listaJogadorDTO);
	}
	
	@GetMapping("/{id}")
	@Operation(description = "Retorna o registroi do estudante pelo id")
	public ResponseEntity<JogadorDTO> buscarJogadorPeloId(@PathVariable("id")
	@Schema(example = ExampleValuesJogador.ID_JOGADOR) Integer id) {
		Jogador estudante = estudanteService.buscarJogadorPorId(id);
		JogadorDTO estudanteDTO = mapper.map(estudante, JogadorDTO.class);
		return ResponseEntity.ok().body(estudanteDTO);
	}
	
	@PostMapping
	@Operation(description = "Cadastra um estudante")
	public ResponseEntity<JogadorDTO> cadastrarJogador(@Valid 
			@io.swagger.v3.oas.annotations.parameters.RequestBody
			(content = @Content (examples = {@ExampleObject(name = 
			"Exemplo de Jogador", value = ExampleValuesJogador.exemploJogador
			)}))
			@RequestBody JogadorDTO estudanteDTO) {
		Jogador estudante = mapper.map(estudanteDTO, Jogador.class);
		estudante = estudanteService.salvar(estudante);
		JogadorDTO novoJogador = mapper.map(estudante, JogadorDTO.class);
		return ResponseEntity.ok().body(novoJogador);
	}
	
	@PutMapping("/{id}")
	@Operation(description = "Altera o registro do estudante pelo id")
	public ResponseEntity<JogadorDTO> atualizarJogador(@PathVariable("id") 
	Integer id, @Schema(example = ExampleValuesJogador.ID_JOGADOR)
	@io.swagger.v3.oas.annotations.parameters.RequestBody
	(content = @Content (examples = {@ExampleObject(name = 
	"Exemplo de Jogador", value = ExampleValuesJogador.exemploJogador
	)}))
	@RequestBody JogadorDTO estudanteDTO) {
		Jogador estudante = mapper.map(estudanteDTO, Jogador.class);
		estudante = estudanteService.updateJogador(id, estudante);
		JogadorDTO novoJogador = mapper.map(estudante, JogadorDTO.class);
		return ResponseEntity.ok().body(novoJogador);
	}
	
	@DeleteMapping("/{id}")
	@Operation(description = "Remove um estudante pelo id")
	public ResponseEntity<Boolean> excluirJogador(@Schema(example = 
			ExampleValuesJogador.ID_JOGADOR)@PathVariable Integer id) {
		Boolean flag = estudanteService.deletarJogador(id);
		return ResponseEntity.ok().body(flag);
	}
	
	
	@GetMapping("paginacao")
	public Page<Jogador> buscarJogadorPorPaginacao
	(@RequestParam @Schema(example = ExampleValuesJogador.PAGINA) Integer pagina,
	@RequestParam @Schema(example = ExampleValuesJogador.ITENS_POR_PAGINA) 
	Integer itensPorPagina,	
	@RequestParam @Schema(example = ExampleValuesJogador.ORDENACAO)
	String ordenacao,
	@RequestParam @Schema(example = ExampleValuesJogador.TIPO_ORDENACAO) 
	String tipoOrdenacao) {
		PageRequest page = PageRequest.of(pagina, itensPorPagina, 
				tipoOrdenacao.equals("ASC")?
				Sort.by(ordenacao).ascending():
					Sort.by(ordenacao).descending());
		return estudanteService.buscarJogadorPorPaginacao(page);
		
	}

}
