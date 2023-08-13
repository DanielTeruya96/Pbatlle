package br.daniel.pokemonbattle.service;

import br.daniel.pokemonbattle.dto.response.BattleSearchResponse;
import br.daniel.pokemonbattle.dto.request.SearchBattleRequest;
import br.daniel.pokemonbattle.dto.response.PokeBattleResponse;
import br.daniel.pokemonbattle.mapper.BattleSearchResponseMapper;
import br.daniel.pokemonbattle.model.Battle;
import br.daniel.pokemonbattle.repository.BattleRepository;
import br.daniel.pokemonbattle.util.PokemonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SearchService {

    @Autowired
    private PokemonClient pokemonClient;

    @Autowired
    private BattleRepository repository;

    @Autowired
    private BattleSearchResponseMapper mapper;


    public BattleSearchResponse search(SearchBattleRequest request) {
        Page<Battle> battles;


        Pageable pageable = PageRequest.of(request.getPage()-1, request.getPageSize());

        if (request.isFull()) {
            battles = repository.findBattleByFirstPokemonIdOrSecondPokemonIdAndBattledIsBetween(
                    request.getPokemonId(),
                    request.getPokemonId(),
                    request.getStartDate(),
                    request.getEndDate(),
                    pageable
            );
        }
        else if (request.isOnlyDate()) {
            battles = repository.findByBattledBetween(
                    request.getStartDate(),
                    request.getEndDate(),
                    pageable);

        }
        else if(request.onlyPokemon()){
            battles = repository.findBattleByFirstPokemonIdOrSecondPokemonId(
                    request.getPokemonId(),
                    request.getPokemonId(),
                    pageable);
        }
        else{
            battles = repository.findAll(pageable);
        }


        List<PokeBattleResponse> list = mapper.map(battles.getContent());
        int allpages = battles.getTotalPages();

        return BattleSearchResponse.builder()
                .list(list)
                .page(request.getPage())
                .nextPage(Math.min(request.getPage() + 1, allpages))
                .lastPage(allpages)
                .totalElement(battles.getTotalElements())
                .build();

    }
}
