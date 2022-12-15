package ElementsJeu;

public class Potion extends Consomable{
    private static int pv_rendu = 20;


    public Potion() {
        super("potion");
    }

    public static void consommerPotion(int quantite, Hero hero) {
        float pdvRecup = hero.getEfficaciteSoin() * quantite * 20;
        if (hero.getPdVie() + pdvRecup > hero.getPdvMax()) {
            hero.setPdVie(hero.getPdvMax());
        } else {
            hero.setPdVie(hero.getPdVie() + pdvRecup);
        }
        hero.setQuantitePotion(hero.getQuantitePotion() - quantite);
    }

}
