package no.oslomet.cs.algdat.Oblig3;


import java.util.*;

public class SBinTre<T> {

    private static final class Node<T>   // en indre nodeklasse
    {
        private T verdi;                   // nodens verdi
        private Node<T> venstre, høyre;    // venstre og høyre barn
        private Node<T> forelder;          // forelder

        // konstruktør
        private Node(T verdi, Node<T> v, Node<T> h, Node<T> forelder) {
            this.verdi = verdi;
            venstre = v;
            høyre = h;
            this.forelder = forelder;
        }

        private Node(T verdi, Node<T> forelder)  // konstruktør
        {
            this(verdi, null, null, forelder);
        }

        @Override
        public String toString() {
            return "" + verdi;
        }

    } // class Node

    private Node<T> rot;                            // peker til rotnoden
    private int antall;                             // antall noder

    private final Comparator<? super T> comp;       // komparator

    public SBinTre(Comparator<? super T> c)    // konstruktør
    {
        rot = null;
        antall = 0;
        comp = c;
    }

    public boolean inneholder(T verdi) {
        if (verdi == null) return false;

        Node<T> p = rot;

        while (p != null) {
            int cmp = comp.compare(verdi, p.verdi);
            if (cmp < 0) p = p.venstre;
            else if (cmp > 0) p = p.høyre;
            else return true;
        }

        return false;
    }

    public int antall() {
        return antall;
    }

    public String toStringPostOrder() {
        if (tom()) return "[]";

        StringJoiner s = new StringJoiner(", ", "[", "]");

        Node<T> p = førstePostorden(rot); // går til den første i postorden
        while (p != null) {
            s.add(p.verdi.toString());
            p = nestePostorden(p);
        }

        return s.toString();
    }

    public boolean tom() {
        return antall == 0;
    }

    public boolean leggInn(T verdi) {

        // Kildekoden i denne metoden er kopiert inn fra kompendiet under Programkode 5.2.3 a).
        // Det er utført en liten endring i koden for å løse oppgave 1 i denne OBLIGEN.

        Objects.requireNonNull(verdi, "Ulovlig med nullverdier!");

        Node<T> p = rot, q = null;               // p starter i roten
        int cmp = 0;                             // hjelpevariabel

        while (p != null)       // fortsetter til p er ute av treet
        {
            q = p;                                 // q er forelder til p
            cmp = comp.compare(verdi,p.verdi);     // bruker komparatoren
            p = cmp < 0 ? p.venstre : p.høyre;     // flytter p
        }

        // p er nå null, dvs. ute av treet, q er den siste vi passerte

        p = new Node<>(verdi,q);                   // oppretter en ny node

        if (q == null) rot = p;                  // p blir rotnode
        else if (cmp < 0) q.venstre = p;         // venstre barn til q
        else q.høyre = p;                        // høyre barn til q

        antall++;                                // én verdi mer i treet
        return true;                             // vellykket innlegging
    }


    public int antall(T verdi) {

        // Deklarerer og initialiserer tellingen av antallet forekomster av verdien
        int antallForekomster = 0;

        // Hvis treet er tomt eller hvis verdien ikke finnes, så returneres 0
        if (tom() || !inneholder(verdi)) {
            return 0;
        }

        // Starter fra roten
        Node<T> node = rot;

        // Løper gjennom til det ikke er flere noder igjen
        while (node != null) {

            // Sammenlikner verdien til gjeldende node med verdien det søkes etter

            int sammenlikning = comp.compare(verdi, node.verdi);

            // Hvis en sammenlikning gir et negativt tall, så betyr det at verdiene er ulike, og det letes videre.
            if (sammenlikning < 0) {
                node = node.venstre;
            }

            else {
                // Hvis sammenlikningen viser 0, så betyr det at en forekomst er funnet og vi adderer antallet
                if (sammenlikning == 0) {
                    antallForekomster++;
                }

                node = node.høyre;
            }
        }

        // Returnerer antallet forekomster av verdien det ble søkt etter
        return antallForekomster;
    }


    private static <T> Node<T> førstePostorden(Node<T> p) {

        // Sjekker om treet er tomt
        if (p == null) {
            throw new NoSuchElementException("Det er ingenting i treet!");
        }

        while (true) {

            // Så lenge p sin venstre ikke viser null, betyr det at noden har et venstre subtre
            if (p.venstre != null) {

                // Beveger seg til venstre i subtreet
                p = p.venstre;
            }

            // Går til høyre hvis p sin høyre ikke viser null
            else if (p.høyre != null) {
                p = p.høyre;
            }

            // Returnerer p
            else {
                return p;
            }
        }
    }

    private static <T> Node<T> nestePostorden(Node<T> p) {

        // Sjekker om treet er tomt
        if (p == null) {
            throw new NoSuchElementException("Det er ingenting i treet!");
        }

        // Hvis p sin forelder er null, så er p automatisk null.
        else if (p.forelder == null) {
            p = null;
        }

        // Ellers forsetter p til neste
        else if (p == p.forelder.høyre) {
            p = p.forelder;
        }

        else if (p == p.forelder.venstre) {

            if (p.forelder.høyre == null) {
                p = p.forelder;
            }

            else
            p = førstePostorden(p.forelder.høyre);
        }

        // Returnerer p
        return p;
    }

    public void postorden(Oppgave<? super T> oppgave) {

        // Er roten null?
        if (rot == null) {
            return;
        }

        // Første verdi hentes og skrives ut
        Node<T> node1 = førstePostorden(rot);
        oppgave.utførOppgave(node1.verdi);

        // Neste verdi deklareres og initialiseres
        Node<T> node2 = nestePostorden(node1);

        // Løper gjennom og utfører oppgave
        while (node2!=null) {
            oppgave.utførOppgave(node2.verdi);
            node2 = nestePostorden(node2);
        }
    }

    public void postordenRecursive(Oppgave<? super T> oppgave) {
        postordenRecursive(rot, oppgave);
    }

    private void postordenRecursive(Node<T> p, Oppgave<? super T> oppgave) {

        // Er noden p null?
        if (p == null) {
            return;
        }

        if (p.venstre != null) {
            postordenRecursive(p.venstre,oppgave);
        }

        if (p.høyre != null){
            postordenRecursive(p.høyre,oppgave);
        }

        oppgave.utførOppgave(p.verdi);
    }

} // ObligSBinTre
