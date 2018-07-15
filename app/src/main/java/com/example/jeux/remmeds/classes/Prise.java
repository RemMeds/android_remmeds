package com.example.jeux.remmeds.classes;

public class Prise {
    private String nommedicament;
    private int compartiment;
    private String heurePrise;
    private String datePrise;
    private String isTaken;
    private String plageHorraire;

    public Prise(String nomMedicament, int compartiment, String heurePrise, String isTaken){
        this.nommedicament = nomMedicament;
        this.compartiment = compartiment;
        this.heurePrise = heurePrise;
    }

    public Prise(String nomMedicament, int compartiment, String heurePrise, String datePrise, String plageHorraire, String isTaken){
        this.nommedicament = nomMedicament;
        this.compartiment = compartiment;
        this.heurePrise = heurePrise;
        this.datePrise = datePrise;
        this.plageHorraire = plageHorraire;
        this.isTaken = isTaken;
    }


    public String getPlageHorraire() {
        return plageHorraire;
    }

    public String getIsTaken() {

        return isTaken;
    }

    public void setIsTaken(String isTaken) {
        this.isTaken = isTaken;
    }

    public String getDatePrise() {
        return datePrise;
    }

    public String getNommedicament() {
        return nommedicament;
    }

    public int getCompartiment() {
        return compartiment;
    }

    public String getHeurePrise() {
        return heurePrise;
    }

}