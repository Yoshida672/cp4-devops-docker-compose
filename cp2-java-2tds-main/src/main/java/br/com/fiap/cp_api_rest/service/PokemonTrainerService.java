package br.com.fiap.cp_api_rest.service;

import br.com.fiap.cp_api_rest.dto.PokemonTrainerRequest;
import br.com.fiap.cp_api_rest.dto.PokemonTrainerResponse;
import br.com.fiap.cp_api_rest.dto.StatusResponse;
import br.com.fiap.cp_api_rest.entity.*;
import br.com.fiap.cp_api_rest.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokemonTrainerService {

    private final PokemonTrainerRepository repository;
    private final TrainerRepository trainerRepository;
    private final PokemonRepository pokemonRepository;
    private final MoveRepository moveRepository;

    public PokemonTrainerService(PokemonTrainerRepository repository, TrainerRepository trainerRepository, PokemonRepository pokemonRepository, MoveRepository moveRepository) {
        this.repository = repository;
        this.trainerRepository = trainerRepository;
        this.pokemonRepository = pokemonRepository;
        this.moveRepository = moveRepository;
    }

    public List<PokemonTrainerResponse> findAll() {
        return repository.findAll().stream()
                .map(this::PokemonTrainerToResponse)
                .collect(Collectors.toList());
    }

    public PokemonTrainerResponse findById(Long id) {
        PokemonTrainer pokemonTrainer = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("PokemonTrainer não encontrado com ID: " + id));
        return PokemonTrainerToResponse(pokemonTrainer);
    }

    public PokemonTrainerResponse save(PokemonTrainerRequest request) {
        PokemonTrainer pokemonTrainer = requestToPokemonTrainer(request);
        return PokemonTrainerToResponse(repository.save(pokemonTrainer));
    }

    public PokemonTrainerResponse update(Long id, PokemonTrainerRequest request) {
        PokemonTrainer existente = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("PokemonTrainer não encontrado com ID: " + id));
        PokemonTrainer atualizado = requestToPokemonTrainer(request);
        atualizado.setId(id);
        return PokemonTrainerToResponse(repository.save(atualizado));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private PokemonTrainer requestToPokemonTrainer(PokemonTrainerRequest request) {
        Trainer trainer = trainerRepository.findById(Long.valueOf(request.trainerId()))
                .orElseThrow(() -> new EntityNotFoundException("Treinador não encontrado"));

        Pokemon pokemon = pokemonRepository.findById(Long.valueOf(request.pokemonId()))
                .orElseThrow(() -> new EntityNotFoundException("Pokemon não encontrado"));

        List<Move> moves = request.movesId() != null ?
                request.movesId().stream()
                        .map(id -> moveRepository.findById(id)
                                .orElseThrow(() -> new EntityNotFoundException("Move com ID " + id + " não encontrado")))
                        .collect(Collectors.toList())
                : List.of();

        Status status = new Status();
        status.setExpPoints(request.status().exp());
        status.setHpBase(request.status().hpBase());
        status.setAttackBase(request.status().attackBase());
        status.setSpAttackBase(request.status().spAttackBase());
        status.setDefenseBase(request.status().defenseBase());
        status.setSpDefenseBase(request.status().spDefenseBase());
        status.setSpeedBase(request.status().speedBase());

        status.generatedIvs();
        status.presetEVs();
        status.calculateAllAttributes();
        status.calculateLevelFromExp(request.groupExp(),request.status().exp());

        PokemonTrainer pokemonTrainer = new PokemonTrainer();
        pokemonTrainer.setPokemon(pokemon);
        pokemonTrainer.setTrainer(trainer);
        pokemonTrainer.setMoves(moves);
        pokemonTrainer.setAbility(request.ability());
        pokemonTrainer.setNature(request.nature());
        pokemonTrainer.setLocation(request.location());
        pokemonTrainer.setGender(request.gender());
        pokemonTrainer.setShiny(request.shiny());
        pokemonTrainer.setGroupExp(request.groupExp());
        pokemonTrainer.setDateCapture(request.dateCapture());
        pokemonTrainer.setNickname(request.nickname() != null && !request.nickname().isBlank()
                ? request.nickname()
                : pokemon.getName());

        pokemonTrainer.setStatus(status);
        status.setPokemonTrainer(pokemonTrainer);

        return pokemonTrainer;
    }

    private PokemonTrainerResponse PokemonTrainerToResponse(PokemonTrainer pt) {
        StatusResponse status = new StatusResponse(
                pt.getStatus().getLvl(),
                pt.getStatus().getHp(),
                pt.getStatus().getAttack(),
                pt.getStatus().getSpAttack(),
                pt.getStatus().getDefense(),
                pt.getStatus().getSpDefense(),
                pt.getStatus().getSpeed(),
                pt.getStatus().getTotal()
        );

        return new PokemonTrainerResponse(
    pt.getId(),
    pt.getNickname(),
    pt.getAbility(),
    pt.getLocation(),
    pt.isShiny(),
    pt.getGroupExp(),
    pt.getGender(),
    pt.getNature(),
    status,
    pt.getDateCapture(),
    pt.getTrainer().getName(),
    pt.getPokemon().getName(),
    pt.getMoves().stream().map(Move::getName).toList());
    }


}
