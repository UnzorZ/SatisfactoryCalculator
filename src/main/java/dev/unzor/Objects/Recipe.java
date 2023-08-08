package dev.unzor.Objects;

import java.util.Map;

public class Recipe {
    public String getName() {
        return name;
    }
    public String getKeyname() {
        return keyname;
    }

    public Map<String, Integer> getIngredients() {
        return ingredients;
    }

    public Map<String, Integer> getProduct() {
        return product;
    }

    private final String name;
    private final String keyname;
    private final Map<String, Integer> ingredients;
    private final Map<String, Integer> product;
    public Recipe(String name, String keyname, Map<String, Integer> ingredients, Map<String, Integer> product) {
        this.name = name;
        this.keyname = keyname;
        this.ingredients = ingredients;
        this.product = product;
    }

    public String toString() {
        return "Name: " + name +
                ", Keyname: " + keyname +
                ", Ingredients: " + ingredients +
                ", Product: " + product;
    }


}
