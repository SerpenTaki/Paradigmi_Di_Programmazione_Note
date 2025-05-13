Struttura del modello dei Thread in Java e quali operazioni si possono fare su di essi.
# Stato del Thread
![[Screenshot 2025-05-12 alle 18.18.35.png]]
![[Screenshot 2025-05-12 alle 18.18.44.png]]
![[Screenshot 2025-05-12 alle 18.18.59.png]]
![[Screenshot 2025-05-12 alle 18.19.10.png]]
![[Screenshot 2025-05-12 alle 18.19.20.png]]
![[Screenshot 2025-05-12 alle 18.19.31.png]]
![[Screenshot 2025-05-12 alle 18.19.44.png]]
![[Screenshot 2025-05-12 alle 18.19.55.png]]
## Esecutori Executors
Creare un nuovo Thread per ogni operazione da fare può velocemente diventare costoso.
L'amministrazione dei Thread impegnati allo stesso modo, si complica al crescere del numero degli oggetti.
La soluzione è cedere parte del controllo al sistema in cambio di maggiore semplicità ed efficienza.
## Callables
Finora abbiamo usato come lavoro da eseguire dei `Runnable`, cioè dei blocchi privi di risultato.
L'interfaccia `Callable`ci permette di definire dei compiti che producono un risultato.
Un semplice `Executor`non esegue `Callable`: è necessario scegliere un `Executor Service`che espone i metodi necessari.
Un `Future` rappresenta un calcolo che prima o poi ritornerà un valore. È possibile verificare se il calcolo è stato completato, ottenere il valore risultante o controllare se sia ancora in corso.
Con a disposizione una lista di `Callables`, un `ExecutorService` ci permette di:
- ottenere un risultato di un `Future`che ha terminato
- ottenere una lista di `Future`nel momento in cui sono tutti completati
Un `ExecutorService` rimane sempre in attesa di nuovi compiti da eseguire, impedendo alla JVM di terminare.
Per permettere alla JVM di fermarsi bisogna esplicitamente fermare il servizio.
# Virtual Threads
una delle novità introdotte con Java 21 è costituita dai `virtual threads`.
è una linea di esecuzione gestita completamente dalla JVM, che non corrisponde ad un Thread di sistema operativo.
Di conseguenza mentre in una macchina di media potenza ci si può aspettare di riuscire a gestire intorno a 10000 threads, la stessa macchina può gestire milioni di virtual threads.
I *virtual thread* sono sintatticamente dei Thread normali, ma tutte le API bloccanti le gestiscono in modo particolare.
La JVM gestisce in maniera autonoma la messa in attesa del *virtual thread* (*unmounting*) durante l'esecuzione di una operazione bloccante, per poi riprenderne l'esecuzione su di un nuovo Thread (*mounting*).
Le operazioni di montaggio e smontaggio sono più economiche di un passaggio di Thread, con il risultato complessivo che si possono avere molte più linee di esecuzione senza pagarne completamente i costi di cambio contesto. Viene creata in questo modo una unità di concorrenza più piccola del thread.
I *virtual thread* possono essere considerati una forma di *concorrenza collaborativa* gestita dalla JVM e che non richiede una programmazione esplicita da parte nostra.
