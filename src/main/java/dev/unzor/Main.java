package dev.unzor;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        Constants.debug = false;

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-debug":
                    Constants.debug = true;
                    Util.printDebug("Debug mode enabled");
                    break;
            }
        }

        System.out.println("Starting...");
        startGui();
    }

    public static void startGui() {
        Toolkit mipantalla = Toolkit.getDefaultToolkit();
        Dimension tamanoPantalla = mipantalla.getScreenSize();

        int alturapantalla = tamanoPantalla.height;
        int anchurapantalla = tamanoPantalla.width;

        GUI gui = new GUI();
        gui.setBounds(anchurapantalla/4, alturapantalla/4, 1050, 650);
        gui.setVisible(true);
    }
}