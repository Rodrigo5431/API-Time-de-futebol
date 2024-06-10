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

import br.com.app.commons.ExampleValuesTreinador;
import br.com.app.dto.TreinadorDTO;
import br.com.app.entity.Treinador;
import br.com.app.service.TreinadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("treinadores")
public class TreinadorResource {

	@Autowired
	private TreinadorService treinadorService;

	@Autowired
	private ModelMapper mapper;

	@GetMapping
	public ResponseEntity<List<TreinadorDTO>> buscarTodosTreinadores() {
		List<Treinador> listaTreinadores = treinadorService.buscarTodosTreinadores();
		// adicionar o modelmapper manualmente no pom.xml (pesquisar modelmapper maven)
		List<TreinadorDTO> listaTreinadorDTO = listaTreinadores.stream()
				.map(treinador -> mapper.map(treinador, TreinadorDTO.class)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listaTreinadorDTO);
	}

	@GetMapping("/{matricula}")
	@Operation(description = "Retorna o registro do treinador pelo matrícula")
	public ResponseEntity<TreinadorDTO> buscarTreinadorPelaMatricula
	(@PathVariable("matricula") @Schema(example = ExampleValuesTreinador.ID_TREINADOR) Integer matricula) {
		Treinador treinador = treinadorService.buscarTreinadorPorId(matricula);
		TreinadorDTO treinadorDTO = mapper.map(treinador, TreinadorDTO.class);
		return ResponseEntity.ok().body(treinadorDTO);
	}

	@PostMapping
	public ResponseEntity<TreinadorDTO> cadastrarTreinador(
			@io.swagger.v3.oas.annotations.parameters.RequestBody
			(content = @Content
			(examples = {
		    @ExampleObject(name = "Exemplo de Treinador", value = ExampleValuesTreinador.exemploTreinador)
			}))
			@RequestBody TreinadorDTO treinadorDTO) {
		Treinador treinador = mapper.map(treinadorDTO, Treinador.class);
		treinador = treinadorService.salvar(treinador);
		TreinadorDTO novoTreinador = mapper.map(treinador, TreinadorDTO.class);
		return ResponseEntity.ok().body(novoTreinador);
	}

	// PUT - atualiza todo o registro
	// Patch - atualiza somente um campo
	@PutMapping("/{id}")
	public ResponseEntity<TreinadorDTO> atualizarTreinador(@PathVariable Integer id,
			@RequestBody TreinadorDTO treinadorDTO) {
		Treinador treinador = mapper.map(treinadorDTO, Treinador.class);
		treinador = treinadorService.updateTreinador(id, treinador);
		TreinadorDTO novoTreinador = mapper.map(treinador, TreinadorDTO.class);
		return ResponseEntity.ok().body(novoTreinador);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> excluirTreinador(@PathVariable Integer id) {
		Boolean flag = treinadorService.deletarTreinador(id);
		return ResponseEntity.ok().body(flag);
	}

	@GetMapping("paginacao")
	public Page<Treinador> buscarTreinadorPorPaginacao(@RequestParam Integer pagina,
			@RequestParam Integer itensPorPagina, @RequestParam String ordenacao, @RequestParam String tipoOrdenacao) {

		PageRequest page = PageRequest.of(pagina, itensPorPagina,
				(tipoOrdenacao.equals("ASC") ? Sort.by(ordenacao).ascending() : Sort.by(ordenacao).descending()));
		return treinadorService.buscarTreinadorPorPaginacao(page);
	}

}