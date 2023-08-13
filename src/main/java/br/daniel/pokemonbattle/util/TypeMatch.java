package br.daniel.pokemonbattle.util;



import br.daniel.pokemonbattle.model.TypeClass;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import static java.util.Objects.isNull;


public class TypeMatch {

    private static Map<String, Map<String, Double>> types;

    public static void preLoad() throws IOException {
        if(isNull(types)){
            Path filePath = Paths.get("src\\main\\resources\\typeMath.json");
            byte[] fileBytes = Files.readAllBytes(filePath);
            String json = new String(fileBytes);

            // Create Gson object
            Gson gson = new Gson();

            // Define the type of the map
            Type type = new TypeToken<Map<String, Map<String, Double>>>() {}.getType();

            // Convert the JSON to a Map
            types = gson.fromJson(json, type);
        }
    }

    public static Double match(TypeClass first, TypeClass second){
        return types.get(first.getName()).get(second.getName());
    }

}
