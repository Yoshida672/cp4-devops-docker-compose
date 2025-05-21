package br.com.fiap.cp_api_rest.dto;

import br.com.fiap.cp_api_rest.entity.Move;
import br.com.fiap.cp_api_rest.enums.Gender;
import br.com.fiap.cp_api_rest.enums.Type;
import jakarta.validation.constraints.*;

import java.util.List;

public record PokemonRequest(

        @Min(1)
        int number,

        @NotBlank
        @Size(min = 2, max = 100)
        String name,

        @NotBlank
        @Size(min = 2, max = 100)
        String title,

        @NotBlank
        @Size(min = 10, max = 500)
        String description,

        @NotEmpty
        List<@NotNull Gender> possibleGender,

        @NotEmpty
        List<@NotBlank String> possibleAbility,

        @NotNull
        Type typePrimary,

        Type typeSecondary,

        @Min(1)
        @Max(3)
        int lineEvolution,

        @NotEmpty
        List<@NotNull Integer> possibleMovesLearnId

) {}
