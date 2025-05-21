package br.com.fiap.cp_api_rest.controller;

import br.com.fiap.cp_api_rest.dto.TrainerRequest;
import br.com.fiap.cp_api_rest.dto.TrainerResponse;
import br.com.fiap.cp_api_rest.service.TrainerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/trainers",produces = {"application/json"})
@Tag(name = "api-trainers")
public class TrainerController {

    private final TrainerService service;

    public TrainerController(TrainerService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<TrainerResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<TrainerResponse> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<TrainerResponse> save(@RequestBody @Valid TrainerRequest request) {
        return ResponseEntity.ok(service.save(request));
    }

    @PutMapping("{id}")
    public ResponseEntity<TrainerResponse> update(@PathVariable Integer id, @RequestBody @Valid TrainerRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
