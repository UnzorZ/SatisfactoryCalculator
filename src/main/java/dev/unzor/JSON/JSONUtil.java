package dev.unzor.JSON;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import dev.unzor.Constants;
import dev.unzor.Objects.Recipe;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class JSONUtil {
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
        String json = readJsonResource("recipesData.json");
        Map<String, Recipe> recipeMap = parseRecipes(json);

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
    public static Map<String, Recipe> parseRecipes(String json) throws IOException {
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

            Recipe recipe = new Recipe(name, keyName, ingredientsMap, productMap, getImage(keyName));
            recipeMap.put(keyName, recipe);
        }

        return recipeMap;
    }
    public static String getImage(String keyName) throws IOException {
        if (keyName.contains("alt-")) {
            keyName = keyName.replace("alt-", "");
        }
        String json = readJsonResource("itemsData.json");
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
        JsonArray itemsArray = jsonObject.getAsJsonArray("items");

        for (JsonElement image : itemsArray) {
            JsonObject itemObject = image.getAsJsonObject();
            String itemKeyName = itemObject.get("key_name").getAsString();
            if (itemKeyName.equals(keyName)) {
                return itemObject.get("image").getAsString();
            }
        }

        return Constants.errorImage; // Reemplaza con el valor correcto
    }

    public static String readJsonResource(String resourceName) {
        try {
            InputStream inputStream = JsonReader.class.getResourceAsStream("/" + resourceName);
            if (inputStream != null) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line);
                }
                return content.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
