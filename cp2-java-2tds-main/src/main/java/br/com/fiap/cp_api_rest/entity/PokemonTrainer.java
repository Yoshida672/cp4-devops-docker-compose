package br.com.fiap.cp_api_rest.entity;

import br.com.fiap.cp_api_rest.enums.Gender;
import br.com.fiap.cp_api_rest.enums.GroupExp;
import br.com.fiap.cp_api_rest.enums.Nature;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
@Entity
@Table(name = "pokemon_trainer")
public class PokemonTrainer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "nickname",nullable = false)
    private String nickname;
    @Enumerated(EnumType.STRING)
    @Column(name = "group_exp",nullable = false)
    private GroupExp groupExp;

    @ManyToOne
    @JoinColumn(name="id_pokemon")
    private Pokemon pokemon;
    @Enumerated(EnumType.STRING)
    @Column(name = "gender",nullable = false)
    private Gender gender;
    @Column(name = "ability",nullable = false)
    private String ability;
    @Enumerated(EnumType.STRING)
    @Column(name = "nature",nullable = false)
    private Nature nature;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_status")
    private Status status;
    @Column(name = "location",nullable = false)
    private String location;
    @ManyToOne
    @JoinColumn(name="id_trainer")
    private Trainer trainer;
    @Column(name = "date_capture",nullable = false)
    private Date dateCapture;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="pokemon_trainer_moves",
            joinColumns =
            @JoinColumn(name="id_pokemon_trainer",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_move",referencedColumnName = "id")
    )
    private List<Move> moves;
    @Column(name = "shiny",nullable = false)
    private boolean shiny;

    public PokemonTrainer() {
    }

    public PokemonTrainer(Long id, String nickname, GroupExp groupExp, Pokemon pokemon, Gender gender, String ability, Nature nature, Status status, String location, Trainer trainer, Date dateCapture, List<Move> moves, boolean shiny) {
        this.id = id;
        this.nickname = nickname;
        this.groupExp = groupExp;
        this.pokemon = pokemon;
        this.gender = gender;
        this.ability = ability;
        this.nature = nature;
        this.status = status;
        this.location = location;
        this.trainer = trainer;
        this.dateCapture = dateCapture;
        this.moves = moves;
        this.shiny = shiny;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public GroupExp getGroupExp() {
        return groupExp;
    }

    public void setGroupExp(GroupExp groupExp) {
        this.groupExp = groupExp;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getAbility() {
        return ability;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }

    public Nature getNature() {
        return nature;
    }

    public void setNature(Nature nature) {
        this.nature = nature;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public Date getDateCapture() {
        return dateCapture;
    }

    public void setDateCapture(Date dateCapture) {
        this.dateCapture = dateCapture;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public boolean isShiny() {
        return shiny;
    }

    public void setShiny(boolean shiny) {
        this.shiny = shiny;
    }
}
