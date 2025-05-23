# Obligatorisk oppgave 3 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 
Oppgaven er levert av følgende student:
* Jørgen Skontorp, S364516, s364516@oslomet.no

> ## ⚠️ Migrated to Codeberg
>
> This project has [migrated to Codeberg](https://codeberg.org/jrgn9/algdat-oblig-3).

# Oppgavebeskrivelse

**I oppgave 1** så kopierte jeg inn programkode 5.2.3 a) fra kompendiet. Deretter la jeg inn q som foreldrenoden i nodekonstruktøren.
Fordi som det står i kommentarene til koden, så er q forelderen til p i while-løkken.

**I oppgave 2** så la jeg inn delen av programkode 5.2.3 a) som traverserer gjennom treet. Så la jeg inn en if-setning for hvis compare gir 0,
dvs at de to som blir sammenliknet er like, så øker antall. Til slutt returneres antall.

**I oppgave 3** så hentet jeg først kode fra kompendiet for å finne første postorden. Jeg gjorde noen endringer i denne for å få den til å kjøre.
Deretter brukte jeg reglene for å finne neste postorden som står i kompendie som pseudokode. Utifra den så kodet jeg nestePostorden()

**I oppgave 4** så lagde jeg først en rotnode og kallet på førstePostorden() på denne for å finne første postorden.
Så lagde jeg en while-løkke som kalte utførOppgave() med verdien til den første noden i postorden.
Deretter kaller jeg nestePostorden() fra p-noden så lenge noden ikke er null.
postordenRecursive() lagde jeg ved at det sjekkes først om p har venstrebarn og i så fall kaller på seg selv med venstrebarn og oppgave.
Hvis p har et høyrebarn (og ikke venstre) så kaller den på seg selv med høyrebarn og oppgave. Så kaller den på oppgave med verdien til p

# Warnings #
- **Non-ASCII characters + Non-ASCII symbols in the ASCII word** 

    Fordi den utleverte koden bruker æøå

- **Field 'forelder' may be 'final'**

    Fordi den utleverte koden ikke har satt forelder som final. Så den blir som den blir

- **Private field 'endringer' is never used**

    Blir ikke brukt fordi jeg ikke har gjort oppg 5 og 6 enda

- **Method 'inneholder(T)' is never used**

    Jeg bruker ikke inneholder-metoden. 
    Enten fordi jeg ikke har skjønt at jeg kan bruke den i en oppgave eller fordi jeg ikke har gjort 5 og 6
  
-  **Return value of the method is never used**

    Brukes ikke, men har latt den stå bare for å returnere noe

- **Condition 'p.forelder.høyre != null' is always 'true'**

  Lar denne stå for å eksplisitt vise krav for neste postorden

- **Commented out code**

  Kommentert ut oppgave 5 og 6 frem til jeg har løst disse

# OBS: Kommentert ut test for oppgave 5 og 6 i main og laget egen branch for å jobbe med oppgave 5 og 6 #
