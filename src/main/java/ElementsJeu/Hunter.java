package ElementsJeu;

import java.util.Random;

public class Hunter extends Hero{
    static Weapon arc = new Weapon("arc", 60, 0);
    private int nbrFleche = 100;

    public Hunter(String name) {
        super(name, 150, 100, "Hunter", 20, 20, 20, 150);
    }

    static public void tireAlArc(Enemy enemy, Hunter hunter) {
        if (hunter.getNbrFleche() > 0) {
            Random rd = new Random();
            int R = rd.nextInt(11);
            int degatsInfliges = arc.getDegats() - enemy.getArmure();
            if (R > 7) {
                System.out.println("Coup critique !");
                degatsInfliges *= 2;
            }
            enemy.setPdVie(enemy.getPdVie() - degatsInfliges);
            hunter.setNbrFleche(hunter.getNbrFleche() - 1);
            System.out.println(hunter.getName() + " inflige " + degatsInfliges + " points de dégats à " + enemy.getName());
            enemy.setPdVie(enemy.getPdVie() - degatsInfliges);
            System.out.println(enemy.getName() + " a " + enemy.getPdVie() + " points de vie");
        }

        else { System.out.println("Vous n'avez plus de flèches"); }
    }

    public static void flecheMagique(Enemy enemy, Weapon weapon, Hero hero) {
        Random rd = new Random();
        int R = rd.nextInt(11);
        int degatsInfliges = weapon.getDegatsMagique() - enemy.getResistanceMagic();
        if (R > 7) {
            System.out.println("Coup critique !");
            degatsInfliges *= 2;
        }
        enemy.setPdVie(enemy.getPdVie() - degatsInfliges);

    }

    public void ameliorationArme() {
        arc.setDegats(arc.getDegats() + 20);
        System.out.println("Votre arc inflige maintenant " + arc.getDegats() + " point de dégats");
    }

    public int getNbrFleche() {
        return nbrFleche;
    }

    public void setNbrFleche(int nbrFleche) {
        this.nbrFleche = nbrFleche;
    }
}

