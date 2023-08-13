package br.daniel.pokemonbattle.expection;

import lombok.Getter;

@Getter
public class PokemonApiException extends RuntimeException{

    private String reason;

    public PokemonApiException(String reason) {
        this.reason = reason;
    }
}
