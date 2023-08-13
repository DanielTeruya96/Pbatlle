package br.daniel.pokemonbattle.mapper;

import br.daniel.pokemonbattle.dto.response.PokeBattleResponse;
import br.daniel.pokemonbattle.model.Battle;
import br.daniel.pokemonbattle.model.Pokemon;
import br.daniel.pokemonbattle.util.PokemonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BattleSearchResponseMapper {

    @Autowired
    private PokemonClient pokemonClient;

    public List<PokeBattleResponse> map(List<Battle> battles) {

        return battles.parallelStream().map(battle -> {
            Pokemon first = pokemonClient.getPokemon(battle.getFirstPokemonId());
            Pokemon second = pokemonClient.getPokemon(battle.getSecondPokemonId());
            Pokemon winner = pokemonClient.getPokemon(battle.getWinnerPokemonId());

            return PokeBattleResponse.builder()
                    .firstPokemon(first)
                    .secondPokeon(second)
                    .winner(winner)
                    .date(battle.getBattled())
                    .build();
        }).collect(Collectors.toList());
    }
}
