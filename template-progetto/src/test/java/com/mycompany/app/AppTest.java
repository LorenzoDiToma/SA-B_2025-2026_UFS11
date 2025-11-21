package com.mycompany.app;

import org.junit.Before;
import org.junit.Test;
import java.util.List;
import java.util.function.Predicate;
import static org.junit.Assert.*;

public class AppTest {
    private Azienda azienda;
    private Dipendente d1, d2, d3, d4;

    @Before
    public void setUp() {
        azienda = Azienda.getAzienda("TestIeO");
        azienda.getListaDipendenti().clear(); 

        d1 = new Dipendente(Dipendente.ruoli.Frontend, "Luca", "Rossi", 2500);
        d2 = new Dipendente(Dipendente.ruoli.Backend, "Maria", "Bianchi", 2700);
        d3 = new Dipendente(Dipendente.ruoli.PM, "Giovanni", "Verdi", 3500);
        d4 = new Dipendente(Dipendente.ruoli.Backend, "Emilio", "Branchione", 3500);

        azienda.aggiungiDipendente(d1);
        azienda.aggiungiDipendente(d2);
        azienda.aggiungiDipendente(d3);
        azienda.aggiungiDipendente(d4);
    }

    @Test
    public void testSingletonAzienda() {
        Azienda altraAzienda = Azienda.getAzienda("NuovoNome");
        assertSame("getAzienda dovrebbe restituire la stessa istanza (Singleton)", azienda, altraAzienda);
    }

    @Test
    public void testAggiungiDipendente() {
        Dipendente d5 = new Dipendente(Dipendente.ruoli.Frontend, "Anna", "Neri", 2600);
        azienda.aggiungiDipendente(d5);
        assertTrue("La lista dei dipendenti dovrebbe contenere il nuovo dipendente", azienda.getListaDipendenti().contains(d5));
        assertEquals("La dimensione della lista dovrebbe essere 5 dopo l'aggiunta", 5, azienda.getListaDipendenti().size());
    }

    @Test
    public void testFiltraDipendenti() {
        Predicate<Dipendente> soloBackend = dipendente -> dipendente.getRuolo() == Dipendente.ruoli.Backend;
        List<Dipendente> backendDevs = azienda.filtraDipendenti(soloBackend);
        assertEquals("Dovrebbero esserci 2 sviluppatori Backend", 2, backendDevs.size());
        assertTrue("La lista filtrata dovrebbe contenere d2", backendDevs.contains(d2));
        assertTrue("La lista filtrata dovrebbe contenere d4", backendDevs.contains(d4));
    }

    @Test
    public void testOrdinaPerRicchezza() {
        List<Dipendente> ordinati = azienda.ordinaPerRicchezza();
        assertEquals("Il primo dipendente dovrebbe essere d3 (o d4)", 3500, ordinati.get(0).getStipendioMensiole());
        assertEquals("Il secondo dipendente dovrebbe essere d4 (o d3)", 3500, ordinati.get(1).getStipendioMensiole());
        assertEquals("Il terzo dipendente dovrebbe essere d2", 2700, ordinati.get(2).getStipendioMensiole());
        assertEquals("Il quarto dipendente dovrebbe essere d1", 2500, ordinati.get(3).getStipendioMensiole());
    }

    @Test
    public void testGetDipendentePiuRicco() {
        List<Dipendente> piuRicchi = azienda.getDipendentePiuRicco();
        assertEquals("Ci dovrebbero essere 2 dipendenti più ricchi", 2, piuRicchi.size());
        assertTrue("La lista dei più ricchi dovrebbe contenere d3", piuRicchi.contains(d3));
        assertTrue("La lista dei più ricchi dovrebbe contenere d4", piuRicchi.contains(d4));
        assertEquals("Lo stipendio dei più ricchi dovrebbe essere 3500", 3500, piuRicchi.get(0).getStipendioMensiole());
    }
}