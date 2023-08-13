package br.daniel.pokemonbattle.service;

import br.daniel.pokemonbattle.model.Pokemon;
import br.daniel.pokemonbattle.model.TypeList;
import br.daniel.pokemonbattle.util.TypeMatch;
import org.springframework.stereotype.Service;

@Service
public class FightService {

    public Pokemon fight(Pokemon first,Pokemon second) {

        Double firstPokemon = calculate(first, second);
        Double secondPokemon = calculate(second,first);

        if(firstPokemon > secondPokemon){
            System.out.println("winner points : "+firstPokemon);
            return first;
        }

        if(secondPokemon > firstPokemon){
            System.out.println("winner points : "+secondPokemon);
            return second;
        }

        return null;
    }

    private static Double calculate(Pokemon first, Pokemon second) {
        Double points =0.0;
        for(TypeList type1: first.getTypes()){
            for(TypeList type2: second.getTypes()){
                points += TypeMatch.match(type1.getType(), type2.getType());
            }
        }
        return points;
    }

}
