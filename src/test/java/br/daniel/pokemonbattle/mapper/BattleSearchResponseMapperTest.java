package br.daniel.pokemonbattle.mapper;

import br.daniel.pokemonbattle.dto.response.PokeBattleResponse;
import br.daniel.pokemonbattle.model.Battle;
import br.daniel.pokemonbattle.model.Pokemon;
import br.daniel.pokemonbattle.util.PokemonClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class BattleSearchResponseMapperTest {

    @Mock
    private PokemonClient pokemonClient;

    @InjectMocks
    private BattleSearchResponseMapper mapper;


    @Test
    void mapTest(){
        when(pokemonClient.getPokemon(anyInt())).thenReturn(new Pokemon());

        List<Battle> battles = new ArrayList<>();

        battles.add(createBattle());
        battles.add(createBattle());
        battles.add(createBattle());
        
        List<PokeBattleResponse> list = mapper.map(battles);
        Assertions.assertNotNull(list);
    }

    private Battle createBattle() {
        Random random = new Random();

        Battle battle = new Battle();
        battle.setBattled(LocalDateTime.now());
        battle.setId(random.nextInt());
        battle.setFirstPokemonId(random.nextInt());
        battle.setSecondPokemonId(random.nextInt());
        battle.setWinnerPokemonId(battle.getFirstPokemonId());
        return battle;
    }

}