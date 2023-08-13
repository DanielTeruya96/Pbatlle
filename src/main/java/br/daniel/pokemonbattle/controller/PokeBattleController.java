package br.daniel.pokemonbattle.controller;


import br.daniel.pokemonbattle.dto.request.BattleRequest;
import br.daniel.pokemonbattle.dto.response.PokeBattleResponse;
import br.daniel.pokemonbattle.service.PokeBattleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/PokeBattle")
public class PokeBattleController {

    @Autowired
    private PokeBattleService service;

    @GetMapping("/generate")
    public ResponseEntity<PokeBattleResponse> generate(){
        PokeBattleResponse response = service.generate();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/battle")
    public ResponseEntity<PokeBattleResponse> battle(@RequestBody BattleRequest request){
        PokeBattleResponse response = service.battle(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
