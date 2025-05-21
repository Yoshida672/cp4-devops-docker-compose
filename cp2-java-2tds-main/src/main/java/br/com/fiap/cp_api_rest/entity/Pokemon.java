package br.com.fiap.cp_api_rest.entity;

import br.com.fiap.cp_api_rest.enums.Gender;
import br.com.fiap.cp_api_rest.enums.Type;
import jakarta.persistence.*;

import java.util.List;
@Entity
public class Pokemon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int number;
    private String name;
    private String title;
    private String description;
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<Gender> possibleGender;
    @ElementCollection
    private List<String> possibleAbility;
    @Enumerated(EnumType.STRING)
    private Type typePrimary;
    @Enumerated(EnumType.STRING)
    private Type typeSecondary;
    private int lineEvolution;
    @ManyToMany
    @JoinTable(name="pokemon_moves",
            joinColumns =
            @JoinColumn(name="id_pokemon",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_moves",referencedColumnName = "id")
    )
    private List<Move> possibleMovesLearn;
    //TODO: CRIAR CLASSE EVOLUÇÃO E APLICAR UMA LISTA DE EVOLUÇÕES AO POKEMON


    public Pokemon() {
    }

    public Pokemon(Integer id, int number, String name, String title, String description, List<Gender> possibleGender, List<String> possibleAbility, Type typePrimary, Type typeSecondary, int lineEvolution, List<Move> possibleMovesLearn) {
        this.id = id;
        this.number = number;
        this.name = name;
        this.title = title;
        this.description = description;
        this.possibleGender = possibleGender;
        this.possibleAbility = possibleAbility;
        this.typePrimary = typePrimary;
        this.typeSecondary = typeSecondary;
        this.lineEvolution = lineEvolution;
        this.possibleMovesLearn = possibleMovesLearn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Gender> getPossibleGender() {
        return possibleGender;
    }

    public void setPossibleGender(List<Gender> possibleGender) {
        this.possibleGender = possibleGender;
    }

    public List<String> getPossibleAbility() {
        return possibleAbility;
    }

    public void setPossibleAbility(List<String> possibleAbility) {
        this.possibleAbility = possibleAbility;
    }

    public Type getTypePrimary() {
        return typePrimary;
    }

    public void setTypePrimary(Type typePrimary) {
        this.typePrimary = typePrimary;
    }

    public Type getTypeSecondary() {
        return typeSecondary;
    }

    public void setTypeSecondary(Type typeSecondary) {
        this.typeSecondary = typeSecondary;
    }

    public int getLineEvolution() {
        return lineEvolution;
    }

    public void setLineEvolution(int lineEvolution) {
        this.lineEvolution = lineEvolution;
    }

    public List<Move> getPossibleMovesLearn() {
        return possibleMovesLearn;
    }

    public void setPossibleMovesLearn(List<Move> possibleMovesLearn) {
        this.possibleMovesLearn = possibleMovesLearn;
    }
}
