package utils;
import java.util.Scanner;

public class ConsoleParser implements InputParser{

    @Override
    public void affchageInt(int nombre) {
        System.out.println(nombre);
    }

    @Override
    public void affichageTexte(String texte) {
        System.out.println(texte);
    }

    @Override
    public String recuperationTexte() {
        Scanner scanner = new Scanner(System.in);
        // scanner.nextLine();
        String texte = scanner.nextLine();
        return texte;
    }

    @Override
    public int recuperationInt() {
        Scanner scanner = new Scanner(System.in);
        int nombre = scanner.nextInt();
        return nombre;
    }



}
