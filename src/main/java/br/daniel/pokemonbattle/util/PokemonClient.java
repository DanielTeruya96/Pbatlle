package br.daniel.pokemonbattle.util;

import br.daniel.pokemonbattle.expection.PokemonApiException;
import br.daniel.pokemonbattle.model.Pokemon;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;



@Configuration
public class PokemonClient {

    private static final Logger logger = LoggerFactory.getLogger(PokemonClient.class);

    @Value("${pokemonapi.url}")
    private String url;

    public Pokemon getPokemon(int pokemonNumber) {

        try {
            HttpResponse<String> response = makeGetRequest(url + pokemonNumber);


            logger.debug(String.valueOf(response.statusCode()));

            ObjectMapper objectMapper = getObjectMapper();

            return objectMapper.readValue(response.body(), Pokemon.class);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new PokemonApiException("Fail to connect to Pokemon APi");
        }
    }

    private HttpResponse<String> makeGetRequest(String url) throws IOException, InterruptedException {
        logger.info("Request: "+url);

        HttpClient client = HttpClient.newBuilder()
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();


        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    private ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }
}
