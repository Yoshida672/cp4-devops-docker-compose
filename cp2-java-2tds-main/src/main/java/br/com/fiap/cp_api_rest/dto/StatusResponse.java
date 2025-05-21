package br.com.fiap.cp_api_rest.dto;

import org.springframework.hateoas.Link;

public record StatusResponse(
        int lvl,
        int hp,
        int attack,
        int spAttack,
        int defense,
        int spDefense,
        int speed,
        int total


) {}
