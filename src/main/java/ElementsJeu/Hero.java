package ElementsJeu;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class Hero extends Combatant {

    // Attributs


    private String name;
    private String classe;
    private int QuantiteNouriture;
    private int QuantitePotion;
    private int armure;
    private float efficaciteSoin;

    private float pdVie;

    private int force;


    public ArrayList<Item> Inventaire = new ArrayList<Item>();

    private boolean seDefend;



    // Méthodes

    public void utiliserUnItem(Hero hero, String item) {
        Scanner scanner = new Scanner(System.in);
        if (item.equals("food")) {
            System.out.println("Mangeons");
            int portions;
            do {
                System.out.println("combien de portions voulez vous manger ?");
                portions = scanner.nextInt();
            } while (portions > hero.getQuantiteNouriture());
            for (int i = 0; i < portions; i++) {
                int j = 0;
                while (Inventaire.get(j).getName() != "food") {
                    j++;
                }
                Food food = (Food) Inventaire.get(j);
                food.Manger(1, hero);
                Inventaire.remove(j);
            }
            hero.setQuantiteNouriture(hero.getQuantiteNouriture() - 2);
            System.out.println(hero.getName() + " a " + hero.getPdVie() + " pv");
        }
        else if (item.equals("potion")) {
            System.out.println("Buvons");
            int portions;
            do {
                System.out.println("combien de portions voulez vous boire ?");
                portions = scanner.nextInt();
            } while (portions > hero.getQuantitePotion());
            for (int i = 0; i < portions; i++) {
                int j = 0;
                while (Inventaire.get(j).getName() != "potion") {
                    j++;
                }
                Potion potion = (Potion) Inventaire.get(j);
                potion.consommerPotion(1, hero);
                Inventaire.remove(j);
            }
            hero.setQuantitePotion(hero.getQuantitePotion() - portions);
            System.out.println(hero.getName() + " a " + hero.getPdVie() + " pv");
        }
    }
    public void addInventory (Item item){
            Inventaire.add(item);
        }

        //constructeur

    public Hero(String name, float pdVie, int pdMana, String classe, int force, int armure, int resistanceMagique, int pdVieMax){
            super(name, pdVie, pdMana, armure, resistanceMagique, pdVieMax);
            this.classe = classe;
            this.force = force;
            this.QuantiteNouriture = 5;
            this.QuantitePotion = 3;
            this.efficaciteSoin = 1;

        }


        // Getter/Setter


        public String getClasse () {
            return classe;
        }

        public int getQuantiteNouriture () {
            return QuantiteNouriture;
        }

        public int getQuantitePotion () {
            return QuantitePotion;
        }


        public float getEfficaciteSoin () {
            return efficaciteSoin;
        }


        public ArrayList getInventory () {
            return Inventaire;
        }

        public boolean getDefence () {
            return seDefend;
        }

        public int getForce () {
            return force;
        }

         public void showInventory () {
            System.out.println("Inventaire :");
            System.out.print("| ");
            for (int i = 0; i < Inventaire.size(); i++) {
                System.out.print(Inventaire.get(i).getName() + " | ");
            }
            System.out.println("");
        }

        public void setClasse (String classe){
            this.classe = classe;
        }

        public void setQuantiteNouriture ( int quantiteNouriture){
            this.QuantiteNouriture = quantiteNouriture;
        }

        public void setQuantitePotion ( int quantitePotion){
            this.QuantitePotion = quantitePotion;
        }


        public void setEfficaciteSoin ( float efficacitePotion){
            this.efficaciteSoin = efficacitePotion;
        }

        public void setDefendre(boolean seDefend) {
            this.seDefend = seDefend;
        }

        public void setForce (int force) {
            this.force = force;
        }
    }

