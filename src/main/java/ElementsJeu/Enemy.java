package ElementsJeu;

import java.util.Random;


public class Enemy extends Combatant{

    Weapon massue = new Weapon("massue", 50, 0);
    private float pdvMax;

    public void attaquer (Enemy enemy, Hero hero) {
        Random rd = new Random();
        int R = rd.nextInt(11);
        float degatsInfliges = massue.getDegats() - hero.getArmure();
        if (R >= 9) {
            System.out.println("Coup critique !");
            degatsInfliges *= 2;
        }
        if (hero.getDefence()) {
            degatsInfliges = degatsInfliges/2;
            hero.setDefendre(false);
        }
        System.out.println(enemy.getName() + " inflige " + degatsInfliges + " points de dégats à " + hero.getName());
        hero.setPdVie(hero.getPdVie() - degatsInfliges);
        System.out.println(hero.getName() + " a " + hero.getPdVie() + " points de vie");
    }
    public Enemy(String name) {
        super(name, (int) (Math.random() * 51) + 100, 0, (int) (Math.random() * 10) + 20, (int) (Math.random() * 10) + 20,0);
        this.pdvMax = this.getPdVie();}

    public Enemy(String name, int pdVie) {
        super(name, pdVie, 0, (int) (Math.random() * 10) + 20, (int) (Math.random() * 10) + 20,0);
        this.pdvMax = this.getPdVie();}


    public float getPdvMax() {
        return pdvMax;
    }
}

