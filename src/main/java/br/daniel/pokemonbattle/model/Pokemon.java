package br.daniel.pokemonbattle.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class Pokemon {

    private int id;
    private String name;
    private long baseExperience;
    private long height;
    private List<Move> moves;
    private Sprites sprites;
    private List<TypeList> types;
    private long weight;

}
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
class Move {
    private TypeClass move;

}

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
class Sprites {
    private String backDefault;
    private Object backFemale;
    private String backShiny;
    private Object backShinyFemale;
    private String frontDefault;
    private Object frontFemale;
    private String frontShiny;
    private Object frontShinyFemale;

}

