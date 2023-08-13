package br.daniel.pokemonbattle.controller;

import br.daniel.pokemonbattle.dto.response.BattleSearchResponse;
import br.daniel.pokemonbattle.dto.request.SearchBattleRequest;
import br.daniel.pokemonbattle.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SearchService service;

    @GetMapping
    public ResponseEntity<BattleSearchResponse> search(@ModelAttribute SearchBattleRequest request){
        BattleSearchResponse response = service.search(request);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

}
