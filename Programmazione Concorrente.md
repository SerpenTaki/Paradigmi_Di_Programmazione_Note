Teoria e tecniche per la gestione di più processi sulla stessa macchina che operano contemporaneamente condividendo le risorse disponibili.
La singola CPU è chiaramente un collo di bottiglia: esegue un istruzione alla volta e questo diventa rapidamente poco efficiente; emergono molto presto opportunità per raggiungere una maggiore efficienza al costo di complessità architetturale e allontanamento dalla teoria.
I Sistemi Operativi si trovano nella necessità di gestire più attività contemporaneamente o in rapida successione.

Un processo descrive per il sistema operativo un programma in esecuzione e tutte le risorse che gli sono dedicate:
- memoria
- canali di I/O
- interrupt e segnali
- stato della CPU
I processi non condividono risorse normalmente: possono comunicare fra loro, ma solo interagendo come entità separate.
L'obiettivo del SO è garantire l'utilizzo efficace e paritario delle risorse, non favorirne l'uso contemporaneo.

Per gestire più linee di esecuzione all'interno dello stesso processo è stato ideato il concetto di thread.

# Thread
Condividono le risorse di uno stesso processo, rendendo economico il costo di passaggio da un ramo di esecuzione all'altro.
La **Programmazione distribuita** implica la comunicazione fra entità che non possono avere stato condiviso. Come questo stato venga gestito è ininfluente.
La **Programmazione funzionale** tratta dati immutabili con qualche concessione alla mutabilità per lo stretto necessario. Lo stato può essere distinto o condiviso.
La **Programmazione concorrente** si pone nel quadrante più difficile dove lo stato è mutabile e condiviso e quindi l'accesso e l'intervento su di esso va coordinato e gestito.
![[Screenshot 2025-04-24 alle 17.14.03.png]]
### Problemi della concorrenza
1. Un'esecuzione concorrente è inerentemente non deterministica. (*non determinismo*)
2. Un thread che non riceve abbastanza risorse non può fare il suo lavoro. (*starvation*)
3. Se più thread competono per le stesse risorse, il loro ordine di esecuzione può essere rilevante per il risultato (*Race Conditions*)
4. Se 2 thread attendono ciascuno la risorsa che ha già preso l'altro, nessuno dei 2 può proseguire (*Deadlock*)
## Le condizioni di Coffman
Condizioni necessarie perchè un Deadlock possa avvenire.
Rimuovere anche una sola delle condizioni rende impossibile entrare in un deadlock.
- Mutua esclusione
- Hold and wait or resource holding
- Attesa Circolare
- no pre-emption
# Tipologie di Concorrenza
| **Tipo**           | **Strutture**           |
| ------------------ | ----------------------- |
| Collaborativa      | Co-Routines             |
| Pre-emptive        | Processi, Threads       |
| Real-Time          | Processi, Threads       |
| Event Driven/Async | Future, Events, Streams |
**Collaborativa**:
I programmi devono esplicitamente cedere il controllo ad intervalli regolari.
È un modello ancora rilevante in alcuni ambiti.
**Pre-empitive**:
Il sistema operativo è in grado di interrompere l'esecuzione di un programma e sottrargli il controllo delle risorse per affidarle al programma seguente.
È il modello più comune nei sistemi operativi moderni
**Real-Time**:
Il sistema operativo garantisce prestazioni precise e prefissate nella suddivisione delle risorse fra i programmi.
**Event Driven**:
I programmi dichiarano le operazioni che vanno eseguite e lasciano all'ambiente di esecuzione la decisione di quando eseguirle e come assegnare le risorse.
# Java Threads
un thread è rappresentato da una istanza dell'omonima classe.
```Java
public Thread(Runnable target)
```
Il metodo principale è `start()`che avvia un nuovo percorso di esecuzione, condividendo lo stesso heap e lo stesso stato complessivo
```Java
void start()
static void sleep(long millis)
```
`sleep()`mette in pausa il thread corrente per un determinato lasso di tempo
L'interfaccia Runnable modella un compito da eseguire da cui non ci si aspetta un risultato. 
