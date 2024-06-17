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

import br.com.app.commons.ExampleValuesTime;
import br.com.app.dto.TimeDTO;
import br.com.app.entity.Time;
import br.com.app.service.TimeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("times")
public class TimeResource {

	@Autowired
	private TimeService timeService;

	@Autowired
	private ModelMapper mapper;

	@GetMapping
	public ResponseEntity<List<TimeDTO>> buscarTodasTimes() {
		List<Time> listaTimes = timeService.buscarTodosTimes();
		List<TimeDTO> listaTimeDTO = listaTimes.stream()
				.map(time -> mapper.map(time, TimeDTO.class)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listaTimeDTO);
	}

	@GetMapping("/{numeroTime}")
	@Operation(description = "Retorna o registro do time pelo número")
	public ResponseEntity<TimeDTO> buscarTimePelaMatricula
	(@PathVariable("numeroTime") @Schema(example = ExampleValuesTime.ID_TIME) Integer numeroTime) {
		Time time = timeService.buscarTimePorId(numeroTime);
		TimeDTO timeDTO = mapper.map(time, TimeDTO.class);
		return ResponseEntity.ok().body(timeDTO);
	}

	@PostMapping
	public ResponseEntity<TimeDTO> cadastrarTime(
			@io.swagger.v3.oas.annotations.parameters.RequestBody
			(content = @Content
			(examples = {
		@ExampleObject(name = "Exemplo de Time", value = ExampleValuesTime.exemploTime)
			}))
			@RequestBody TimeDTO timeDTO) {
		Time time = mapper.map(timeDTO, Time.class);
		time = timeService.salvar(time);
		TimeDTO novoTime = mapper.map(time, TimeDTO.class);
		return ResponseEntity.ok().body(novoTime);
	}

	@PutMapping("/{id}")
	public ResponseEntity<TimeDTO> atualizarTime(@PathVariable Integer id,
			@RequestBody TimeDTO timeDTO) {
		Time time = mapper.map(timeDTO, Time.class);
		time = timeService.updateTime(id, time);
		TimeDTO novoTime = mapper.map(time, TimeDTO.class);
		return ResponseEntity.ok().body(novoTime);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> excluirTime(@PathVariable Integer id) {
		Boolean flag = timeService.deletarTime(id);
		return ResponseEntity.ok().body(flag);
	}

	@GetMapping("paginacao")
	public Page<Time> buscarTimePorPaginacao(@RequestParam Integer pagina,
			@RequestParam Integer itensPorPagina, @RequestParam String ordenacao, @RequestParam String tipoOrdenacao) {

		PageRequest page = PageRequest.of(pagina, itensPorPagina,
				(tipoOrdenacao.equals("ASC") ? Sort.by(ordenacao).ascending() : Sort.by(ordenacao).descending()));
		return timeService.buscarTimePorPaginacao(page);
	}

}