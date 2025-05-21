package br.com.fiap.cp_api_rest.service;

import br.com.fiap.cp_api_rest.dto.MoveResponse;
import br.com.fiap.cp_api_rest.dto.PokemonRequest;
import br.com.fiap.cp_api_rest.dto.PokemonResponse;
import br.com.fiap.cp_api_rest.entity.Pokemon;
import br.com.fiap.cp_api_rest.entity.Move;
import br.com.fiap.cp_api_rest.repository.PokemonRepository;
import br.com.fiap.cp_api_rest.repository.MoveRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokemonService {

    private final PokemonRepository repository;
    private final MoveRepository moveRepository;

    public PokemonService(PokemonRepository repository, MoveRepository moveRepository) {
        this.repository = repository;
        this.moveRepository = moveRepository;
    }

    public List<PokemonResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(PokemonService::PokemonToResponse)
                .collect(Collectors.toList());
    }

    public PokemonResponse findById(Integer id) {
        Pokemon pokemon = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pokemon não encontrado com id " + id));
        return PokemonToResponse(pokemon);
    }

    public PokemonResponse save(PokemonRequest request) {
        Pokemon pokemon = RequestToPokemon(request);
        return PokemonToResponse(repository.save(pokemon));
    }

    public PokemonResponse update(Integer id, PokemonRequest request) {
        Pokemon existente = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pokemon não encontrado com id " + id));
        Pokemon atualizado = RequestToPokemon(request);
        atualizado.setId(id);
        return PokemonToResponse(repository.save(atualizado));
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    private Pokemon RequestToPokemon(PokemonRequest request) {
        List<Move> moves = request.possibleMovesLearnId() != null
                ? request.possibleMovesLearnId().stream()
                .map(id -> moveRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Move com ID " + id + " não encontrado")))
                .collect(Collectors.toList())
                : List.of();

        return new Pokemon(
                null,
                request.number(),
                request.name(),
                request.title(),
                request.description(),
                request.possibleGender(),
                request.possibleAbility(),
                request.typePrimary(),
                request.typeSecondary(),
                request.lineEvolution(),
                moves
        );
    }

    public static PokemonResponse PokemonToResponse(Pokemon pokemon) {

        return new PokemonResponse(
                pokemon.getId(),
                pokemon.getNumber(),
                pokemon.getName(),
                pokemon.getTitle(),
                pokemon.getDescription(),
                pokemon.getTypePrimary(),
                pokemon.getTypeSecondary(),
                pokemon.getLineEvolution()
        );
    }



}

