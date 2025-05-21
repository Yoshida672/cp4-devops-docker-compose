package br.com.fiap.cp_api_rest.repository;

import br.com.fiap.cp_api_rest.entity.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PokemonRepository extends JpaRepository<Pokemon,Integer> {
}
