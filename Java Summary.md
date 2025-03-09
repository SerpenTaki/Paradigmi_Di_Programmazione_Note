# Classi e Tipi
In Java, l'unità principale di organizzazione del codice è la *Classe*.
Ogni oggetto fa necessariamente riferimento alla definizione di una *Classe*, che determina la struttura del suo stato ed il codice che opera su tale stato.
Il codice eseguibile deve necessariamente essere incluso in una *Classe*: come *metodo* dotato di un nome e richiamabile da altri oggetti, o come blocco anonimo eseguito alla creazione di ciascuna istanza.
Una classe in Java è dichiarata con la parola chiave `class` seguita dal nome e dalla definizione.
```Java
class App {}
```
Per convenzione le classi Java sono denominate in *Pascal Case* con l'iniziale maiuscola.
Una classe appartiene ad un *Package*, che permette di organizzare le classi in gruppi gerarchici
```Java
package it.unipd.pdp2024

class App{}
```
La parola chiave `package`se presente deve essere la prima riga di codice del file;
Se la classe pubblica `App` viene definita all'interno del `package it.unipd.pdp2024`, il suo file sorgente deve trovarsi all'interno della directory `it/unipd/pdp2024`
```shell
it/unipd/pdp2024/App.java
```
Per convenzione i *package* sono denominati con nomi di dominio in ordine inverso:
- `org.apache.commons`
- `com.oracle.jdbc`
## Visibilità
Una classe non può usare un'altra classe qualsiasi: deve averne visibilità.
Ogni classe, nella sua definizione indica la sua visibilità. ovvero la politica di accesso che intende concedere alle altre classi.
#### Visibilità di default
In mancanza di indicazioni, una classe è visibile da parte di tutte le classi dello stesso package, ma non dalle classi al di fuori di esso.
Una classe può usare una qualsiasi altra classe all'interno dello stesso package senza indicazioni particolari.
#### Visibilità pubblica
Una classe dichiarata `public`è visibile da qualsiasi altra classe caricata dalla JVM. Un file sorgente può contenere **al più una** classe pubblica, e **deve** chiamarsi come la classe contenuta.
U