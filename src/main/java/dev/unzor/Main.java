package dev.unzor;

import com.formdev.flatlaf.FlatDarculaLaf;
import dev.unzor.GUI.CraftGUI;
import dev.unzor.JSON.JSONUtil;
import dev.unzor.Objects.Recipe;
import dev.unzor.Util.GeneralUtil;
import dev.unzor.Util.RecipeFilter;
import dev.unzor.Util.RecipeTablePrinter;

import java.awt.*;
import java.io.IOException;
import java.util.Map;

public class Main {

    static {
        FlatDarculaLaf.install();
    }

    public static void main(String[] args) throws IOException {
        Constants.debug = false;

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-debug":
                    Constants.debug = true;
                    GeneralUtil.printDebug("Debug mode enabled");
                    break;
            }
        }
        Map<String, Recipe> recipes = JSONUtil.getRecipeMap();

        Map<String, Integer> ingredients = recipes.get("alt-nuclear-fuel-rod").getIngredients();

        System.out.println(recipes.get("alt-nuclear-fuel-rod").toString());
        GeneralUtil.printIngredients(ingredients);

        RecipeTablePrinter.printRecipeTable(RecipeFilter.filterRecipesByName(recipes, "alt-nuclear-fuel-rod"));

        System.out.println("Starting...");
        //startGui();

        CraftGUI craftGUI = new CraftGUI();
        craftGUI.setVisible(true);



        //Recipe recipeObject = new Recipe();
        //recipeObject.setCosteDeLingotesAcero(1);
        //recipeObject.setCosteDeLingotesCobre(2);
        //recipeObject.setCosteDeLingotesHierro(3);
        //System.out.println(recipeObject.getCosteDeLingotesAcero());
        //System.out.println(recipeObject.getCosteDeLingotesCobre());
        //System.out.println(recipeObject.getCosteDeLingotesHierro());
    }

    /**
     * @author Unzor
     * @method startGui
     * @description Este método inicializa una interfaz gráfica de usuario (GUI) utilizando
     *              las dimensiones de la pantalla por defecto. Crea una instancia de la
     *              clase GUI y la hace visible en la pantalla en una ubicación específica.
     *              Esta función es responsable de mostrar la interfaz gráfica a los usuarios.
     */
    public static void startGui() {
        Toolkit mipantalla = Toolkit.getDefaultToolkit();
        Dimension tamanoPantalla = mipantalla.getScreenSize();

        int alturapantalla = tamanoPantalla.height;
        int anchurapantalla = tamanoPantalla.width;

        GUI_old gui = new GUI_old();
        gui.setBounds(anchurapantalla/4, alturapantalla/4, 1050, 650);
        gui.setVisible(true);
    }
}