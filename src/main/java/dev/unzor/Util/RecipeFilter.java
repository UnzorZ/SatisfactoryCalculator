package dev.unzor.Util;

import dev.unzor.Objects.Recipe;

import java.util.HashMap;
import java.util.Map;

public class RecipeFilter {

    /**
     * @author Unzor
     * @method filterRecipesByName
     * @param recipeMap: un objeto de tipo Map que contiene las recetas y sus claves
     * @param filterName: el nombre de la receta que se desea filtrar
     * @description Este método filtra las recetas de un Map de tipo Recipe según el nombre de la receta.
     * Para ello, se recorre cada elemento del Map y se comprueba si el nombre de la receta coincide con el filtro.
     * Si coincide, se agrega la receta al Map filtrado.
     * El Map filtrado contiene solo las recetas que coinciden con el filtro.
     */
    public static Map<String, Recipe> filterRecipesByName(Map<String, Recipe> recipeMap, String filterName) {
        Map<String, Recipe> filteredRecipes = new HashMap<>();

        for (Map.Entry<String, Recipe> entry : recipeMap.entrySet()) {
            String keyName = entry.getKey();
            Recipe recipe = entry.getValue();

            if (recipe.getKeyname().equals(filterName)) {
                filteredRecipes.put(keyName, recipe);
            }
        }

        return filteredRecipes;
    }
}
