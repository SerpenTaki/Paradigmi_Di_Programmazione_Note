package MM;

public class AreaRettangolo{
    public static void main(String[] args) {
        int base = 5;
        int altezza = 10;
        int area = base * altezza;
        System.out.println("L'area del rettangolo è: " + area);
        double areaDouble = area;
        System.out.println("L'area del rettangolo in double è: " + areaDouble);
        // Narrowing conversion
        int areaInt = (int) areaDouble;
        System.out.println("L'area del rettangolo in int è: " + areaInt);
        boolean sonoUguali = (area == areaDouble); // Confronto tra int e double
        System.out.println("L'area in int e double sono uguali? " + sonoUguali);
    }
}