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
Una classe può dichiarare come membro una o più classi; queste vengono dette *Nested Classes*
##### Static Nested Classes
Una classe *static* non ha un accesso privilegiato ai membri della classe ospiti, nella pratica quindi è una normale classe di package che ha un nome sintatticamente prefissato da quello della classe ospite.
```Java
package it.unipd.pdp2023

public class App{
	static class Foo{
		int a;
	}
	static String s;
}
```
//Sono legate a qualche design patter come Builder o Factory
##### Inner Classes
Una classe interna non statica è una parte dello stato di un oggetto del tipo ospite. Quindi, ha lo stesso ciclo di vita ed ha un riferimento privilegiato all'oggetto ospitante. Non può dichiarare membri `static`ma solo membri di istanza
```Java
package it.unipd.pdp2023

public class App{
	class Bar{
		int a;
	}
	String s;
}
App a = new App();
App.Bar b = a.new Bar();
```
//sono il segnale di un modello dati forse troppo complesso.
# Inizializzatori 
**Statici**
I blocchi di inizializzazione dichiarati `static`vengono eseguiti in ordine lessicale, al caricamento della classe.
**Istanza**
Sono eseguiti in ordine lessicale, durante la creazione di ciascuna istanza di oggetto della classe. In particolare, sono eseguiti dopo il supercostruttore ma prima di qualsiasi costruttore.
# Ereditarietà
Java mette a disposizione un meccanismo di ereditarietà singola, una classe può avere una sola superclasse, di cui eredita codice e parte dello stato. Una sottoclasse ha accesso ai membri `public, package e protected` della superclasse ma non ai membri `private`.
```Java
package it.unipd.pdp2023

classe App{
	private int a;
	protected int b;
}

class Foo extends App{
	private String c;
}
```
Si evita quindi il "Diamond Problem" ma parte dei vantaggi dell'ereditarietà multipla viene recuperata con altri meccanismi.
Una sottoclasse è anche un *sottotipo* della classe che estende. Può cioè essere usata in ogni posto in cui viene richiesta la classe superiori.
Tutti i metodi in Java sono "virtual" nel senso cha ha il termine in C++. Vale a dire , il codice che realmente viene eseguito alla chiamata di un metodo è noto con certezza esclusivamente al runtime.
Una classe dichiara di essere sottoclasse di un'altra con la parola chiave `extends`dopo il nome della classe. Una classe dichiarata `final`non può essere usata come superclasse; non è possibile derivarne una sottoclasse.
Una classe dichiarata `sealed`può elencare le classi alle quali permette di ereditare, in modo tale da definire interamente la gerarchia di ereditarietà.
```Java
public abstract sealed class Shape{
	permits com.example.polar.Circle,
			com.example.quad.Rectangle,
			com.example.com.simple.Square {}
}
```
Una sottoclasse di una classe `sealed`può essere dichiarata `non-sealed` ed essere quindi estesa.
```Java
public non-sealed class Square
	extends Shape {public double side;}
```
Una classe dichiarata `abstract` deve essere usata come superclasse; non è possibile istanziarla direttamente.
```Java
package it.unipd.pdp2023

abstract class App{
	private int a;
	protected int b;
}

class Foo extends App{
	private String c;
}

App app = new App(); //Errore di compilazione
App foo = new Foo(); // OK
```
# Interfacce
Una interfaccia dichiara le caratteristiche di un Tipo senza fornire una sua implementazione. Le classi possono dichiarare di *implementare* una interfaccia fornendo l'implementazione richiesta. Contengono dichiarazioni di costanti, metodi astratti, metodi statici, metodi di default (Java8), metodi privati(Java9).
```Java
package it.unipd.pdp2023

interface Baz{
	int TEST = 1;
	void bar();
	String desc(boolean b);
}
class Foo extends App implements Baz{
	private String c;
	public void bar() {};
	public String desc(boolean b) {return "";}
}
```
Le interfacce permettono di avere una sorta di ereditarietà multipla in java mitigando il Diamond Problem. Una classe può estendere una sola altra classe, ma implementare molte interfacce.
**ATTENZIONE**
- Un interfaccia può essere estesa solo da un'altra interfaccia !!
- Una interfaccia può avere visibilità pubblica o di package
- Tutti i membri di una interfaccia sono pubblici, senza necessità di indicarlo.
### Annotazioni
Una interfaccia il cui nome inizia con @ diventa di un tipo particolare detto *annotazione*.
Le *annotazioni* si possono applicare sintatticamente a classi, metodi e variabili, e sono disponibili a momento dell'esecuzione.
Il loro scopo è arricchire di *metadati* la struttura a cui sono applicate, in modo da consentirne la rilevazione e l'uso durante la compilazione o l'esecuzione

| **Tipo**               | **Uso**                                                                      |
| ---------------------- | ---------------------------------------------------------------------------- |
| `@Deprecated`          | Segnala un metodo che verrà rimosso in futuro                                |
| `@Override`            | Segnala un membro che sostituisce un membro di una superclasse o interfaccia |
| `@SuppressWarnings`    | Istruisce il compilatore a sopprimere gli avvisi nel costrutto annotato      |
| `@FunctionalInterface` | Indica al compilatore che l'interfaccia può essere usata in modo funzionale  |
```Java
class Foo extends Bar{
	@Override
	String methodB() {return "";}
	@Deprecated
	@Override
	int methdOld {return 0;}
	@SuppressWarnings("unused")
	private boolean b = false;
}
```
# Tipi Generici
Una classe *generica* dichiara uno o più parametri di tipo che possono essere specificati in seguito:
```Java
interface MappableList<T>{
	void add(T element);
	T head();
	List<T> tail();
	<M> List<M> map(Function<T,M> xform);
}

class StringList implements MappableList< String > {
	public void add(String element) {};
	public String head() { return ""; };
	public List< String > tail() {
		return Collections.emptyList();
	};
	public < M > List< M > map(Function< String, M > xform) {
		List< M > res = new ArrayList<>();
		for (String s : Arrays.asList(""))res.add(xform.apply(s));
		return res;
	};
}
```
L'uso dei genereci permette di evitare di duplicare il codice e di semplificare l'uso delle classi contenitore fornendo al compilarore informazioni riguardo al tipo di ritorno di alcuni metodi.
# Valori Primitivi
| **Universo** | **Tipo Corto** | **Tipo Lungo** |
| ------------ | -------------- | -------------- |
| Interi       | byte,short,int | long           |
| Decimali     | float          | double         |
| Caratteri    | char           |                |
| Booleani     | boolean        |                |
Un valore primitivo non è un oggetto: nella sintassi non è trattato allo stesso modo ed un tipo primitivo non può essere indicato come parametro di tipo.
Ogni tipo primitivo ha quindi un corrispettivo Tipo non primitivo che può essere usato per trasportare lo stesso valore nelle situazioni in cui sia necessario.

| **Universo** | **Tipo Corto**      | **Tipo Lungo** |
| ------------ | ------------------- | -------------- |
| Interi       | Byte,Short, Integer | Long           |
| Decimali     | Float               | Double         |
| Caratteri    | Character           |                |
| Booleani     | Boolean             |                |
Gli array sono oggetti:
> An object is a class instance or an array.
> - Specifica Java

La classe array discende da Object e viene creata dinamicamente quando necessario.
```Java
class Foo{
	int[] array;
	Foo(){
		array = new int[10];
		array[0] = 42;
	}
}
```
# Enumerazioni
`enum`è una particolare categoria di classi: rappresenta un tipo contenente un numero determinato di elementi, definiti alla dichiarazione.
```Java
enum Category{
	A, B, C, D;
}

Category cat = Category A;
```
L'enumerazione può dichiarare varibili, metodi o implementare interfacce, e queste caratteristiche si riflettono sugli elementi in quanto istanze della classe.
Se nessun elemento dichiara una implementazione specifica, `enum`si considera `final`. Se uno o più ce l'hanno, l'intera enumerazione non lo è.
# Records
```Java
record Name(String firstName, String lastName) {} //Immutabile
```
Ottiene automaticamente:
- membri privati con metodi di accesso pubblici
- un costruttore con tutti gli elementi del record
- `equals, hashCode, toString`generati automaticamente dallo stato del record
È possibile definire metodi in un Record, o reimplementare uno dei metodi generati automaticamente. È possibile dichiarare un record generico o implementare una interfaccia. In questo modo, il Record diventa una scorciatoia per definire tutte le classi che normalmente modellano valori di dominio che vengono trasportati da un posto all'altro del sistema.
# This e Super
Le parole chiave `this`e `super`hanno un significato particolare. `this` permette di indicare l'istanza corrente durante l'esecuzione di un metodo. Può essere utile per risolvere ambiguità di denominazione o per rendere più esplicito il significato di una espressione
```Java
class Foo{
	public final int idx;
	public final String title;

	public Foo(int idx, String title){
		this.idx = idx;
		this.title = title;
	}
}
```
`super` indica l'oggetto padre nella gerarchia di ereditarietà. Permette di controllare il passaggio degli argomenti al costruttore della classe padre all'interno del costruttore della classe figlio.
```Java
class A{
	public final int a;
	public A(int a) {this.a = a;}
}

class B extends A{
	public final int b;
	public B(int a, int b){
		super(a);
		this.b = b;
	}
}
```
# Lambda Espressioni
Il compilatore individua il tipo che è atteso nell'espressione in cui la lambda si trova; il risultato è una istanza di un oggetto che implementa tale tipo, con il comportamento dato dall'istruzione.
```Java
() -> 42
() -> {return 42;}
() -> {System.gc();}

(int x) -> {return x+1;}
x -> x+1

(int x, int y) -> x+y
(x, y) -> x+y
```
# Condizionali
In Java i costrutti condizionali sono istruzioni, non espressioni.
Questo significa che eseguono blocchi di codice separati a seconda del valore della condizione.
```Java
enum Days {LUN, MAR, MER, GIO, VEN, SAT, DOM}
Days day =...;

switch (day){
	case LUN:
		System.out.println("Inizio settimana");
		break;
	case SAT, DOM:
		System.out.println("Weekend");
	default:
		System.out.println("Nel mezzo...");
		break;
}
```
# Iterazioni
##### While & For
```Java
int i = 0, sum = 0;
while(i<100)
	sum += i++;

int[] vals = new int {5,4,3,2,1}
for(int i = 0; i < vals.length; i++)
	System.out.println(vals[i]);

for(String s: List.of("foo", "bar", "baz"))
	System.out.println(s);
```
##### Do
```Java
public static String toHexString(int i){
	StringBuffer buf = new StringBuffer(8);
	do{
		buf.append(Character.forDigit(i & 0xF,16));
		i >>>= 4;
	} while(i != 0);
	return buf.reverse().toString();
}
```
La parola chiave `break` permette di interrompere immediatamente il ciclo di iterazione più interno in corso, qualsiasi sia il suo tipo.
La parola chiave `continue` permette di interrompere l'esecuzione dell'iterazione corrente, per proseguire immediatamente con la successiva (se applicabile).
L'istruzione `return`conclude la chiamata al metodo attuale, ritornando il controllo al codice chiamante.
# Eccezioni
### Try-Catch
```Java
class FooException extends Exception {}
class BarExcption extends Exception {}

class Foo{
	void a() throws FooException, BarException {return;}
	void b() throws BarException {
		try{ a(); } catch (FooException e){
			e.printStackTrace();
		} finally{ //sempre eseguito
			System.out.println("Always");
		} 
	}
}
```
### Throw
Per lanciare eccezioni destinate ad essere catturate da una istruzione `try`. Richiede un oggetto discendente da `Exception`
```Java
class FooException extends Exception{}

class Foo {
	void a() throws FooException, BarException{
		throw new FooException();
	}
	void b() throws BarException{
		try{ a(); } catch(FooException e){
			e.printStackTrace();
		} finally{
			System.out.println("Always");
		}
	}
}
```
# STL