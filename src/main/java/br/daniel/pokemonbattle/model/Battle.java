package br.daniel.pokemonbattle.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Battle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column
    private int firstPokemonId;

    @Column
    private int secondPokemonId;

    @Column
    private LocalDateTime battled;

    @Column int winnerPokemonId;

}
