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

    //OPPGAVE 1 - gjør de endringene som trengs for at referansen forelder får korrekt verdi i hver node
    public boolean leggInn(T verdi) {

        //Kode hentet fra programkode 5.2.3 a) fra kompendiet - https://www.cs.hioa.no/~ulfu/appolonius/kap5/2/kap52.html#5.2.3
        Objects.requireNonNull(verdi, "Ulovlig med nullverdier!");

        Node<T> p = rot, q = null;               // p starter i roten
        int cmp = 0;                             // hjelpevariabel

        while (p != null) { // fortsetter til p er ute av treet
            q = p;                                 // q er forelder til p
            cmp = comp.compare(verdi,p.verdi);     // bruker komparatoren
            p = cmp < 0 ? p.venstre : p.høyre;     // flytter p
        }

        // p er nå null, dvs. ute av treet, q er den siste vi passerte

        p = new Node<>(verdi, q);                   // oppretter en ny node med konstruktøren og gir den verdi og forelder som input

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

    //OPPGAVE 2
    // skal returnere antall forekomster av verdi i treet. Det er tillatt med duplikater og det betyr at en verdi kan forekomme flere ganger.
    // Hvis verdi ikke er i treet (null er ikke i treet), skal metoden returnere 0.
    public int antall(T verdi) {
        //Gir feilmelding om det kommer inn en nullverdi
        Objects.requireNonNull(verdi, "Ulovlig med nullverdier!");

        //Kode hentet fra programkode 5.2.3 a) fra kompendiet - https://www.cs.hioa.no/~ulfu/appolonius/kap5/2/kap52.html#5.2.3
        Node<T> p = rot, q = null;               // p starter i roten
        int cmp = 0;                             // hjelpevariabel
        int antall = 0; //Variabel for å telle antall

        while (p != null) { // fortsetter til p er ute av treet
            q = p;                                 // q er forelder til p
            cmp = comp.compare(verdi,p.verdi);     // bruker komparatoren
            if (cmp == 0) { //Hvis verdien er lik p sin verdi (compare gir da 0)
                antall++;   //Øker antall
            }
            p = cmp < 0 ? p.venstre : p.høyre;     // flytter p
        }
        return antall;  //Returnerer antall
    }

    public void nullstill() {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    //OPPGAVE 3 - skal returnere første node post orden med p som rot
    private static <T> Node<T> førstePostorden(Node<T> p) {
        while (p.venstre != null) { //Så lenge p sin venstrepeker ikke er null så skal p være p.venstre
            p = p.venstre;
        }
        return p;   //Returnerer p
    }

    //OPPGAVE 3 - skal returnere den noden som kommer etter p i postorden. Hvis p er den siste i postorden, skal metoden returnere null
    private static <T> Node<T> nestePostorden(Node<T> p) {
        while(p.høyre != null) {
            p = p.høyre;
        }
        return p;
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
