package dev.unzor.Util;

import dev.unzor.Objects.Recipe;

import java.util.Map;

public class RecipeTablePrinter {

    /**
     * @author Unzor
     * @method printRecipeTable
     * @param recipeMap: un objeto de tipo Map que contiene las recetas y sus claves
     * @description Este método imprime una tabla con las recetas y sus ingredientes recibidas como parámetro.
     * La tabla se muestra en formato de tabla y utiliza caracteres especiales para hacerla más legible.
     * Para cada receta, se muestra su clave, los ingredientes y el producto.
     * La tabla se muestra en orden alfabético y se ordena por la clave de las recetas.
     */
    public static void printRecipeTable(Map<String, Recipe> recipeMap) {
        System.out.println("+----------------------+-------------------------------------------------------------------------------------+");
        System.out.println("|        Key Name      |                                     Ingredientes                                    |");
        System.out.println("+----------------------+-------------------------------------------------------------------------------------+");

        for (Map.Entry<String, Recipe> entry : recipeMap.entrySet()) {
            String keyName = entry.getKey();
            Recipe recipe = entry.getValue();
            String ingredients = formatIngredients(recipe.getIngredients());

            System.out.printf("| %-19s | %-70s \n", keyName, ingredients);
            System.out.println("+----------------------+-------------------------------------------------------------------------------------+");
            System.out.println("| Product: " + formatProduct(recipe.getProduct()));
            System.out.println("+----------------------+-------------------------------------------------------------------------------------+");
        }
    }

    /**
     * @author Unzor
     * @method formatIngredients
     * @param ingredients: un objeto de tipo Map que contiene los ingredientes y sus cantidades
     * @description Este método convierte un Map de ingredientes y sus cantidades a una cadena de texto con formato.
     * Para ello, se recorre cada elemento del Map y se agrega el nombre del ingrediente y su cantidad a la cadena de texto.
     * La cadena de texto se muestra en formato "nombre: cantidad, nombre: cantidad,...".
     * Si el Map está vacío, se devuelve una cadena vacía.
     */
    static String formatIngredients(Map<String, Integer> ingredients) {
        StringBuilder formatted = new StringBuilder();
        for (Map.Entry<String, Integer> entry : ingredients.entrySet()) {
            formatted.append(entry.getKey()).append(": ").append(entry.getValue()).append(", ");
        }
        if (formatted.length() > 2) {
            formatted.delete(formatted.length() - 2, formatted.length()); // Eliminar la última coma y espacio
        }
        return formatted.toString();
    }
    /**
     * Formatea un mapa de producto en una cadena de texto.
     *
     * @author Unzor
     * @method formatProduct
     * @param product El mapa de producto a formatear.
     * @return Una cadena de texto con el formato "clave: valor, clave: valor, ...".
     * @description Este método toma un mapa de producto y lo convierte en una cadena
     *              de texto formateada, donde cada par clave-valor se muestra con
     *              el formato "clave: valor". La cadena resultante representa los
     *              ingredientes o productos de una receta en un formato legible.
     */
    static String formatProduct(Map<String, Integer> product) {
        StringBuilder formatted = new StringBuilder();
        for (Map.Entry<String, Integer> entry : product.entrySet()) {
            formatted.append(entry.getKey()).append(": ").append(entry.getValue()).append(", ");
        }
        if (formatted.length() > 2) {
            formatted.delete(formatted.length() - 2, formatted.length()); // Eliminar la última coma y espacio
        }
        return formatted.toString();
    }
}
