package br.com.fiap.cp_api_rest.service;

import br.com.fiap.cp_api_rest.controller.MoveController;
import br.com.fiap.cp_api_rest.dto.MoveRequest;
import br.com.fiap.cp_api_rest.dto.MoveResponse;
import br.com.fiap.cp_api_rest.entity.Move;
import br.com.fiap.cp_api_rest.repository.MoveRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class MoveService {

    private final MoveRepository repository;

    public MoveService(MoveRepository repository) {
        this.repository = repository;
    }

    public Page<MoveResponse> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(move -> MoveToResponse(move,true));

    }

    public MoveResponse findById(Integer id) {
        Move move = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Move não encontrado com id " + id));
        return MoveToResponse(move,false);
    }

    public MoveResponse save(MoveRequest request) {
        Move move = RequestToMove(request);
        return MoveToResponse(repository.save(move),true);
    }

    public MoveResponse update(Integer id, MoveRequest request) {
        Move moveExistente = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Move não encontrado com id " + id));

        moveExistente.setName(request.name());
        moveExistente.setDescription(request.description());
        moveExistente.setType(request.type());
        moveExistente.setCategory(request.category());
        moveExistente.setPower(request.power());
        moveExistente.setAccuracy(request.accuracy());
        moveExistente.setPpMax(request.ppMax());
        moveExistente.setPokemonsLearn(request.pokemonsLearn());

        return MoveToResponse(repository.save(moveExistente),false);
    }

    public void delete(Integer id) {
        repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Move não encontrado com id " + id));
        repository.deleteById(id);
    }


    private Move RequestToMove(MoveRequest request) {
        Move move = new Move();
        move.setName(request.name());
        move.setDescription(request.description());
        move.setType(request.type());
        move.setCategory(request.category());
        move.setPower(request.power());
        move.setAccuracy(request.accuracy());
        move.setPpMax(request.ppMax());
        move.setPpCurrent(move.getPpMax());
        move.setPokemonsLearn(request.pokemonsLearn());
        return move;
    }

    public MoveResponse MoveToResponse(Move move, boolean self) {
        Link link;
        if(self){
            link = linkTo(methodOn(MoveController.class).getById(move.getId())).withSelfRel().withRel("Move");
        }else{
            link =linkTo(
                    methodOn(
                            MoveController.class
                    ).getById(0)
            ).withRel("Lista de Clientes");
        }
        return new MoveResponse(
                move.getId(),
                move.getName(),
                move.getDescription(),
                move.getType(),
                move.getCategory(),
                move.getPower(),
                move.getAccuracy(),
                move.getPpMax(),
                move.getPpCurrent()
        );
    }
}
