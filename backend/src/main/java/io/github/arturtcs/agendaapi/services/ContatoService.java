package io.github.arturtcs.agendaapi.services;

import io.github.arturtcs.agendaapi.model.Contato;
import io.github.arturtcs.agendaapi.repositories.ContatoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContatoService {

    private final ContatoRepository repository;

    public Contato salvar ( Contato contato ) {
        return repository.save(contato);
    }

    public void delete (Integer id) {
        repository.deleteById(id);
    }

    public List<Contato> listarContatos() {
        return repository.findAll();
    }

    public void favoritar(Integer id) {
        Optional<Contato> contato = repository.findById(id);
        contato.ifPresent( c -> {
            boolean favorito = c.getFavorito() == Boolean.TRUE;
            c.setFavorito(!favorito);
            repository.save(c);
        });
    }
}
