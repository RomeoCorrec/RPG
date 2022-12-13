package ElementsJeu;

import java.util.Random;

public class Mage extends SpellCaster {

    static Weapon baguette = new Weapon("baguette", 0, 50);

    public static void sortDeFeu(Mage hero, Enemy enemy) {
        if (hero.getPdMana() > 10) {
            System.out.println("ca brule");
            Random rd = new Random();
            int R = rd.nextInt(11);
            int degatsInfliges = baguette.getDegatsMagique() - enemy.getResistanceMagic();
            if (R > 7) {
                System.out.println("Coup critique !");
                degatsInfliges *= 2;
            }
            System.out.println(hero.getName() + " inflige " + degatsInfliges + " à " + enemy.getName());
            enemy.setPdVie(enemy.getPdVie() - degatsInfliges);
            System.out.println(enemy.getName() + " a " + enemy.getPdVie());
            hero.setPdMana(hero.getPdMana() - 10);
        }
        else System.out.println("Vous n'avez pas asser de mana !");
    }

    public static void sortDeGlace(Mage hero, Enemy enemy) {
        if (hero.getPdMana() > 10) {
            Random rd = new Random();
            int R = rd.nextInt(11);
            int C = 1;
            if (R > 7) {
                System.out.println("Coup critique !");
                C = 2;
            }
            enemy.setResistanceMagic(enemy.getResistanceMagic() - 5 * C);
            enemy.setArmure(enemy.getArmure() - 5 * C);
            hero.setPdMana(hero.getPdMana() - 10);
        }
        else System.out.println("Vous n'avez pas asser de mana !");
    }

    public void ameliorationArme() {
        baguette.setDegatsMagique(baguette.getDegats() + 20);
        System.out.println("Votre baguette inflige maintenant " + baguette.getDegats() + " point de dégats");
    }

    public Mage(String name) {
        super(name, 130, 150, "Mage", 30, 20, 10, 130);
    }
}

