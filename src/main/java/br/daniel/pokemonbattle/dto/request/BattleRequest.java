package br.daniel.pokemonbattle.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BattleRequest {

    private List<Integer> pokemonsId;
}
