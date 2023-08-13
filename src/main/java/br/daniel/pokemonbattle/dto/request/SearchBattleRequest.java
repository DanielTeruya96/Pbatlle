package br.daniel.pokemonbattle.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SearchBattleRequest {

    private int page = 1;
    private int pageSize = 10;

    private Integer pokemonId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public boolean isFull() {
        return  pokemonId != null && startDate != null && endDate != null;
    }

    public boolean isOnlyDate() {
        return startDate != null && endDate != null;
    }

    public boolean onlyPokemon() {
        return  pokemonId != null && startDate == null && endDate == null;
    }
}
