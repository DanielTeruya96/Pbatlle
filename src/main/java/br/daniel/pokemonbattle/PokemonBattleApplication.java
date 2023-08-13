package br.daniel.pokemonbattle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

import static br.daniel.pokemonbattle.util.TypeMatch.preLoad;

@SpringBootApplication
public class PokemonBattleApplication {

    public static void main(String[] args) throws IOException {
        preLoad();
        SpringApplication.run(PokemonBattleApplication.class, args);
    }

}
