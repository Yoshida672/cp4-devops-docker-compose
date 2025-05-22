package br.com.fiap.cp_api_rest.repository;

import br.com.fiap.cp_api_rest.entity.PokemonTrainer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PokemonTrainerRepository extends JpaRepository<PokemonTrainer, Long> {
}
