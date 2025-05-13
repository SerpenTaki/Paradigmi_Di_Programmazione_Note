package MM;

public class Stringhe {
    public static void main(String[] args){
        String s1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        System.out.println("La lunghezza della stringa è: " + s1.length());
        String s2 = s1.toLowerCase();
        System.out.println("La stringa in minuscolo è: " + s2);
        System.out.println("La stringa in maiuscolo è: " + s2.toUpperCase());
        System.out.println("Troviamo la lettera M: " + s1.indexOf("M"));
        String txt = "We are the so-called \"Vikings\" from the north."; // Escape character
        System.out.println(txt);
    }
}
