package br.daniel.pokemonbattle.util;

import br.daniel.pokemonbattle.expection.PokemonApiException;

import java.util.List;

public class ValidationUtils {

    public static void ValidPokemonId(List<Integer> pokemonId) {
        pokemonId.forEach(id -> {
            if (id < 0 || id > 1009)
                throw new PokemonApiException("Invalid pokemon id: " + id);
        });
    }
}
