package dev.unzor.Deprecated;

import dev.unzor.Constants;
import dev.unzor.Util.GeneralUtil;

public class Recipe {
    /**
     * Se aclara que el numero de coste es el numero de unidades de producto que se necesita por minuto
     */

    int costeDeLingotesHierro;
    int costeDeLingotesCobre;
    int costeDeLingotesDeAcero;

    int costePlanchasDeHierro;
    int costeBarrasDeHierro;
    int costeTornillos;
    int costeLaminasDeCobre;
    int costeVigasDeAcero;
    int costeTuberiasDeAcero;
    int costeCarcasasDeAluminio;
    int costeAlambre;
    int costeCables;
    int costeTurboalambre;
    int costeHormigon;
    int costeCristalDeCuarzo;
    int costeSilice;
    int costePlanchasDeHierroReforzadas;
    int costeEstructurasModulares;
    int costeVigasIndustriales;
    int costeRotores;
    int costeEstatores;
    int costeMotores;
    int costeLimitadoresDeIA;
    int costeCircuitosImpresos;
    int costeOrdenadores;
    int costePlastico;
    int costeGoma;

    double costeTotalDeHierro;
    double costeTotalDeCobre;
    double costeTotalDeCaterio;
    double costeTotalDeCaliza;
    double costeTotalDeCarbon;
    double CosteTotalDeCuarzo;
    double costeTotalDeBauxita;
    double costeTotalDePetroleo;

    int produccionPorMinuto;

    public Recipe() {
        if (Constants.debug)
            GeneralUtil.printDebug("Recipe created");
        setAllDefault(0);
    }

    /**
     * @author: Unzor
     * @method: setAllDefault
     * @param i: numero a asignar
     * Establece todas las variables al parametro i
     */

    public void setAllDefault(int i) {
        costeDeLingotesHierro = i;
        costeDeLingotesCobre = i;
        costeDeLingotesDeAcero = i;
        costePlanchasDeHierro = i;
        costeBarrasDeHierro = i;
        costeTornillos = i;
        costeLaminasDeCobre = i;
        costeVigasDeAcero = i;
        costeTuberiasDeAcero = i;
        costeCarcasasDeAluminio = i;
        costeAlambre = i;
        costeCables = i;
        costeTurboalambre = i;
        costeHormigon = i;
        costeCristalDeCuarzo = i;
        costeSilice = i;
        costePlanchasDeHierroReforzadas = i;
        costeEstructurasModulares = i;
        costeVigasIndustriales = i;
        costeRotores = i;
        costeEstatores = i;
        costeMotores = i;
        costeLimitadoresDeIA = i;
        costeCircuitosImpresos = i;
        costeOrdenadores = i;
        costePlastico = i;
        costeGoma = i;
        costeTotalDeHierro = i;
        costeTotalDeCobre = i;
        costeTotalDeCaterio = i;
        costeTotalDeCaliza = i;
        costeTotalDeCarbon = i;
        CosteTotalDeCuarzo = i;
        costeTotalDeBauxita = i;
        costeTotalDePetroleo = i;
    }

    public double costeHierro() {
        double total;
        /**
         * En este metodo se calcula el coste de hierro que se necesita para producir el producto.
         * El coste de hierro se calcula de la siguiente manera:
         * <coste del material>/<produccion por minuto>/<coste de hierro por minuto> = coste de hierro
         */
        costeTotalDeHierro = costeDeLingotesHierro +
                (costePlanchasDeHierro/20*30) +
                (costeBarrasDeHierro/15*15) +
                (costeTornillos) + 0;

        total = costeTotalDeHierro;
        return total;


    }


    public int getCosteDeLingotesHierro() {
        return costeDeLingotesHierro;
    }

    public void setCosteDeLingotesHierro(int costeDeHierro) {
        this.costeDeLingotesHierro = costeDeHierro;
    }

    public int getCosteDeLingotesCobre() {
        return costeDeLingotesCobre;
    }

    public void setCosteDeLingotesCobre(int costeDeCobre) {
        this.costeDeLingotesCobre = costeDeCobre;
    }

    public int getCosteDeLingotesAcero() {
        return costeDeLingotesDeAcero;
    }

    public void setCosteDeLingotesAcero(int costeDeCaterio) {
        this.costeDeLingotesDeAcero = costeDeCaterio;
    }
}
