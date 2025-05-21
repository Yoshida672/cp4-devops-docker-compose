package br.com.fiap.cp_api_rest.repository;

import br.com.fiap.cp_api_rest.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StatusRepository extends JpaRepository<Status, Integer> {
}
