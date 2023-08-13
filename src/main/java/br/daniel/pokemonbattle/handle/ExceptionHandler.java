package br.daniel.pokemonbattle.handle;

import br.daniel.pokemonbattle.expection.PokemonApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    protected ResponseEntity<String> exceptionHandle(Exception e){
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("{\"Erro nao mapeado\" }");
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(PokemonApiException.class)
    protected ResponseEntity<String> exceptionHandle(PokemonApiException e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(e.getReason());
    }
}
