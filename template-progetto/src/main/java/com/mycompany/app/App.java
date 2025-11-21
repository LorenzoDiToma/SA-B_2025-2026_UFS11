package com.mycompany.app;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class App
{
    public static void main( String[] args )
    {
        Dipendente d1 = new Dipendente(Dipendente.ruoli.Frontend, "Luca", "Rossi", 2500);
        Dipendente d2 = new Dipendente(Dipendente.ruoli.Backend, "Maria", "Bianchi", 2700);
        Dipendente d3 = new Dipendente(Dipendente.ruoli.PM, "Giovanni", "Verdi", 3500);
        Dipendente d4 = new Dipendente(Dipendente.ruoli.Backend, "Emilio", "Branchione", 3500);

        Azienda a =  Azienda.getAzienda("IeO");
        a.aggiungiDipendente(d1);
        a.aggiungiDipendente(d2);
        a.aggiungiDipendente(d3);
        a.aggiungiDipendente(d4);

        System.out.println("\n");
        Predicate<Dipendente> allFrontEnd = dipendente -> dipendente.getRuolo() == Dipendente.ruoli.Frontend;

        a.filtraDipendenti(allFrontEnd).forEach(dipendente -> System.out.println(dipendente.toString()));
        System.out.println("\n\n=============================== ORDINATI PER RICCHEZZA\n");
        a.ordinaPerRicchezza().forEach(dipendente -> System.out.println(dipendente.toString()));

        System.out.println("\n\n=============================== DIPENDENTE PIU RICCO\n");
        a.getDipendentePiuRicco().forEach(dipendente -> System.out.println(dipendente.toString()));


        System.out.println("\n\n=============================== NOMI E COGNOMI\n");
        a.getListaDipendenti().stream().map(dipendente -> dipendente.getCognome() + " " + dipendente.getNome()).forEach(System.out::println);

        System.out.println("\n\n=============================== RAGGRUPPATI PER RUOLO\n");
        Map<Dipendente.ruoli, List<String>> groupPerRuoli =  a.getListaDipendenti().stream()
                .collect(
                        Collectors.groupingBy(
                                Dipendente::getRuolo,
                                Collectors.mapping(Dipendente::getCognome, Collectors.toList())
                        )
                );
        groupPerRuoli.forEach((k, v) -> System.out.println(k + " - " + v));
    }
}
