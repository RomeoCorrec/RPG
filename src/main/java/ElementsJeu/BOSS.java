package ElementsJeu;

import java.util.Random;

public class BOSS extends Enemy{

    Weapon Machette = new Weapon("Machette", 70, 0);
    private float PdVie;
    private int PdMana;
    private int armure;
    private int resistanceMagic;
    private String name;

    @Override
    public void attaquer (Enemy enemy, Hero hero) {
        Random rd = new Random();
        int R = rd.nextInt(11);
        float degatsInfliges = Machette.getDegats() - hero.getArmure();
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

    public BOSS(String name) {
        super(name, (int) ((Math.random() * 51) + 0));
        Random R = new Random();
        this.PdVie = 300;
        this.armure = R.nextInt(10) + 30;
        this.resistanceMagic = R.nextInt(10) + 30;
        int degats = R.nextInt(10) + 60;
        this.Machette.setDegats(degats);
    }


}
