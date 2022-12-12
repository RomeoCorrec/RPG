package ElementsJeu;

public class Food extends Consomable{
    private int pv_rendu = 20;


    public Food() {
        super("food");
    }

    public static void Manger(int portions, Hero hero) {
        if (hero.getQuantiteNouriture() == 0 || hero.getQuantiteNouriture() - portions < 0) {
            System.out.println("Vous n'avez plus assez de nourriture");
        }

        else {
            float pdvRecup = portions * 30;
            if (hero.getPdVie() + pdvRecup > hero.getPdVieMax()) {
                hero.setPdVie(hero.getPdVieMax());
            } else {
                hero.setPdVie(hero.getPdVie() + pdvRecup);
            }
            hero.setQuantiteNouriture(hero.getQuantiteNouriture() - portions);
        }
    }
}
