package Classi;

public class Main {
  public static void main(String[] args) {
    Pig pig = new Pig();
    System.out.println(" "); // true
    pig.makeSound(); // Oink
    pig.sleep(); // ZZZ
    System.out.println(" "); // true
    Lion lion = new Lion();
    lion.makeSound(); // Roar
    lion.sleep();
    lion.hunt(); // Hunting
   // Mobili m = new Mobili(); // non posso instanziare un oggetto di tipo astratto;
    Cassetto  c = new Cassetto();
    c.toctoc(); // nella libreria non fai toc toc
    c.ikea(); // false
    c.open();
    c.close();
    System.out.println(" "); // true
    Mobili l = new libreria();;
    l.toctoc(); // nella libreria non fai toc toc
    l.ikea(); // false
    System.out.println(" "); // true
  }
}
