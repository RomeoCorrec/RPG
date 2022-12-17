package ElementsJeu;

import java.util.ArrayList;
import java.util.Random;

public class Healer extends SpellCaster{

    Weapon caducee = new Weapon("caducée", 0, 30);



    public void soin(Healer heroH, Hero hero) {
        float degatsSoigner = (caducee.getDegatsMagique() + heroH.getForce()) * hero.getEfficaciteSoin();
        System.out.println("Je soigne " + hero.getName());
        Random rd = new Random();
        int R = rd.nextInt(11);
        if (R > 7) {
            System.out.println("Coup critique !");
            degatsSoigner *= 2;
        }
        if (hero.getPdVie() + degatsSoigner > hero.getPdvMax()) {
            hero.setPdVie(hero.getPdvMax());
        } else {
            hero.setPdVie(hero.getPdVie() + degatsSoigner);
        }
        System.out.println(degatsSoigner);
        System.out.println(hero.getName() + "a " + hero.getPdVie() + " pv");
        heroH.setPdMana(heroH.getPdMana() - 10);
    }

    public void soinDeZone(Healer heroH, ArrayList<Hero> TH) {
        System.out.println("Je soigne tout le monde !");
        for (Hero hero : TH) {
            int degatsSoigne = (int) ((caducee.getDegatsMagique() + heroH.getForce()) * hero.getEfficaciteSoin()) / 3;
            Random rd = new Random();
            int R = rd.nextInt(11);
            if (R > 7) {
                System.out.println("Coup critique !");
                degatsSoigne *= 2;
            }
            System.out.println(heroH.getName() + " soigne " + degatsSoigne + " points de vie a " + hero.getName());
            if (hero.getPdVie() + degatsSoigne > hero.getPdvMax()) {
                hero.setPdVie(hero.getPdvMax());
            } else {
                hero.setPdVie(hero.getPdVie() + degatsSoigne);
            }
            System.out.println(hero.getName() + "a " + hero.getPdVie() + " pv");
        }
        heroH.setPdMana(heroH.getPdMana() - 10 * TH.size());
    }

    public void ameliorationArme() {
        caducee.setDegats(caducee.getDegats() + 20);
        System.out.println("Votre épée inflige maintenant " + caducee.getDegats() + " point de dégats");
    }
    public Healer(String name) {
        super(name, 130, 1000, "Healer", 10, 20, 10, 130);
    }


}