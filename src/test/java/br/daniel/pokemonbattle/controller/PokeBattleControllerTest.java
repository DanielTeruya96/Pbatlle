package br.daniel.pokemonbattle.controller;



import br.daniel.pokemonbattle.dto.request.BattleRequest;
import br.daniel.pokemonbattle.dto.response.PokeBattleResponse;
import br.daniel.pokemonbattle.service.PokeBattleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class PokeBattleControllerTest {

    @InjectMocks
    PokeBattleController controller;

    @Mock
    private PokeBattleService service;

    @Test
    void generate(){
        when(this.service.generate()).thenReturn(PokeBattleResponse.builder().build());
        ResponseEntity<PokeBattleResponse> entity = controller.generate();
        Assertions.assertNotNull(entity);
    }

    @Test
    void battle() {
        when(this.service.battle(any())).thenReturn(PokeBattleResponse.builder().build());

        ResponseEntity<PokeBattleResponse> entity = controller.battle(new BattleRequest());
        Assertions.assertNotNull(entity);
    }
}