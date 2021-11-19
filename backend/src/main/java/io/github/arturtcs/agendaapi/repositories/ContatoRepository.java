package io.github.arturtcs.agendaapi.repositories;

import io.github.arturtcs.agendaapi.model.Contato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContatoRepository extends JpaRepository<Contato, Integer> {
}
