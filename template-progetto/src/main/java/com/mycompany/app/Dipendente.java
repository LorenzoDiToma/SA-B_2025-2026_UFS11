package com.mycompany.app;

public class Dipendente {
    public enum ruoli {Frontend, Backend, PM};
    private ruoli ruolo;
    private String nome;
    private String cognome;
    private int  stipendioMensiole;


    public Dipendente(ruoli ruolo, String nome, String cognome, int stipendioMensiole) {
        this.ruolo = ruolo;
        this.nome = nome;
        this.cognome = cognome;
        this.stipendioMensiole = stipendioMensiole;
    }


    public ruoli getRuolo() {
        return ruolo;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public int getStipendioMensiole() {
        return stipendioMensiole;
    }

    public void setRuolo(ruoli ruolo) {
        this.ruolo = ruolo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setStipendioMensiole(int stipendioMensiole) {
        this.stipendioMensiole = stipendioMensiole;
    }


    @Override
    public String toString(){
        return nome + " " + cognome + ", " + ruolo + " - " + stipendioMensiole;
    }
}
