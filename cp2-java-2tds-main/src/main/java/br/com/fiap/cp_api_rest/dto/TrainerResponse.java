package br.com.fiap.cp_api_rest.dto;

import br.com.fiap.cp_api_rest.entity.PokemonTrainer;
import org.springframework.hateoas.Link;

import java.util.Date;
import java.util.List;

public record TrainerResponse(
        Long id,
        String name,
        Date startJourney,
        double money,
        int pokemonHasCaught,
        int pokemonHasView,
        int numberBadge,
        String city,
        List<PokemonTrainer> pokemons

) {}
