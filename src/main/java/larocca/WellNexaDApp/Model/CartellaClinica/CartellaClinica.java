package larocca.WellNexaDApp.Model.CartellaClinica;

import java.util.ArrayList;

public class CartellaClinica {
    private String codiceFiscale;
    private ArrayList<Visita> visite;

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public ArrayList<Visita> getVisite() {
        return visite;
    }

    public void setVisite(ArrayList<Visita> visite) {
        this.visite = visite;
    }
}
