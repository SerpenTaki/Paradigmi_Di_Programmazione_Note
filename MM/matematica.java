package MM;

public class matematica {
    public static void main(String[] args) {
        // Operatori aritmetici
        int a = 10;
        int b = 5;
        System.out.println("Somma: " + (a + b));
        System.out.println("Sottrazione: " + (a - b));
        System.out.println("Moltiplicazione: " + (a * b));
        System.out.println("Divisione: " + (a / b));
        System.out.println("Modulo: " + (a % b));

        // Operatori di confronto
        System.out.println("Uguale: " + (a == b));
        System.out.println("Diverso: " + (a != b));
        System.out.println("Maggiore: " + (a > b));
        System.out.println("Minore: " + (a < b));
        System.out.println("Maggiore o uguale: " + (a >= b));
        System.out.println("Minore o uguale: " + (a <= b));

        // Operatori logici
        boolean x = true;
        boolean y = false;
        System.out.println("AND: " + (x && y));
        System.out.println("OR: " + (x || y));
        System.out.println("NOT: " + (!x));

        // Operazioni Math
        System.out.println("Max: " + Math.max(a, b) + " Min: " + Math.min(a, b));
        System.out.println("Radice quadrata di " + a + ": " + Math.sqrt(a));
        System.out.println("Potenza di " + a + " elevato a " + b + ": " + Math.pow(a, b));
        System.out.println("Valore assoluto di -10: " + Math.abs(-10));
        System.out.println("Valore casuale: " + Math.random());
        int randomNum = (int)(Math.random() * 101);  // 0 to 100
        System.out.println("Numero casuale tra 0 e 100: " + randomNum);
    }
    
}
