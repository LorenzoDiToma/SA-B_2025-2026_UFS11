package com.mycompany.app;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Azienda {
    static Azienda azienda;
    private String nomeAzienda;
    private ArrayList<Dipendente> listaDipendenti;

    private Azienda(String nomeAzienda) {
        this.nomeAzienda = nomeAzienda;
        this.listaDipendenti = new ArrayList<>();
    }

    public static Azienda getAzienda(String nomeAzienda) {
        if(azienda == null) {
            azienda = new Azienda(nomeAzienda);
        }

        return azienda;
    }

    public void aggiungiDipendente(Dipendente dipendente) {
        listaDipendenti.add(dipendente);
    }

    public List<Dipendente> getListaDipendenti() {
        return listaDipendenti;
    }

    public List<Dipendente> filtraDipendenti(Predicate<Dipendente> filtro) {
        return listaDipendenti.stream().filter(filtro).toList();
    }

    public List<Dipendente> ordinaPerRicchezza() {
        return listaDipendenti.stream().sorted((dipendente1, dipendente2) ->  Integer.compare(dipendente1.getStipendioMensiole(), dipendente2.getStipendioMensiole()) * -1).toList();
    }

    public List<Dipendente> getDipendentePiuRicco() {
        int maxStipendio = ordinaPerRicchezza().get(0).getStipendioMensiole();
        return ordinaPerRicchezza().stream().filter(dipendente -> dipendente.getStipendioMensiole() == maxStipendio).toList();
    }
}
