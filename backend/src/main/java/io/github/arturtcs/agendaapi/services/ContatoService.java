package io.github.arturtcs.agendaapi.services;

import io.github.arturtcs.agendaapi.model.Contato;
import io.github.arturtcs.agendaapi.repositories.ContatoRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
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

    public Page<Contato> listarPorPaginas(Integer pagina, Integer tamanhoPagina){
        PageRequest pageRequest = PageRequest.of(pagina, tamanhoPagina);
        return repository.findAll(pageRequest);
    }

    public void favoritar(Integer id) {
        Optional<Contato> contato = repository.findById(id);
        contato.ifPresent( c -> {
            boolean favorito = c.getFavorito() == Boolean.TRUE;
            c.setFavorito(!favorito);
            repository.save(c);
        });
    }

    public byte[] addPhoto(Integer id, Part arquivo) {
        Optional<Contato> contato = repository.findById(id);
        return contato.map( c -> {
            try {
                InputStream inputStream = arquivo.getInputStream();
                byte[] bytes = new byte[(int) arquivo.getSize()];
                IOUtils.readFully(inputStream, bytes);
                c.setFoto(bytes);
                repository.save(c);
                inputStream.close();
                return bytes;
            } catch (IOException e) {
                return null;
            }
        }).orElse(null);
    }
}
