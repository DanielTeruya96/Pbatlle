package br.daniel.pokemonbattle.service;



import br.daniel.pokemonbattle.dto.request.BattleRequest;
import br.daniel.pokemonbattle.dto.response.PokeBattleResponse;
import br.daniel.pokemonbattle.expection.PokemonApiException;
import br.daniel.pokemonbattle.mapper.BattleMapper;
import br.daniel.pokemonbattle.model.Battle;
import br.daniel.pokemonbattle.model.Pokemon;
import br.daniel.pokemonbattle.repository.BattleRepository;
import br.daniel.pokemonbattle.util.PokemonClient;
import br.daniel.pokemonbattle.util.RandomUtil;
import br.daniel.pokemonbattle.util.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import static java.util.Objects.isNull;

@Service
public class PokeBattleService {

    @Autowired
    private PokemonClient pokemonClient;

    @Autowired
    private BattleRepository repository;

    @Autowired
    private FightService fightService;


    public PokeBattleResponse generate(){
        int firstPokemonNumber = RandomUtil.getRandonPokemonNumber();
        int secondPokemonNumber = RandomUtil.getRandonPokemonNumber();

        Pokemon firstPokemon = pokemonClient.getPokemon(firstPokemonNumber);
        Pokemon secondPokemon = pokemonClient.getPokemon(secondPokemonNumber);

       return battled(firstPokemon, secondPokemon);
    }

    private PokeBattleResponse battled(Pokemon firstPokemon, Pokemon secondPokemon) {
        Pokemon winner = fightService.fight(firstPokemon, secondPokemon);

        PokeBattleResponse response = PokeBattleResponse.builder()
                .firstPokemon(firstPokemon)
                .secondPokeon(secondPokemon)
                .date(LocalDateTime.now())
                .winner(winner)
                .build();

        Battle battle = BattleMapper.map(response);
        repository.save(battle);
        return response;
    }


    public PokeBattleResponse battle(BattleRequest request) {

        preBattle(request);

        Pokemon firstPokemon  = pokemonClient.getPokemon(request.getPokemonsId().get(0));
        Pokemon secondPokemon = pokemonClient.getPokemon(request.getPokemonsId().get(1));

        return battled(firstPokemon,secondPokemon);
    }

    private void preBattle(BattleRequest request) {
        List<Integer> pokemonId = request.getPokemonsId();

        if(isNull(pokemonId) || pokemonId.isEmpty()){
            throw new PokemonApiException("pokemonId is empty");
        }

        ValidationUtils.ValidPokemonId(pokemonId);

        if(pokemonId.size() == 1){
            pokemonId.add(RandomUtil.getRandonPokemonNumber());
        }


        while(pokemonId.size() > 2){
            Random random = new Random();
            int randomNumber = random.nextInt(pokemonId.size() - 1);
            pokemonId.remove(randomNumber);
        }

        request.setPokemonsId(pokemonId);
    }
}
