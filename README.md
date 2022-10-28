# Obligatorisk oppgave 3 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 
Oppgaven er levert av følgende student:
* Alexander Hagen Huse, s354599, s354599@oslomet.no


# Oppgavebeskrivelse

I oppgave 1 så gikk jeg frem ved å først kopiere kildekode fra kompendiet under Programkode 5.2.3 a). 
Deretter utførte jeg en liten endring, som innebar å legge til verdien i variabelen `q` 
når ny node opprettes, for å angi foreldrenoden.

I oppgave 2 så startet jeg med å sjekke om treet var tomt eller ikke innholdt
verdien det letes etter i utgangspunktet ved å kalle på metodene tom() og
inneholder(). Så startet letingen fra rot. Benyttet en while-løkke til å løpe gjennom.
Verdiene ble sammenliknet ved bruk av comp.compare(). Hvis en sammenlikning gir et negativt tall, så betyr det at 
verdiene er ulike, og det letes videre. Hvis sammenlikningen viser 0, så betyr det at en forekomst er 
funnet og vi adderer antallet. Til slutt returneres antallet forekomster av verdien det ble søkt etter.

