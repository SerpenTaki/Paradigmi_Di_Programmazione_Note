package MM;

public class Main {
    static void myMethod() {
        System.out.println("Hello from myMethod");
    }

    static void stampaNomeEdEta(String nome, int eta){
        System.out.println(nome + " ha " + eta + " anni");
    }

    static int incrementaEtadiCinque(int eta){
        for (int i = 0; i < 5; i++) {
            eta++;
        }
        return eta;
    }

    public static void main(String[] args) {
        System.out.println("Hello World");
        myMethod();
        String nome = "Mario";
        int eta = 30;
        stampaNomeEdEta(nome, eta);
        System.out.println("L'eta incrementata di 5 e': " + incrementaEtadiCinque(eta));
    }
}
