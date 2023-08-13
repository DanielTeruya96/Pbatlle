package br.daniel.pokemonbattle.service;



import br.daniel.pokemonbattle.dto.request.BattleRequest;
import br.daniel.pokemonbattle.dto.response.PokeBattleResponse;
import br.daniel.pokemonbattle.expection.PokemonApiException;
import br.daniel.pokemonbattle.model.Battle;
import br.daniel.pokemonbattle.model.Pokemon;
import br.daniel.pokemonbattle.repository.BattleRepository;
import br.daniel.pokemonbattle.util.PokemonClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class PokeBattleServiceTest {

    @InjectMocks
    PokeBattleService service;

    @Mock
    PokemonClient client;

    @Mock
    private BattleRepository repository;

    @BeforeEach
    void before(){
        when(client.getPokemon(anyInt())).thenReturn(new Pokemon());
        when(repository.save(any())).thenReturn(new Battle());
    }

    @Test
    void generate(){
        battledTest(service.generate());
    }

    @Test
    void battle(){
        BattleRequest request = new BattleRequest();
        request.setPokemonsId(new ArrayList<>(List.of(1,2,3)));

        battledTest(service.battle(request));
    }

    @Test
    void battledOnePokemon(){
        BattleRequest request = new BattleRequest();
        request.setPokemonsId(new ArrayList<>(List.of(1)));

        battledTest(service.battle(request));
    }

    @Test
    void emptyList(){
        assertThrows(PokemonApiException.class, ()->{
            BattleRequest request = new BattleRequest();
            service.battle(request);
        });
    }

    private void battledTest(PokeBattleResponse battleResponse) {

        Pokemon firstPokemon = battleResponse.getFirstPokemon();
        Pokemon secondPokemon = battleResponse.getSecondPokeon();
        Pokemon winner = battleResponse.getWinner();

        assertNotNull(battleResponse);
        assertNotNull(battleResponse.getFirstPokemon());
        assertNotNull(battleResponse.getSecondPokeon());
        assertNotNull(battleResponse.getWinner());
        assertNotNull(battleResponse.getDate());

        assertTrue((firstPokemon.equals(winner) || (secondPokemon.equals(winner))));
    }


}