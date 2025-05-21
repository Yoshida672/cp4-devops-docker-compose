package br.com.fiap.cp_api_rest.controller;

import br.com.fiap.cp_api_rest.dto.MoveRequest;
import br.com.fiap.cp_api_rest.dto.MoveResponse;
import br.com.fiap.cp_api_rest.service.MoveService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/moves",produces = {"application/json"})
@Tag(name = "api-moves")

public class MoveController {

    private final MoveService service;

    public MoveController(MoveService service) {
        this.service = service;
    }



    @GetMapping
    public ResponseEntity<Page<MoveResponse>> getAll(@RequestParam(defaultValue = "0") Integer page) {
        Pageable pageable = PageRequest.of(page, 2);
        return ResponseEntity.ok(service.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MoveResponse> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<MoveResponse> create(@RequestBody @Valid MoveRequest request) {
        return ResponseEntity.ok(service.save(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MoveResponse> update(@PathVariable Integer id, @RequestBody @Valid MoveRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
