package ElementsJeu;

import javafx.scene.control.Tab;
import utils.ConsoleParser;
import utils.InputParser;
import utils.InputParser.*;

import java.util.ArrayList;
import java.util.Random;


public class Game {
    static InputParser CP = new ConsoleParser();
    public static void main(String[] args) throws InterruptedException {
        ArrayList<Hero> TableauHero = new ArrayList<>();
        ArrayList<Enemy> TableauEnemy = new ArrayList<>();
        CP.affichageTexte("Bienvenue sur le Mini RPG Lite 3000 !!");
        CP.affichageTexte("combien voulez vous jouer de héros ?");
        int N = 0;
        try {
            N = CP.recuperationInt();
        } catch (Exception e) {
            CP.affichageTexte("Veuillez rentrer un nombre");
            Game.main(new String[] {});
        }
        for (int i = 0; i < N; i++) {
            InitialisationHero(TableauHero, i);
            initialisationEnemy(TableauEnemy, i);
        }
        int initSize = TableauHero.size();
        InitialisationInventaire(TableauHero);

        boolean VaAffronterLeBoss = false;
        for (int i = 0; i < 5; i++) {
        tourDeCombat(TableauHero, TableauEnemy);
        for(int j = 0; j < initSize; j++) {
            initialisationEnemy(TableauEnemy, j);
        }
        Thread.sleep(1000);
        AmeliorationPostCombat(TableauHero);
        if (i == 4 && TableauHero.size() != 0) {
            VaAffronterLeBoss = true;
        }
        }
        if (VaAffronterLeBoss) {
            CP.affichageTexte("Vous allez maintenant affronter le BOSS");
            BOSS boss = new BOSS("BOSS");
            boss.setPdVie(100 * TableauHero.size());
            ArrayList<Enemy> TB = new ArrayList<>();
            TB.add(boss);
            tourDeCombat(TableauHero, TB);
            if(TableauHero.size() != 0) {
                CP.affichageTexte("Vous avez vaincue le BOSS, bien jouer !!");
            }
        }
    }

    public static void InitialisationHero(ArrayList<Hero> TH, int i) {
            int C = 0;
            try {
                CP.affichageTexte("1. Warrior");
                CP.affichageTexte("2. Hunter");
                CP.affichageTexte("3. Healer");
                CP.affichageTexte("4. Mage");
                CP.affichageTexte("Quelle est la classe du héro n°" + (i + 1) + " ?");

                do {
                    C = CP.recuperationInt();
                } while (0 > C & C > 4);
            } catch (Exception e) {
                CP.affichageTexte("Veuillez entrer un nombre entre 1 et 4");
                InitialisationHero(TH, i);
            }

            switch (C) {
                case 1:
                    CP.affichageTexte("quelle est le nom de vore Warrior ?");
                    String nomW = CP.recuperationTexte();
                    // Warrior warrior = new Warrior(nomW);
                    TH.add(new Warrior(nomW));
                    break;

                case 2:
                    CP.affichageTexte("quelle est le nom de vore Hunter ?");
                    String nomH = CP.recuperationTexte();
                    //Hunter hunter = new Hunter(nomH);
                    TH.add(new Hunter(nomH));
                    break;

                case 3:
                    CP.affichageTexte("quelle est le nom de vore Healer ?");
                    String nomHe = CP.recuperationTexte();
                    // Healer healer = new Healer(nomHe);
                    TH.add(new Healer(nomHe));
                    break;

                case 4:
                    CP.affichageTexte("quelle est le nom de vore Mage ?");
                    String nomM = CP.recuperationTexte();
                    // Mage mage = new Mage(nomM);
                    TH.add(new Mage(nomM));
                    break;
            }

        }

    public static void initialisationEnemy(ArrayList<Enemy> TE, int i) {
        String num = String.valueOf(i + 1);
        TE.add(new Enemy("Enemy " + num));
    }

    public static void InitialisationInventaire(ArrayList<Hero> TH) {
        for (Hero i : TH) {
            for (int q = 0; q < i.getQuantiteNouriture(); q++) {
                i.addInventory(new Food());
            }

            for (int q = 0; q < i.getQuantitePotion(); q++) {
                i.addInventory(new Potion());
            }
        }
    }

    public static void tourDeCombat(ArrayList<Hero> TableauHero, ArrayList<Enemy> TableauEnemy) throws InterruptedException {
        int NbrTour = 0;
        while (TableauHero.size() != 0 && TableauEnemy.size() != 0) {
            ArrayList<Combatant> TableauCombatant = new ArrayList<>();
            ArrayList<Combatant> TableauMorts = new ArrayList<>();
            TableauCombatant.addAll(TableauHero);
            TableauCombatant.addAll(TableauEnemy);
            int TailleI = TableauCombatant.size();
            ArrayList<Combatant> TourCombatant = new ArrayList<>();
            for (int i = 0; i < TailleI; i++) {
                Random rd = new Random();
                int R = rd.nextInt(TailleI - i);
                TourCombatant.add(TableauCombatant.get(R));
                TableauCombatant.remove(R);
            }

            NbrTour += 1;
            CP.affichageTexte("TOUR N° " + NbrTour);
            for (Combatant C : TourCombatant) {
                if (!TableauMorts.contains(C)) {
                    Random rd = new Random();
                    Combatant combatantActuel = C;
                    CP.affichageTexte("C'est le tour de " + combatantActuel.getName());
                    Thread.sleep(1000);
                    if (combatantActuel instanceof Enemy) {
                        int NJ = rd.nextInt(TableauHero.size());
                        ((Enemy) combatantActuel).attaquer(((Enemy) combatantActuel), TableauHero.get(NJ));
                        Thread.sleep(1000);
                        if (TableauHero.get(NJ).getPdVie() <= 0) {
                            CP.affichageTexte(TableauHero.get(NJ).getName() + " est mort");
                            TableauMorts.add(TableauHero.get(NJ));
                            TableauHero.remove(NJ);
                            if (TableauHero.isEmpty()) {
                                CP.affichageTexte("Tous vos héros sont morts");
                                CP.affichageTexte("Vous avez échoué...");
                                break;
                            }
                        }
                    } else if (combatantActuel instanceof Hero) {
                        Hero heroActuel = ((Hero) combatantActuel);
                        if (heroActuel.getClasse().equals("Warrior")) {
                            // Warrior warrior = (Warrior) ((Hero) combatantActuel);
                            // warrior.setName(heroActuel.getName());
                            CP.affichageTexte("Que voulez vous faire " + heroActuel.getName() + " ?");
                            Thread.sleep(1000);
                            CP.affichageTexte("1. Attaquer");
                            CP.affichageTexte("2. Se défendre");
                            CP.affichageTexte("3. Utiliser un Item");
                            int choix = CP.recuperationInt();

                            switch (choix) {
                                case 1:
                                    CP.affichageTexte("quel enemie voulez-vous attaquer ?");
                                    int x = 1;
                                    for (Enemy e : TableauEnemy) {
                                        CP.affichageTexte(x + ". " + e.getName());
                                        x++;
                                    }
                                    int choixE = CP.recuperationInt();
                                    ((Warrior) heroActuel).coupDepee(((Warrior) heroActuel), TableauEnemy.get(choixE - 1));
                                    if (TableauEnemy.get(choixE - 1).getPdVie() <= 0) {
                                        CP.affichageTexte(TableauEnemy.get(choixE - 1).getName() + " est mort");
                                        TableauEnemy.remove(choixE - 1);
                                    }
                                    break;

                                case 2:
                                    heroActuel.setDefendre(true);
                                    CP.affichageTexte(heroActuel.getName() + " se défend !");
                                    break;

                                case 3:
                                    CP.affichageTexte("Quel item voulez vous utiliser ?");
                                    heroActuel.showInventory();
                                    String item = CP.recuperationTexte();
                                    heroActuel.utiliserUnItem(heroActuel, item);
                                    break;
                            }

                        } else if (heroActuel.getClasse().equals("Hunter")) {
                            CP.affichageTexte("Que voulez vous faire ?");
                            Thread.sleep(1000);
                            CP.affichageTexte("1. Attaquer");
                            CP.affichageTexte("3. Se défendre");
                            CP.affichageTexte("2. Utiliser un Item");
                            int choix = CP.recuperationInt();

                            switch (choix) {
                                case 1:
                                    CP.affichageTexte("quel enemie voulez-vous attaquer ?");
                                    int x = 1;
                                    for (Enemy e : TableauEnemy) {
                                        CP.affichageTexte(x + ". " + e.getName());
                                        x++;
                                    }
                                    int choixE = CP.recuperationInt();
                                    ((Hunter) heroActuel).tireAlArc(TableauEnemy.get(choixE - 1), ((Hunter) heroActuel));
                                    if (TableauEnemy.get(choixE - 1).getPdVie() <= 0) {
                                        CP.affichageTexte(TableauEnemy.get(choixE - 1).getName() + " est mort");
                                        TableauEnemy.remove(choixE - 1);
                                    }
                                    break;

                                case 2:
                                    heroActuel.setDefendre(true);
                                    CP.affichageTexte(heroActuel.getName() + " se défend !");
                                    break;

                                case 3:
                                    CP.affichageTexte("Quel item voulez vous utiliser ?");
                                    heroActuel.showInventory();
                                    CP.recuperationTexte();
                                    String item = CP.recuperationTexte();
                                    heroActuel.utiliserUnItem(heroActuel, item);
                                    break;


                            }
                        } else if (heroActuel.getClasse() == "Mage") {
                            CP.affichageTexte("Que voulez vous faire " + heroActuel.getName() + " ?");
                            Thread.sleep(1000);
                            CP.affichageTexte("1. Attaquer");
                            CP.affichageTexte("3. Se défendre");
                            CP.affichageTexte("2. Utiliser un Item");
                            int choix = CP.recuperationInt();

                            switch (choix) {
                                case 1:
                                    CP.affichageTexte("Quel sort voulez-vous utiliser ?");
                                    Thread.sleep(1000);
                                    CP.affichageTexte("1. Sort de feu");
                                    CP.affichageTexte("2. Sort de glace");
                                    int choixS = CP.recuperationInt();
                                    CP.affichageTexte("quel enemie voulez-vous attaquer ?");
                                    int x = 1;
                                    for (Enemy e : TableauEnemy) {
                                        CP.affichageTexte(x + ". " + e.getName());
                                        x++;
                                    }
                                    int choixE = CP.recuperationInt();
                                    switch (choixS) {
                                        case 1:
                                            Mage.sortDeFeu((Mage) heroActuel, TableauEnemy.get(choixE - 1));
                                            if (TableauEnemy.get(choixE - 1).getPdVie() <= 0) {
                                                CP.affichageTexte(TableauEnemy.get(choixE - 1).getName() + " est mort");
                                                TableauEnemy.remove(choixE - 1);

                                            }
                                            break;
                                        case 2:
                                            ((Mage) heroActuel).sortDeGlace(((Mage) heroActuel), TableauEnemy.get(choixE - 1));
                                            break;
                                    }
                                    break;

                                case 2:
                                    heroActuel.setDefendre(true);
                                    CP.affichageTexte(heroActuel.getName() + " se défend !");
                                    break;

                                case 3:
                                    CP.affichageTexte("Quel item voulez vous utiliser ?");
                                    heroActuel.showInventory();
                                    CP.recuperationTexte();
                                    String item = CP.recuperationTexte();
                                    heroActuel.utiliserUnItem(heroActuel, item);
                                    break;


                            }
                        } else if (heroActuel.getClasse().equals("Healer")) {
                            CP.affichageTexte("Que voulez vous faire " + heroActuel.getName() + " ?");
                            // Thread.sleep(1000);
                            CP.affichageTexte("1. Soigner");
                            CP.affichageTexte("3. Se défendre");
                            CP.affichageTexte("2. Utiliser un Item");
                            int choix = CP.recuperationInt();

                            switch (choix) {
                                case 1:
                                    CP.affichageTexte("Quel sort voulez-vous utiliser ?");
                                    Thread.sleep(1000);
                                    CP.affichageTexte("1. Soin monocible");
                                    CP.affichageTexte("2. Soin de zone");
                                    int choixS = CP.recuperationInt();
                                    switch (choixS) {
                                        case 1:
                                            CP.affichageTexte("Qui voulez-vous soigner ?");
                                            for (Hero h : TableauHero) {
                                                CP.affichageTexte((TableauHero.indexOf(h) + 1) + ". ");
                                                CP.affichageTexte(h.getName());
                                            }
                                            int choixH = CP.recuperationInt();
                                            ((Healer) heroActuel).soin(((Healer) heroActuel), TableauHero.get(choixH - 1));
                                            break;
                                        case 2:
                                            ((Healer) heroActuel).soinDeZone(((Healer) heroActuel), TableauHero);
                                            break;
                                    }
                                    break;

                                case 2:
                                    heroActuel.setDefendre(true);
                                    CP.affichageTexte(heroActuel.getName() + " se défend !");
                                    break;

                                case 3:
                                    CP.affichageTexte("Quel item voulez vous utiliser ?");
                                    heroActuel.showInventory();
                                    String item = CP.recuperationTexte();
                                    heroActuel.utiliserUnItem(heroActuel, item);
                                    break;


                            }
                        }
                        if (TableauEnemy.isEmpty()) {
                            CP.affichageTexte("Vous avez vaincu tous les ennemis de ce niveau");
                            break;
                        }
                    }
                    CP.affichageTexte("\n");
                    CP.affichageTexte("\n");
                    CP.affichageTexte("\n");

                }
            }
        }
    }

    public static void AmeliorationPostCombat (ArrayList<Hero> TA) {
        for(Hero H : TA) {
            CP.affichageTexte("que vouler vous faire " + H.getName() + " ?");
            CP.affichageTexte("1. augmenter vos dégats");
            CP.affichageTexte("2. augmenter la résistance au attaque");
            CP.affichageTexte("3. augmenter l'éfficacité des soins");
            CP.affichageTexte("4. augmenter le nombre de potions et de nouriture");
            if(H instanceof Hunter) {
                CP.affichageTexte("5. augmenter le nombre de flèches");
            }
            int Choix = CP.recuperationInt();
            switch (Choix)  {
                case 1:
                    H.setForce(H.getForce() + 10);
                    CP.affichageTexte("Vous avez maintenant " + H.getForce() + " points de forces");
                    break;

                case 2:
                    H.setArmure(H.getArmure() + 10);
                    CP.affichageTexte("Vous avez maintenant " + H.getArmure() + " points d'armure");
                    break;

                case 3:
                    H.setEfficaciteSoin((float) ( H.getEfficaciteSoin() + 0.1));
                    CP.affichageTexte("Les soins que vous recevez sont maintenant efficace à " + H.getEfficaciteSoin()*100 + "% sur vous");
                    break;

                case 4:
                    H.setQuantitePotion(H.getQuantitePotion() + 1);
                    H.setQuantiteNouriture(H.getQuantiteNouriture() + 1);
                    CP.affichageTexte("Vous avez maintenant " + H.getQuantitePotion() + " potions et " + H.getQuantiteNouriture() + " portions de nouriture");
                    break;

                case 5:
                    try {
                        ((Hunter) H).setNbrFleche(((Hunter) H).getNbrFleche() + 5);
                        CP.affichageTexte("Vous avez maintenant " + ((Hunter) H).getNbrFleche() + " flèches");
                    } catch (Exception e) {
                        CP.affichageTexte("Vous n'êtes pas un hunter !");
                    }
                    break;
            }
        }
    }
}
