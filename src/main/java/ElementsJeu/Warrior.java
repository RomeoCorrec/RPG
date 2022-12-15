package ElementsJeu;

import java.util.Random;

public class Warrior extends Hero {
    static Weapon sword = new Weapon("sword", 60, 0);

    public Warrior(String name) {
        super(name, 200, 0, "Warrior", 30, 30, 50, 200);
    }

    public static void coupDepee(Warrior warrior, Enemy enemy) {
        Random rd = new Random();
        int R = rd.nextInt(11);
        int degatsInfliges = sword.getDegats() - enemy.getArmure();
        if (R > 7) {
            System.out.println("Coup critique !");
            degatsInfliges *= 2;
        }
        System.out.println(warrior.getName() + " inflige " + degatsInfliges + " points de dégats à " + enemy.getName());
        enemy.setPdVie(enemy.getPdVie() - degatsInfliges);
        System.out.println(enemy.getName() + " a " + enemy.getPdVie() + " points de vie");

    }

    public void ameliorationArme() {
        sword.setDegats(sword.getDegats() + 20);
        System.out.println("Votre épée inflige maintenant " + sword.getDegats() + " point de dégats");
    }
}
