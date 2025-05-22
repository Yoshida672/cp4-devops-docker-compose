package br.com.fiap.cp_api_rest;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "API POKEMON",description = "API RESTFUL de pokemons",version = "v1"))
public class CpApiRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(CpApiRestApplication.class, args);
	}

}
