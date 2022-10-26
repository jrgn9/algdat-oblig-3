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

    //OPPGAVE 6
    //Der kan du kopiere Programkode 5.2 8 d), men i tillegg må du gjøre de endringene som trengs for at pekeren forelder får korrekt verdi i alle noder etter en fjerning.
/*    public boolean fjern(T verdi) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }*/

    //OPPGAVE 6
    //skal fjerne alle forekomstene av verdi i treet. Husk at duplikater er tillatt. Dermed kan en og samme verdi ligge flere steder i treet.
    // Metoden skal returnere antallet som ble fjernet. Hvis treet er tomt, skal 0 returneres
/*    public int fjernAlle(T verdi) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }*/

    //OPPGAVE 2
    // skal returnere antall forekomster av verdi i treet. Det er tillatt med duplikater og det betyr at en verdi kan forekomme flere ganger.
    // Hvis verdi ikke er i treet (null er ikke i treet), skal metoden returnere 0.
    public int antall(T verdi) {
        //Gir feilmelding om det kommer inn en nullverdi
        Objects.requireNonNull(verdi, "Ulovlig med nullverdier!");

        //Kode hentet fra programkode 5.2.3 a) fra kompendiet - https://www.cs.hioa.no/~ulfu/appolonius/kap5/2/kap52.html#5.2.3
        Node<T> p = rot;               // p starter i roten
        int cmp;                             // hjelpevariabel
        int antall = 0; //Variabel for å telle antall

        while (p != null) { // fortsetter til p er ute av treet
            cmp = comp.compare(verdi,p.verdi);     // bruker komparatoren
            if (cmp == 0) { //Hvis verdien er lik p sin verdi (compare gir da 0)
                antall++;   //Øker antall
            }
            p = cmp < 0 ? p.venstre : p.høyre;     // flytter p
        }
        return antall;  //Returnerer antall
    }

    //OPPGAVE 6
    //skal traversere (rekursivt eller iterativt) treet i en eller annen rekkefølge og sørge for at samtligepekere og nodeverdier i treet blir nullet.
    // Det er med andre ord ikke tilstrekkelig å sette rot til null og antall til 0.
/*    public void nullstill() {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }*/

    //OPPGAVE 3 - skal returnere første node post orden med p som rot
    private static <T> Node<T> førstePostorden(Node<T> p) {
        //Hentet fra Programkode 5.1.7 h) i kompendiet https://www.cs.hioa.no/~ulfu/appolonius/kap5/1/kap51.html#5.1.7
        while (true) {
            if (p.venstre != null) p = p.venstre;   //Sjekker først om det er et venstrebarn og setter p til å være dette
            else if (p.høyre != null) p = p.høyre;  //Hvis det ikke er et venstrebarn, men et høyrebarn så er det p sitt høyrebarn
            else return p;  //Hvis det ikke er noen barn så er p første postorden
        }
    }

    //OPPGAVE 3 - skal returnere den noden som kommer etter p i postorden. Hvis p er den siste i postorden, skal metoden returnere null
    private static <T> Node<T> nestePostorden(Node<T> p) {
        if (p.forelder == null) {   //Hvis p ikke har en forelder  så er p rotnoden og p er den siste i postorden. Oppgaven sier at dette skal returnere null
            return null;
        }
        else if (p == p.forelder.høyre) {   //Hvis p er høyre barn til sin forelder, er forelderen den neste
            return p.forelder;
        }
        else if (p.forelder.venstre == p) { //Hvis p er venstre barn til sin forelder
            if (p.forelder.høyre == null) { //Hvis p er enebarn så er forelderen den neste
                return p.forelder;
            }
            if (p.forelder.høyre != null) {
                return førstePostorden(p.forelder.høyre);   //Hvis p ikke er enebarn, så er den neste den noden som kommer først i postorden i subtreet med foreldren sin høyre som rot.
            }
        }
        return null;
    }

    //OPPGAVE 4
    //implementer funksjonen uten bruk av rekursjon og uten bruk av hjelpevariabler som stack / queue.
    //Du skal bruke funksjonen nestePostorden fra forrige oppgave. Start med å finne den første noden p i postorden.
    //Deretter vil (f.eks. i en while-løkke) setningen: p = nestePostorden(p); gi den neste. Osv. til p blir null.
    public void postorden(Oppgave<? super T> oppgave) {
        Node<T> r = rot;    //Lager en rotnode
        Node<T> p = førstePostorden(r); //Kaller på førstePostorden() på rot for å finne første postorden til node p

        while(p != null) {  //While-løkke som fortsetter så lenge p ikke er null (til rotnoden)
            oppgave.utførOppgave(p.verdi);  //Utfører oppgaven på p sin verdi for hver gang
            p = nestePostorden(p);  //Kaller p på nestePostorden() for å finne denne
        }

    }

    public void postordenRecursive(Oppgave<? super T> oppgave) {
        postordenRecursive(rot, oppgave);
    }

    //OPPGAVE 4
    //Lag et rekursivt kall som traverserer treet i postorden rekkefølge.
    private void postordenRecursive(Node<T> p, Oppgave<? super T> oppgave) {
        if (p.venstre != null) {    //Hvis p har et venstrebarn
            postordenRecursive(p.venstre, oppgave); //Kaller på metoden med venstrebarn og oppgave
        }
        if (p.høyre != null) {  //Hvis p har et høyrebarn (og ikke venstrebarn)
            postordenRecursive(p.høyre, oppgave);   //Kaller på metoden med høyrebarn og oppgave
        }
        oppgave.utførOppgave(p.verdi);  //Kaller på oppgave med verdien til p
    }

    //OPPGAVE 5
    //lag serialize som gjør om binærtreet til et array
    //Metoden serialize skal være iterativ og må bruke en kø til å traversere treet i nivå orden. Arrayet som returneres av serialize skal inneholde verdiene i alle nodene i nivå orden.
/*    public ArrayList<T> serialize() {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }*/

    //OPPGAVE 5
    //Lag deserialize som tar et array og gjør om til et binært søketre
    //skal da ta arrayet fra serialize, og legge inn alle verdiene (igjen i nivå orden), og dermed gjenskape treet
/*    static <K> SBinTre<K> deserialize(ArrayList<K> data, Comparator<? super K> c) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }*/


} // ObligSBinTre
