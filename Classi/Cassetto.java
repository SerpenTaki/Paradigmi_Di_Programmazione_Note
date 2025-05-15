package Classi;

public class Cassetto implements Mobili {
    public void toctoc() {
        System.out.println("toc toc");
    }

    public boolean ikea() {
        return true;
    }
    public void open() {
        System.out.println("Cassetto aperto");
    }
    public void close() {
        System.out.println("Cassetto chiuso");
    }
}
