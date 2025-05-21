package br.com.fiap.cp_api_rest.dto;

import jakarta.validation.constraints.*;
import java.util.Date;

public record TrainerRequest(

        @NotBlank(message = "O nome é obrigatório")
        String name,

        @NotNull(message = "A data de início da jornada é obrigatória")
        @PastOrPresent(message = "A data de inicio tem que ter ocorrido")
        Date startJourney,

        @NotNull(message = "O dinheiro não pode ser nulo")
        @Min(value = 0,message = "O dinheiro não pode ser negativo")
        double money,

        @Min(value = 0, message = "O número de Pokémon capturados não pode ser negativo")
        int pokemonHasCaught,

        @Min(value = 0, message = "O número de Pokémon vistos não pode ser negativo")
        int pokemonHasView,

        @Min(value = 0, message = "O número de badges não pode ser negativo")
        int numberBadge,

        @NotBlank(message = "A cidade é obrigatória")
        String city
) {}
