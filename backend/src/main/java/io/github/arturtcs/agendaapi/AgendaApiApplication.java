package io.github.arturtcs.agendaapi;

import io.github.arturtcs.agendaapi.model.Contato;
import io.github.arturtcs.agendaapi.repositories.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AgendaApiApplication {

	@Bean
	public CommandLineRunner commandLineRunner(@Autowired ContatoRepository repository) {
		return args -> {
			 Contato contato = Contato.builder()
					 .nome("Artur")
					 .email("arturtarcisio1@gmail.com")
					 .favorito(false).build();

			repository.save(contato);
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(AgendaApiApplication.class, args);
	}

}
