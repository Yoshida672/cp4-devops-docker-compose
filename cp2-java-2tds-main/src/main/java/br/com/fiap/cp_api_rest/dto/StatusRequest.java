package br.com.fiap.cp_api_rest.dto;

import jakarta.validation.constraints.*;

public record StatusRequest(
        @PositiveOrZero
        int exp,
        @Min(value = 1)
        @Max(value = 255)
        int hpBase,

        @Min(value = 1)
        @Max(value = 255)
        int attackBase,

        @Min(value = 1)
        @Max(value = 255)
        int spAttackBase,

        @Min(value = 1)
        @Max(value = 255)
        int defenseBase,

        @Min(value = 1)
        @Max(value = 255)
        int spDefenseBase,

        @Min(value = 1)
        @Max(value = 255)
        int speedBase

) {}
