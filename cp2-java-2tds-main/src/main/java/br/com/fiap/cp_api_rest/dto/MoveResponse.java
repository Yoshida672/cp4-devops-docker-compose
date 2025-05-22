package br.com.fiap.cp_api_rest.dto;

import br.com.fiap.cp_api_rest.entity.Move;
import br.com.fiap.cp_api_rest.enums.Category;
import br.com.fiap.cp_api_rest.enums.Type;

public record MoveResponse(
         String name,
         String description,
         Type type,
         Category category,
         double power,
         double accuracy,
         int ppMax,
         int ppCurrent
) {


}
