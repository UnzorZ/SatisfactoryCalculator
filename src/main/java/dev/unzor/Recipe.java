package dev.unzor;

public class Recipe {
    int costeDeHierro;
    int costeDeCobre;
    int costeDeCaterio;

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

    int costeTotalDeHierro;
    int costeTotalDeCobre;
    int costeTotalDeCaterio;
    int costeTotalDeCaliza;
    int costeTotalDeCarbon;
    int CosteTotalDeCuarzo;
    int costeTotalDeBauxita;
    int costeTotalDePetroleo;
    public Recipe() {
        if (Constants.debug)
            Util.printDebug("Recipe created");

        this.costeAlambre = 0;



    }

    public int getCosteDeHierro() {
        return costeDeHierro;
    }

    public void setCosteDeHierro(int costeDeHierro) {
        this.costeDeHierro = costeDeHierro;
    }

    public int getCosteDeCobre() {
        return costeDeCobre;
    }

    public void setCosteDeCobre(int costeDeCobre) {
        this.costeDeCobre = costeDeCobre;
    }

    public int getCosteDeCaterio() {
        return costeDeCaterio;
    }

    public void setCosteDeCaterio(int costeDeCaterio) {
        this.costeDeCaterio = costeDeCaterio;
    }
}
