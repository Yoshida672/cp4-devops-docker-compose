package br.com.fiap.cp_api_rest.repository;

import br.com.fiap.cp_api_rest.entity.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainerRepository extends JpaRepository<Trainer, Long> {
}
