package br.com.fiap.cp_api_rest.dto;

import br.com.fiap.cp_api_rest.entity.Pokemon;
import br.com.fiap.cp_api_rest.enums.Category;
import br.com.fiap.cp_api_rest.enums.Type;
import jakarta.validation.constraints.*;

import java.util.List;

public record MoveRequest(

        @NotBlank
        @Size(min = 2, max = 100)
        String name,

        @NotBlank
        @Size(min = 10, max = 300)
        String description,

        @NotNull
        Type type,

        @NotNull
        Category category,

        @DecimalMin(value = "0.0", inclusive = true)
        @DecimalMax(value = "120.0", inclusive = true)
        double power,

        @DecimalMin(value = "0.0", inclusive = true)
        @DecimalMax(value = "100.0", inclusive = true)
        double accuracy,

        @Min(1)
        int ppMax,

        List<@NotNull Pokemon> pokemonsLearn

) {}
