package br.com.fiap.cp_api_rest.service;

import br.com.fiap.cp_api_rest.dto.TrainerRequest;
import br.com.fiap.cp_api_rest.dto.TrainerResponse;
import br.com.fiap.cp_api_rest.entity.Trainer;
import br.com.fiap.cp_api_rest.repository.TrainerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainerService {

    private final TrainerRepository repository;

    public TrainerService(TrainerRepository repository) {
        this.repository = repository;
    }

    public List<TrainerResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(this::TrainerToResponse)
                .collect(Collectors.toList());
    }

    public TrainerResponse findById(Long id) {
        Trainer trainer = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Treinador não encontrado com ID: " + id));
        return TrainerToResponse(trainer);
    }

    public TrainerResponse save(TrainerRequest request) {
        Trainer trainer = RequestToTrainer(request);
        return TrainerToResponse(repository.save(trainer));
    }

    public TrainerResponse update(Long id, TrainerRequest request) {
        Trainer existente = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Treinador não encontrado com ID: " + id));
        Trainer atualizado = RequestToTrainer(request);
        atualizado.setId(id);
        return TrainerToResponse(repository.save(atualizado));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private Trainer RequestToTrainer(TrainerRequest request) {
        return new Trainer(
                null,
                request.name(),
                request.startJourney(),
                request.money(),
                request.pokemonHasCaught(),
                request.pokemonHasView(),
                request.numberBadge(),
                request.city(),
                null
        );
    }

    private TrainerResponse TrainerToResponse(Trainer trainer) {
        return new TrainerResponse(
                trainer.getId(),
                trainer.getName(),
                trainer.getStartJourney(),
                trainer.getMoney(),
                trainer.getPokemonHasCaught(),
                trainer.getPokemonHasView(),
                trainer.getNumberBadge(),
                trainer.getCity(),
                trainer.getPokemons()
        );
    }
}
