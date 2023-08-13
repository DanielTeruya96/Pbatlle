package br.daniel.pokemonbattle.dto.response;


import br.daniel.pokemonbattle.model.Pokemon;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class PokeBattleResponse {

    private Pokemon firstPokemon;
    private Pokemon secondPokeon;

    private LocalDateTime date;

    private Pokemon winner;

}
