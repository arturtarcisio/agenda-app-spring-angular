package io.github.arturtcs.agendaapi.controllers;

import io.github.arturtcs.agendaapi.model.Contato;
import io.github.arturtcs.agendaapi.services.ContatoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contatos")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ContatoController {

    private final ContatoService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Contato save(@RequestBody Contato contato) {
        return service.salvar(contato);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete (@PathVariable Integer id) {
        service.delete(id);
    }

    @GetMapping
    public List<Contato> listarTodos() {
        return service.listarContatos();
    }

    //Atualizacao parcial da entidade, apenas um atributo. Utilizar o patch.
    @PatchMapping("{id}/favorito")
    public void favoritar(@PathVariable Integer id, @RequestBody Boolean favorito) {
        service.favoritar(id, favorito);
    }

}
