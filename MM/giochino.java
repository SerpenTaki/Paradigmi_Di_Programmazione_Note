package MM;

public class giochino {
     public static void main(String[] args) {
        // Inizializza il numero casuale tra 1 e 100
        int numeroCasuale = (int) (Math.random() * 100) + 1;
        int tentativi = 0;
        boolean indovinato = false;

        System.out.println("Benvenuto al gioco! Indovina un numero tra 1 e 100.");

        while (!indovinato) {
            // Chiedi all'utente di inserire un numero
            System.out.print("Inserisci un numero: ");
            java.util.Scanner scanner = new java.util.Scanner(System.in);
            int numeroUtente = 0;
            boolean inputValido = false;
            while (!inputValido) {
                if (scanner.hasNextInt()) {
                    numeroUtente = scanner.nextInt();
                    if (numeroUtente >= 1 && numeroUtente <= 100) {
                        inputValido = true;
                    } else {
                        System.out.print("Per favore inserisci un numero tra 1 e 100: ");
                    }
                } else {
                    System.out.print("Input non valido. Inserisci un numero intero: ");
                    scanner.next(); // scarta input non valido
                }
            }
            tentativi++;

            // Controlla se il numero è corretto
            if (numeroUtente == numeroCasuale) {
                System.out.println("Congratulazioni! Hai indovinato il numero in " + tentativi + " tentativi.");
                indovinato = true;
            } else if (numeroUtente < numeroCasuale) {
                System.out.println("Troppo basso! Riprova.");
            } else {
                System.out.println("Troppo alto! Riprova.");
            }
        }
    }
}
