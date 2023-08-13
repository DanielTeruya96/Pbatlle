package br.daniel.pokemonbattle.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class BattleSearchResponse {

    List<PokeBattleResponse> list;

    private int page;
    private int nextPage;
    private int lastPage;

    private long totalElement;
}

