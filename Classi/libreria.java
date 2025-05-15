package Classi;

public class libreria implements Mobili {
    public void toctoc() {
        System.out.println("nella libreria non fai toc toc");
    }
    public boolean ikea() { // costretto a implementare entrambi i metodi dell'interfaccia
        return false; // altrimenti non compila
    }
}
