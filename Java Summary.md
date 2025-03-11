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
Un file sorgente può contenere **al più una** classe pubblica, e deve chiamarsi come la classe contenuta.
Usando la direttiva `import` si aggiunge al file sorgente il nome della classe importata, che può quindi essere richiamato senza essere prefissato dal package.
```Java
package it.unipd.pdp2024;
import it.unipd.pdp2024.Util;

public class App{
	Util a;
}
```
### ClassLoading
Il meccanismo di **ClassLoading** può interferire con la visibilità;
- il *ClassLoader* è l'ultimo arbitro della visibilità, e può decidere di non rendere accessibili al resto del codice alcune delle classi che carica, per es. per motivi di sicurezza
- A rigore, il nome di una classe può non essere unico fra *ClassLoader* diversi; in questi particolari ambienti altre regole determinano la visibilità effettiva.
# Struttura
Una classe può contenere:
- **variabili** -> definiscono la struttura dello stato di ciascun oggetto della classe, nome in Camel Case ovvero *iniziale minuscola* ad eccezione delle variabili segnate `static final`che sono costanti e pertanto si scrivono solitamente in maiuscolo con parole separate da underscore.
	- **Statiche**: nè esiste una sola copia, legata alla classe
	- **Istanza**: ogni oggetto ha la propria e fa parte del suo stato.
		- `public`: scritte e lette da ogni classe
		- `protected`: possono essere lette e scritte da classi che estendono la classe
		- `default`: possono essere lette e scritte da classi del package
		- `private`: possono essere lette e scritte solo da codice della classe
			- *final*: il valore della variabile non può essere modificato dopo l'assegnamento, inoltre, un valore deve essere assegnato alla costruzione
			- *transient*: la variabile va ignorata in sede di serializzazione
			- *volatile*: la variabile ha un comportamento particolare in relazione all'accesso concorrente
- **metodi**
	- I metodi vengono richiamati con la notazione `valore.nomemetodo(parametri)`dove `valore`è un oggetto della classe che li ha definiti, oppure la classe stessa per i metodi statici
	- Un costruttore viene richiamato con la parola chiave `new`e ritorna un oggetto della classe. *se non dichiarato esplicitamente il compilatore genera un costruttore di default, privo di argomenti, tuttavia quello di defaulti non viene generato se un altro costruttore viene generato*
```Java
boolean p = App.prepare("baz", 1);
App a = new App();
a.apply('z');
```
- altre classi
- blocchi di codice anonime
```Java
public class App {
	static char c;
	int a, a2 = 5, a3 = 7;
	String b;
	private boolean secret;
	public App() {}; //costruttore
	int apply(char d) {return 0;}
	static boolean prepare(String target, int count){
		throws RuntimeException{
			return false;
		}
	}
}
```
# Eccezioni
Le Eccezioni sono oggetti, ma vengono creati e usati in casi particolari e sono supportate da apposita sintassi. Derivano dalla classe `Throwable`. 
Una prima suddivisione avviene fra le 2 sottoclassi di `Throwable`:
- `Exception`: gli errori nonostante i quali il programma dovrebbe essere in grado di proseguire
- `Error`: gli errori dai quali il programma non è in grado di proseguire
Una particolare sottoclasse di `Exception`è `RuntimeException`: essa rappresenta ogni errore che può avvenire durante la normale valutazione di espressioni. Viene lanciata da JVM e quindi non necessita di essere dichiarata.
Eccezioni derivate da `RuntimeException`e `Error`sono dette *unchecked exceptions* e non necessitano dichiarazione nella definizione di un metodo.
Tutte le altre, discendenti da `Exception o Throwable`direttamente, sono dette *checked exception* e **devono** essere dichiarate nella definizione di un metodo.
# Nested Classes

