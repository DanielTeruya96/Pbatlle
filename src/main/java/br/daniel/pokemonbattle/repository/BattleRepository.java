package br.daniel.pokemonbattle.repository;

import br.daniel.pokemonbattle.model.Battle;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

@Repository
public interface BattleRepository extends JpaRepository<Battle, Integer> {

    Page<Battle> findBattleByFirstPokemonIdOrSecondPokemonIdAndBattledIsBetween
            (Integer firstPokemonId, Integer secondPokemonId, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);

    Page<Battle> findByBattledBetween(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);

    Page<Battle> findBattleByFirstPokemonIdOrSecondPokemonId(Integer firstPokemonId, Integer secondPokemonId, Pageable pageable);

}