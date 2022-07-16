package com.ainnov.sqlite2;

public class UserModel {
    private int id;
    private String nume;
    private int varsta;
    private boolean isActive;

    public UserModel(int id, String nume, int varsta, boolean isActive) {
        this.id = id;
        this.nume = nume;
        this.varsta = varsta;
        this.isActive = isActive;
    }

    public UserModel(){

    }

    @Override
    public String toString() {
        return "Nume: " + nume + '\'' +
                " Varsta: " + varsta +
                " Activ: " + isActive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getVarsta() {
        return varsta;
    }

    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
