package br.com.fiap.cp_api_rest.dto;

import br.com.fiap.cp_api_rest.entity.PokemonTrainer;
import br.com.fiap.cp_api_rest.enums.Gender;
import br.com.fiap.cp_api_rest.enums.GroupExp;
import br.com.fiap.cp_api_rest.enums.Nature;
import org.springframework.hateoas.Link;

import java.util.Date;
import java.util.List;

public record PokemonTrainerResponse(
        Long id,
        String nickname,
        String ability,
        String location,
        boolean shiny,
        GroupExp groupExp,
        Gender gender,
        Nature nature,
        StatusResponse status,
        Date dateCapture,
        String trainerName,
        String pokemonName,
        List<String> moveNames

) {


}
