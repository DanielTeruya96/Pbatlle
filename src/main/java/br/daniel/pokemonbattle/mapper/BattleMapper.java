package br.daniel.pokemonbattle.mapper;

import br.daniel.pokemonbattle.dto.response.PokeBattleResponse;
import br.daniel.pokemonbattle.model.Battle;
import static java.util.Objects.nonNull;

public class BattleMapper {
    public static Battle map(PokeBattleResponse response) {
        Battle battle = new Battle();
        battle.setBattled(response.getDate());
        battle.setFirstPokemonId(response.getFirstPokemon().getId());
        battle.setSecondPokemonId(response.getSecondPokeon().getId());
        if(nonNull(response.getWinner())){
            battle.setWinnerPokemonId(response.getWinner().getId());
        }

        return battle;
    }
}
