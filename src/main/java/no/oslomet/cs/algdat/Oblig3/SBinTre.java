package no.oslomet.cs.algdat.Oblig3;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import java.util.StringJoiner;

public class SBinTre<T> {

    public static void main (String[] args) {

        Integer[] a = {4,7,2,9,5,10,8,1,3,6};
        SBinTre<Integer> tre = new SBinTre<>(Comparator.naturalOrder());
        for (int verdi : a) {tre.leggInn(verdi); }
        //System.out.println(tre.antall());  // Utskrift: 10


        Integer[] a2 = {4,7,2,9,4,10,8,7,4,6};
        SBinTre<Integer> tre2 = new SBinTre<>(Comparator.naturalOrder());
        for (int verdi : a2) { tre2.leggInn(verdi); }

        System.out.println(tre.antall());      // Utskrift: 10
        System.out.println(tre.antall(5));     // Utskrift: 0
        System.out.println(tre.antall(4));     // Utskrift: 3
        System.out.println(tre.antall(7));     // Utskrift: 2
        System.out.println(tre.antall(10));    // Utskrift: 1
    }




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
    private int endringer;                          // antall endringer

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






    public boolean fjern(T verdi) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public int fjernAlle(T verdi) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }


/*
Metodene inneholder(), antall() og tom() er ferdig kodet. Den første avgjør om en verdi
ligger i treet eller ikke. De to andre fungerer på vanlig måte. Lag kode for metoden public
int antall(T verdi). Den skal returnere antall forekomster av verdi i treet. Det er tillatt
med  duplikater  og  det  betyr  at  en  verdi  kan  forekomme  flere  ganger.  Hvis verdi ikke  er  i
treet (null er ikke i treet), skal metoden returnere 0. Test koden din ved å lage trær der du
legger inn flere like verdier. Sjekk at metoden din da gir rett svar. Her er ett eksempel:
  Integer[] a = {4,7,2,9,4,10,8,7,4,6};
  SBinTre<Integer> tre = new SBinTre<>(Comparator.naturalOrder());
  for (int verdi : a) { tre.leggInn(verdi); }

  System.out.println(tre.antall());      // Utskrift: 10
  System.out.println(tre.antall(5));     // Utskrift: 0
  System.out.println(tre.antall(4));     // Utskrift: 3
  System.out.println(tre.antall(7));     // Utskrift: 2
  System.out.println(tre.antall(10));    // Utskrift: 1
 */


    public int antall(T verdi) {

        int antallForekomster = 0;

        if (tom()) {
            return 0;
        }

        else if (inneholder(verdi) == false) {
            return 0;
        }

        else {

            Node<T> node = rot;

            for (int i = 0; i < antall; i++) {

                if (verdi.equals(node.verdi))
                antallForekomster++;
            }
        }

        return antallForekomster;
    }

    public void nullstill() {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    private static <T> Node<T> førstePostorden(Node<T> p) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    private static <T> Node<T> nestePostorden(Node<T> p) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public void postorden(Oppgave<? super T> oppgave) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public void postordenRecursive(Oppgave<? super T> oppgave) {
        postordenRecursive(rot, oppgave);
    }

    private void postordenRecursive(Node<T> p, Oppgave<? super T> oppgave) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public ArrayList<T> serialize() {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    static <K> SBinTre<K> deserialize(ArrayList<K> data, Comparator<? super K> c) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }


} // ObligSBinTre
