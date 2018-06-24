package com.example.jeux.remmeds.classes;

public class Prise {
    private String nomMedicament;
    private int compartiment;
    private String heurePrise;

    public Prise(String nomMedicament, int compartiment, String heurePrise) {
        this.nomMedicament = nomMedicament;
        this.compartiment = compartiment;
        this.heurePrise = heurePrise;
    }

    public Prise() {

    }

    public String getNomMedicament() {
        return nomMedicament;
    }

    public int getCompartiment() {
        return compartiment;
    }

    public String getHeurePrise() {
        return heurePrise;
    }

    public void setNomMedicament(String nomMedicament) {
        this.nomMedicament = nomMedicament;
    }

    public void setCompartiment(int compartiment) {
        this.compartiment = compartiment;
    }

    public void setHeurePrise(String heurePrise) {
        this.heurePrise = heurePrise;
    }
}
