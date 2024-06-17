package br.com.app.inicializacao;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import br.com.app.entity.Jogador;
import br.com.app.entity.Treinador;
import br.com.app.entity.Time;
import br.com.app.service.JogadorService;
import br.com.app.service.TreinadorService;
import br.com.app.service.TimeService;

@Component
public class Init implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private JogadorService jogadorService;

	@Autowired
	private TreinadorService treinadorService;
	
	@Autowired
	private TimeService timeService;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		Jogador jogador1 = new Jogador();
		jogador1.setNome("Lucas");
		jogador1.setNumeroCamisa("10");
		jogadorService.salvar(jogador1);

		Jogador jogador2 = new Jogador();
		jogador2.setNome("Rodolfo");
		jogador2.setNumeroCamisa("7");
		jogadorService.salvar(jogador2);

		Jogador jogador3 = new Jogador();
		jogador3.setNome("Fernando");
		jogador3.setNumeroCamisa("15");
		jogadorService.salvar(jogador3);

		// buscar Todos Os Jogadores
		List<Jogador> listaJogador = jogadorService.buscarTodosJogadors();
		listaJogador.forEach(jogador -> System.out.println(jogador.getNome()));

		// buscar Jogador Por Id
		Jogador jogador = jogadorService.buscarJogadorPorId(1);
		System.out.println(jogador.getNome());

		// deletar Jogador
		Boolean flag = jogadorService.deletarJogador(1);
		System.out.println(flag);

		// update Jogador
		Jogador jogador4 = new Jogador();
		jogador4.setNome("Fernando");
		jogador4.setNumeroCamisa("20");
		Jogador jogadorAlterado = jogadorService.updateJogador(3, jogador4);
		System.out.println(jogadorAlterado.getNumeroCamisa());

		//Treinador
		Treinador treinador1 = new Treinador();
		treinador1.setNome("Frederico");
		treinador1.setExperiencia("Master");
		treinadorService.salvar(treinador1);
		
		Treinador treinador2 = new Treinador();
		treinador2.setNome("Rafael");
		treinador2.setExperiencia("Sem experiencia");
		treinadorService.salvar(treinador2);

		Treinador treinador4 = new Treinador();
		treinador4.setNome("Lucas");
		treinador4.setExperiencia("Pleno");
		treinadorService.salvar(treinador4);

		//Buscar todos Treinadores
		List<Treinador> listaTreinadores = treinadorService.buscarTodosTreinadores();
		listaTreinadores.forEach(treinador -> System.out.println(treinador.getNome()));

		//Buscar por id
		Treinador treinador = treinadorService.buscarTreinadorPorId(1);
		System.out.println(treinador.getNome());

		//delete
		flag = treinadorService.deletarTreinador(2);
		System.out.println(flag);

		//update
		Treinador treinador3 = new Treinador();
		treinador3.setNome("Frederico");
		treinador3.setExperiencia("Aprendiz");
		Treinador treinadorAlterado = treinadorService.updateTreinador(1, treinador2);
		System.out.println(treinadorAlterado.getExperiencia());
		
		//time
		Time time1 = new Time();
		time1.setNomeTime("Touros Raivosos");
		time1.setJogadores("12");
		timeService.salvar(time1);
		
		Time time2 = new Time();
		time2.setNomeTime("Barbixas");
		time2.setJogadores("12");
		timeService.salvar(time2);

		Time time4 = new Time();
		time4.setNomeTime("Caramujos Furiosos");
		time4.setJogadores("23");
		timeService.salvar(time4);

		//buscar todos
		List<Time> listaTimes = timeService.buscarTodosTimes();
		listaTimes.forEach(time -> System.out.println(time.getNomeTime()));

		//buscar por id
		Time time = timeService.buscarTimePorId(1);
		System.out.println(time.getNomeTime());

		//delete
		flag = timeService.deletarTime(2);
		System.out.println(flag);

		//update
		Time time3 = new Time();
		time3.setNomeTime("Farofeiros");
		time3.setJogadores("11");
		Time timeAlterado = timeService.updateTime(1, time3);
		System.out.println(timeAlterado.getNomeTime());

	}
}
