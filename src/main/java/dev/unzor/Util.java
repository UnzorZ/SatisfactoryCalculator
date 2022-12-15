package dev.unzor;

import javax.swing.*;
import java.util.ArrayList;

public class Util {
    static JComboBox<String> comboBox = GUI.materialComboBox;


    public static void arrayToStringConverter(ArrayList<String> menas) {
        String[] string = menas.toArray(new String[menas.size()]);

        for (int i=0; i<menas.size(); i++) {
            string[i] = menas.get(i);
            comboBox.addItem(string[i]);
        }
    }
    public static void printDebug(String message) {
        if (Constants.debug) {
            System.out.println("[+] DEBUG: " + message);
        }
    }
}
