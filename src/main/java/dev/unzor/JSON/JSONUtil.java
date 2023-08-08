package dev.unzor.JSON;

import com.google.gson.*;
import dev.unzor.Objects.Recipe;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class JSONUtil {
    static Path configPath = Paths.get("src/main/resources/recipesData.json");
    static File configFile = configPath.toFile();

    /**
     * @author Unzor
     * @return Un mapa que contiene las recetas cargadas desde el archivo.
     * @throws IOException Si ocurre un error al leer el archivo de configuración.
     * @method getRecipeMap
     * @description Este método lee un archivo de configuración que contiene información
     *              sobre recetas y devuelve un mapa de recetas. Cada receta se representa
     *              como un objeto Recipe dentro del mapa, y la clave del mapa es el nombre
     *              de la receta. El archivo de configuración debe estar en un formato
     *              adecuado para ser procesado por el método parseRecipes.
     */
    public static Map<String, Recipe> getRecipeMap() throws IOException {
        InputStreamReader reader = new InputStreamReader(Files.newInputStream(configFile.toPath()));
        Map<String, Recipe> recipeMap = parseRecipes(reader);

        return recipeMap;
    }

    /**
     * @author Unzor
     * @method parseRecipes
     * @param json: un objeto de tipo InputStreamReader que contiene los datos de las recetas en formato JSON
     * @description Este método convierte los datos de las recetas en formato JSON a un objeto Map de tipo Recipe.
     * Para ello, se utiliza la librería Gson para convertir los datos JSON a un objeto de tipo JsonObject, y luego se recorre cada elemento de la matriz de recetas.
     * Para cada elemento, se obtienen los datos de la clave, el nombre, los ingredientes y el producto, y se crea un objeto Recipe con esos datos.
     * Finalmente, se agrega el objeto Recipe a un Map de tipo Recipe con la clave como clave y el objeto Recipe como valor.
     * El Map resultante contiene todas las recetas y sus claves.
     */
    static Map<String, Recipe> parseRecipes(InputStreamReader json) throws IOException {
        Map<String, Recipe> recipeMap = new HashMap<>();
        Gson gson = new Gson();

        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
        JsonArray recipesArray = jsonObject.getAsJsonArray("recipes");

        for (JsonElement recipeElement : recipesArray) {
            JsonObject recipeObject = recipeElement.getAsJsonObject();
            String keyName = recipeObject.get("key_name").getAsString();
            String name = recipeObject.get("name").getAsString();
            JsonArray ingredientsArray = recipeObject.getAsJsonArray("ingredients");

            Map<String, Integer> ingredientsMap = new HashMap<>();
            for (JsonElement ingredientElement : ingredientsArray) {
                JsonArray ingredientArray = ingredientElement.getAsJsonArray();
                String ingredientName = ingredientArray.get(0).getAsString();
                int ingredientAmount = ingredientArray.get(1).getAsInt();
                ingredientsMap.put(ingredientName, ingredientAmount);
            }

            Map<String, Integer> productMap = new HashMap<>();
            JsonArray jproductArray = recipeObject.getAsJsonArray("product");
            String productName = jproductArray.get(0).getAsString();
            int productAmount = jproductArray.get(1).getAsInt();
            productMap.put(productName, productAmount);

            Recipe recipe = new Recipe(name, keyName, ingredientsMap, productMap);
            recipeMap.put(keyName, recipe);
        }

        return recipeMap;
    }
}
