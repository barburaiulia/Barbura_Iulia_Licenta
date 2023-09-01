package com.example.prorate;

public class Comms {
    private String creator;
    private String materie;
    private Float nota;
    private String descriere;

    public Comms(String creator, String materie, Float nota, String descriere) {
        this.creator = creator;
        this.materie = materie;
        this.nota = nota;
        this.descriere = descriere;
    }

    public Comms() {

    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getMaterie() {
        return materie;
    }

    public void setMaterie(String materie) {
        this.materie = materie;
    }

    public Float getNota() {
        return nota;
    }

    public void setNota(Float nota) {
        this.nota = nota;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }
}
