package br.com.fiap.cp_api_rest.entity;


import br.com.fiap.cp_api_rest.enums.Category;
import br.com.fiap.cp_api_rest.enums.Effect;
import br.com.fiap.cp_api_rest.enums.Type;
import jakarta.persistence.*;
import jdk.jfr.Percentage;

import java.util.List;
@Entity
@Table(name = "poke_move")
public class Move {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    @Column(name = "move_name",nullable = false)
    private String name;

    @Column(name = "move_description",nullable = false)
    private String description;
    @Column(name = "move_type",nullable = false)
    private Type type;
    @Column(name = "move_category",nullable = false)
    private Category category;
    @Column(name = "move_power",nullable = false)
    private double power;
    @Column(name = "move_accuracy",nullable = false)
    private double accuracy;
    @Column(name = "pp_max",nullable = false)
    private int ppMax;
    @Column(name = "pp_current",nullable = false)
    private int ppCurrent;
//    private Effect effectMove;
//    private double chanceEffectMove;
//    private Effect effectSecondary;
//    private double chanceEffectSecondary;
    @ManyToMany(mappedBy = "possibleMovesLearn")
    private List<Pokemon> pokemonsLearn;

    public Move() {
    }

    public Move(Integer id, String name, String description, Type type, Category category, double power, double accuracy, int ppMax, int ppCurrent,  List<Pokemon> pokemonsLearn) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.category = category;
        this.power = power;
        this.accuracy = accuracy;
        this.ppMax = ppMax;
        this.ppCurrent = ppCurrent;
        this.pokemonsLearn = pokemonsLearn;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public int getPpMax() {
        return ppMax;
    }

    public void setPpMax(int ppMax) {
        this.ppMax = ppMax;
    }

    public int getPpCurrent() {
        return ppCurrent;
    }

    public void setPpCurrent(int ppCurrent) {
        this.ppCurrent = ppCurrent;
    }

    public List<Pokemon> getPokemonsLearn() {
        return pokemonsLearn;
    }

    public void setPokemonsLearn(List<Pokemon> pokemonsLearn) {
        this.pokemonsLearn = pokemonsLearn;
    }
}
