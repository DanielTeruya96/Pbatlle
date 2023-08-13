package br.daniel.pokemonbattle.util;

import java.util.Random;

public class RandomUtil {

    static Random random = new Random();

    public static int getRandonPokemonNumber() {
        return random.nextInt((1008 - 1) + 1) + 1;
    }

}
