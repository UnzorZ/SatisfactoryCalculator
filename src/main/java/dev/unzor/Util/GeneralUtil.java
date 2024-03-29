package dev.unzor.Util;

import dev.unzor.Constants;
import dev.unzor.Deprecated.GUI_old;
import dev.unzor.Objects.Recipe;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GeneralUtil {
    static JComboBox<String> comboBox = GUI_old.materialComboBox;

    /**
     * @author Unzor
     * @method arrayToStringConverter
     * @param menas: Array de strings
     * @description: Convierte un array de Strings en un String
     */
    public static void arrayToStringConverter(ArrayList<String> menas) {
        String[] string = menas.toArray(new String[menas.size()]);

        for (int i=0; i<menas.size(); i++) {
            string[i] = menas.get(i);
            comboBox.addItem(string[i]);
        }
    }

    /**
     * @author Unzor
     * @method: printDebug
     * @param message: Mensaje a imprimir
     * @description: Imprime un mensaje de debug si la variable debug es true
     */
    public static void printDebug(String message) {
        if (Constants.debug) {
            System.out.println("[+] DEBUG: " + message);
        }
    }

    /**
     * @author Unzor
     * @method: addStuffToTable
     * @param tabla: tabla a la que se le va a añadir la fila
     * @param mena: mena a añadir
     * @param cantidad: cantidad de mena a añadir
     * @param tier: tier de la mena a añadir
     * @param overclock: overclock del minero a añadir
     * @description: añade una fila a la tabla de menas
     */
    public static void addStuffToTable(JTable tabla, String mena, String cantidad, String tier, String overclock) {
        // Obtener el modelo de datos de la tabla
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();

        // Crear una nueva fila de datos
        Object[] rowData = {mena, cantidad, tier, overclock};

        // Agregar la nueva fila al modelo de datos
        model.addRow(rowData);
    }

    /**
     * @author Unzor
     * @method: printTableContent
     * @param table: tabla de la que se va a imprimir el contenido
     * @param tablerowtoprint: fila de la que se va a imprimir el contenido
     * @description: Imprime el contenido de la tabla
     */
    public static void printTableContent(JTable table, int tablerowtoprint) {
        // Este string builder se usa para almacenar el contenido de la tabla en el final del metodo
        // donde se enseña un ejemplo de como se puede usar una fila de tabla concreta
        StringBuilder sb = new StringBuilder();

        // Primero, obtenemos el TableModel de la JTable
        TableModel model = table.getModel();

        // Obtenemos el número de filas y columnas en la tabla
        int numRows = model.getRowCount();
        int numColumns = model.getColumnCount();

        // Creamos una lista para almacenar los valores de cada fila
        List<List<Object>> rows = new ArrayList<>();

        // Creamos un bucle anidado para recorrer cada fila y columna
        for (int i = 0; i < numRows; i++) {
            // Creamos una lista para almacenar los valores de la fila actual
            List<Object> row = new ArrayList<>();

            for (int j = 0; j < numColumns; j++) {
                // Usamos el método getValueAt() del TableModel para obtener
                // el valor de la celda en la fila i y columna j
                Object value = model.getValueAt(i, j);

                // Agregamos el valor a la lista de la fila actual
                row.add(value);
            }

            // Agregamos la lista de la fila actual a la lista de todas las filas
            rows.add(row);
        }
        /**
        * Ahora puedes acceder a los valores de cada fila usando la lista rows.
        * Por ejemplo, para imprimir los valores de la primera fila en consola, puedes usar esto, donde
        * @param tablerow es la fila que quieres imprimir
        **/

        GeneralUtil.printDebug("Imprimiendo fila " + tablerowtoprint);

        if (tablerowtoprint > rows.size()-1) {
            GeneralUtil.printDebug("La fila que quieres imprimir no existe");
            return;
        }

        List<Object> firstRow = rows.get(tablerowtoprint);
        for (Object value : firstRow) {
            sb.append(value + " ");
        }
        GeneralUtil.printDebug("Primera fila: " + sb);
    }

    /**
     * @author Unzor
     * @method: compareTableContent
     * @param table: tabla de la que se va a comparar el contenido
     * @description: Comprara cada fila de la tabla para comprobar que no haya filas repetidas. Si las hay, las eliminará y sumará la cantidad
     * de la fila repetida a la fila original para que no haya filas repetidas
     */
    public static void compareTableContent(JTable table) {
        //Obtener el modelo de datos de la tabla
        TableModel model = table.getModel();

        //Crear una lista para guardar las filas
        List<Object[]> rows = new ArrayList<>();

        //Iterar sobre cada fila y guardarla en la lista
        for (int row = 0; row < model.getRowCount(); row++) {
            Object[] currentRow = new Object[model.getColumnCount()];
            for (int column = 0; column < model.getColumnCount(); column++) {
                currentRow[column] = model.getValueAt(row, column);
            }
            rows.add(currentRow);
        }

        //Comparar cada fila con las demás
        for (int i = 0; i < rows.size(); i++) {
            for (int j = i + 1; j < rows.size(); j++) {
                boolean rowsAreEqual = true;

                //Comparar cada celda en ambas filas
                for (int k = 0; k < model.getColumnCount(); k++) {
                    if (!rows.get(i)[k].equals(rows.get(j)[k])) {
                        rowsAreEqual = false;
                        break;
                    }
                }

                //Si las filas son iguales, imprimir un mensaje por consola. Hay que tener en cuenta que
                //en java las filas empiezan en 0 para poder localizarlas en la tabla con el metodo printTableContent()
                if (rowsAreEqual) {
                    printDebug("Las filas " + i + " y " + j + " son iguales");
                    //Remover la fila duplicada
                    ((DefaultTableModel) model).removeRow(j);
                    //sumarle el numero que valga a la variable cantidad de la fila que se mantiene. El index de la columna es uno ya que
                    //se empieza a contar desde 0 y la posicion es la segunda.
                    int currentValue = stringToInt(model.getValueAt(i, 1).toString());
                    int newValue = currentValue + stringToInt(model.getValueAt(i, 1).toString());

                    // Establecer el nuevo valor en la celda
                    model.setValueAt(newValue, i, 1);
                }
            }
        }
    }

    /**
     * @author Unzor
     * @method: stringToInt
     * @param string: string que se va a convertir a int
     * @return int: devuelve el string convertido a int
     * @description: Convierte un string a int
     */
    public static int stringToInt(String string) {
        int num = Integer.parseInt(string);
        return num;
    }

    /**
     @author Unzor
     @method printIngredients
     @param hashMap: un objeto de tipo Map que contiene los ingredientes y sus cantidades
     @description Este método imprime una tabla con los ingredientes y sus cantidades recibidos como parámetro.
     La tabla se muestra en formato de tabla y utiliza caracteres especiales para hacerla más legible.
     La tabla se muestra en orden alfabético y se ordena por el nombre de los ingredientes. */
    public static void printIngredients(Map<String, Integer> hashMap) {
        System.out.println("Tabla de ingredientes");
        System.out.println("+--------------------------+---------+");
        System.out.println("|          Nombre          | Cantidad|");
        System.out.println("+--------------------------+---------+");

        for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
            String nombre = entry.getKey();
            int cantidad = entry.getValue();
            System.out.printf("| %-24s | %7d |\n", nombre, cantidad);
        }

        System.out.println("+--------------------------+---------+");
    }

    /**
     @author Unzor
     @method printHashMapTable
     @param hashMap: un objeto de tipo Map que contiene las recetas y sus claves
     @description Este método imprime una tabla con las recetas y sus claves recibidas como parámetro.
     La tabla se muestra en formato de tabla y utiliza caracteres especiales para hacerla más legible.
     La tabla se muestra en orden alfabético y se ordena por la clave de las recetas. */
    public static void printHashMapTable(Map<String, Recipe> hashMap) {
        System.out.println("+---------------------+---------+");
        System.out.println("|        Clave        |  Valor  |");
        System.out.println("+---------------------+---------+");

        for (Map.Entry<String, Recipe> entry : hashMap.entrySet()) {
            String key = entry.getKey();
            Recipe value = entry.getValue();
            System.out.printf("| %-19s | %7d |\n", key, value);
        }

        System.out.println("+---------------------+---------+");
    }
}
