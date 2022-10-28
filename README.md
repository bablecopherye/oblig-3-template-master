# Obligatorisk oppgave 3 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 
Oppgaven er levert av følgende student:
* Alexander Hagen Huse, s354599, s354599@oslomet.no


# Oppgavebeskrivelse

I oppgave 1 så gikk jeg frem ved å først kopiere kildekode fra kompendiet under Programkode 5.2.3 a). 
Deretter utførte jeg en liten endring, som innebar å legge til verdien i variabelen `q` 
når ny node opprettes, for å angi foreldrenoden.

I oppgave 2 så startet jeg med å sjekke om treet var tomt eller ikke innholdt
verdien det letes etter i utgangspunktet ved å kalle på metodene `tom()` og
`inneholder()`. Så startet letingen fra rot. Benyttet en while-løkke til å løpe gjennom.
Verdiene ble sammenliknet ved bruk av `comp.compare()`. Hvis en sammenlikning gir et negativt tall, så betyr det at 
verdiene er ulike, og det letes videre. Hvis sammenlikningen viser 0, så betyr det at en forekomst er 
funnet og vi adderer antallet. Til slutt returneres antallet forekomster av verdien det ble søkt etter.

Oppgave 3: I `førstePostorden(Node<T> p)` brukes en `while`-løkke til å løpe gjennom. Inni denne løkka så er det
`if`-/`else`-setninger. Så lenge `p` sin venstre ikke viser `null`, betyr det at noden har et venstre subtre og beveger seg
dermed til venstre i subtreet. Hvis `p` sin venstre viser `null`, sjekkes det så om `p` sin høyre viser `null` eller ikke.
Hvis ikke `null`, så gås det til høyre i subtreet. Returnerer til slutt `p`. I `nestePostorden(Node<T> p)` returneres
noden som kommer etter p i postorden.

Oppgave 4: Lagde hjelpemetodene `public void postorden(Oppgave <? super T> oppgave)` og
`private void postordenRecursive(Node<T>  p,  Oppgave<?  super  T>  oppgave)`. Den første metoden benyttet seg av
metoden `førstePostorden()` for startnode, for deretter å utføre oppgave. Metoden `nestePostorden()` ble så brukt
for å hente neste node. En while-løkke løper videre, utfører oppgave i rekkefølge og oppdaterer variabelen
som settes av `nestePostorden()`. Den andre metoden, `postordenRecursive()`, travereseres treet i postorden rekkefølge,
og kaller seg selv hvis hvis p sin venstre ikke er null, eller deretter p sin høyre ikke er null.


Ellers:
Gjenstående warnings handler for det meste om Non-ASCII characters og symbols. Jeg fjernet ubrukte metoder og tester.