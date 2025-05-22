package br.com.fiap.cp_api_rest.controller;

import br.com.fiap.cp_api_rest.dto.PokemonTrainerRequest;
import br.com.fiap.cp_api_rest.dto.PokemonTrainerResponse;
import br.com.fiap.cp_api_rest.service.PokemonTrainerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/pokemon-trainers",produces = {"application/json"})
@Tag(name = "api-pokemons-trainers")
public class PokemonTrainerController {

    private final PokemonTrainerService service;

    public PokemonTrainerController(PokemonTrainerService service) {
        this.service = service;
    }

    @GetMapping
    public List<PokemonTrainerResponse> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PokemonTrainerResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<PokemonTrainerResponse> save(@RequestBody @Valid PokemonTrainerRequest request) {
        return ResponseEntity.ok(service.save(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PokemonTrainerResponse> update(@PathVariable Long id, @RequestBody @Valid PokemonTrainerRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
