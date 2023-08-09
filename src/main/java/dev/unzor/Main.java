package dev.unzor;

import com.formdev.flatlaf.FlatDarculaLaf;
import dev.unzor.GUI.CraftGUI;
import dev.unzor.Util.GeneralUtil;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {

    static {
        //FlatMaterialDarkerIJTheme.setup();
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
        GeneralUtil.printDebug("Starting...");
        startGui();
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

        SwingUtilities.invokeLater(() -> {
            CraftGUI craftgui = new CraftGUI();
            craftgui.setBounds(anchurapantalla/4, alturapantalla/4, 1160, 650);
            craftgui.setVisible(true);
        });

    }
}