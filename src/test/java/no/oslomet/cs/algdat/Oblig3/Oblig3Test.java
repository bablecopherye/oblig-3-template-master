package no.oslomet.cs.algdat.Oblig3;


////// Testprogram for mappeeksamen ////////////////////////

import java.util.Comparator;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Oblig3Test {

    // OPPGAVE 1 ////////////////////////////////////////////////
    @org.junit.jupiter.api.Test
    void oppgave1() {
        int antallFeil = 0;
        SBinTre<Integer> tre =
                new SBinTre<>(Comparator.naturalOrder());

        try {
            if (tre.antall() != 0) {
                antallFeil++;
                System.out.println("Oppgave 1a: Feil antall i et tomt tre!");
            }
        } catch (Exception e) {
            antallFeil++;
            System.out.println
                    ("Oppgave 1b: Skal ikke kaste unntak for et tomt tre");
        }

        tre.leggInn(1);
        tre.leggInn(2);
        tre.leggInn(3);

        if (tre.antall() != 3) {
            antallFeil++;
            System.out.println
                    ("Oppgave 1c: Antall blir ikke oppdatert!");
        }

        assertEquals(antallFeil, 0);
    }  // slutt på Oppgave 1


    // OPPGAVE 2 ////////////////////////////////////////////////
    @org.junit.jupiter.api.Test
    void oppgave2() {
        SBinTre<Integer> tre =
                new SBinTre<>(Comparator.naturalOrder());

        int antallFeil = 0;

        tre.leggInn(1);

        try {
            if (tre.antall(1) != 1) {
                antallFeil++;
                System.out.println("Oppgave 2a: Feil antall(T)-metoden!");
            }
        } catch (Exception e) {
            antallFeil++;
            System.out.println
                    ("Oppgave 2b: Skal ikke kaste unntak her!");
        }

        // Nytt tre
        tre = new SBinTre<>(Comparator.naturalOrder());
        int[] a = {1, 7, 1, 6, 1, 5, 1, 4, 1, 1, 1, 3};
        for (int verdi : a) tre.leggInn(verdi);

        if (tre.antall(7) != 1 || tre.antall(6) != 1
                || tre.antall(5) != 1 || tre.antall(4) != 1
                || tre.antall(3) != 1 || tre.antall(2) != 0
                || tre.antall(1) != 7 || tre.antall(0) != 0) {
            antallFeil++;
            System.out.println("Oppgave 2c: Feil antall(T)-metoden!");
        }


        assertEquals(antallFeil, 0);
    }  // slutt på Oppgave 2


    // OPPGAVE 3 ////////////////////////////////////////////////
    @org.junit.jupiter.api.Test
    void oppgave3() {
        int antallFeil = 0;
        SBinTre<Integer> tre =
                new SBinTre<>(Comparator.naturalOrder());

        String s;

        try {
            s = tre.toStringPostOrder();
            if (!s.equals("[]")) {
                antallFeil++;
                System.out.println("Oppgave 3a: Feil i toStringPostOrder() for et tomt tre!!");
            }
        } catch (Exception e) {
            antallFeil++;
            System.out.println
                    ("Oppgave 3b: Skal ikke kaste unntak for et tomt tre!");
        }

        // legger inn 10
        tre.leggInn(10);

        s = tre.toStringPostOrder();
        if (!s.equals("[10]")) {
            antallFeil++;
            System.out.println("Oppgave 3c: Feil i toStringPostOrder() for et tre med kun en verdi!");
        }

        // legger inn flere verdier
        int[] a = {6, 14, 1, 8, 12, 3, 7, 9, 11, 13, 2, 5, 4};
        for (int verdi : a) tre.leggInn(verdi);

        try {
            s = tre.toStringPostOrder();
            if (!s.equals("[2, 4, 5, 3, 1, 7, 9, 8, 6, 11, 13, 12, 14, 10]")) {
                antallFeil++;
                System.out.println("Oppgave 3d: Feil i toStringPostOrder()! Men feilen kan");
                System.out.println("ligge i leggInn() eller i nesteInorden().");
            }
        } catch (Exception e) {
            antallFeil++;
            System.out.println
                    ("Oppgave 3e: Her kastes et unntak! Det skal ikke skje!");
        }

        // Et nytt tre
        tre = new SBinTre<>(Comparator.naturalOrder());

        // legger inn 10 flere ganger
        for (int i = 0; i < 4; i++) tre.leggInn(10);

        s = tre.toStringPostOrder();
        if (!s.equals("[10, 10, 10, 10]")) {
            antallFeil++;
            System.out.println("Oppgave 3f: Feil i toStringPostOrder()! Men feilen kan");
            System.out.println("ligge i leggInn() eller i nesteInorden().");
        }

        // Et nytt tre
        tre = new SBinTre<>(Comparator.naturalOrder());

        int[] b = {5, 4, 3, 2, 1};
        for (int k : b) tre.leggInn(k);

        s = tre.toStringPostOrder();
        if (!s.equals("[1, 2, 3, 4, 5]")) {
            antallFeil++;
            System.out.println("Oppgave 3g: Feil i toStringPostOrder()! Men feilen kan");
            System.out.println("ligge i leggInn() eller i nesteInorden().");
        }
        assertEquals(antallFeil, 0);
    }  // slutt på Oppgave 3


    // OPPGAVE 4 ////////////////////////////////////////////////
    @org.junit.jupiter.api.Test
    void oppgave4() {
        SBinTre<Integer> tre =
                new SBinTre<>(Comparator.naturalOrder());

        // legger inn verdier i treet
        int[] a = {10, 6, 14, 1, 8, 12, 3, 7, 9, 11, 13, 2, 5, 4};
        for (int verdi : a) tre.leggInn(verdi);

        //Denne lambda-funksjonen skriver ut mellomrom før nodens verdi.
        AtomicReference<String> postorden = new AtomicReference<>();
        Oppgave<Integer> oppgave = c -> postorden.set(postorden.get() + " " + c.toString()) ;

        //Test at postorden fungerer
        postorden.set("");
        tre.postorden(oppgave);
        assertEquals(postorden.get(), " 2 4 5 3 1 7 9 8 6 11 13 12 14 10");

        //Test at rekursiv postorden fungerer
        postorden.set("");
        tre.postordenRecursive(oppgave);
        assertEquals(postorden.get(), " 2 4 5 3 1 7 9 8 6 11 13 12 14 10");
    }  // slutt på Oppgave 4



} // Oblig3Test
