package ElementsJeu;

public abstract class Combatant {

    //attributs
    private float PdVie;
    private float pdvMax;
    private int PdMana;
    private int armure;
    private int resistanceMagic;
    private String name;

    private boolean estVivant;


    public boolean estMort() {
        if (getPdVie() <= 0) {
            return true;
        }
        return false;
    }

    public void APDV() {
        System.out.println(getPdVie());
    }

    public Combatant(String name, float pdVie, int pdMana, int armure, int resistanceMagique, float pdvMax) {
        this.name = name;
        this.PdVie = pdVie;
        this.PdMana = pdMana;
        this.armure = armure;
        this.resistanceMagic = resistanceMagic;
        this.pdvMax = pdvMax;
    }

    // Getter/Setter

    public String getName () {
        return name;
    }

    public float getPdVie() {
        return this.PdVie;
    }

    public int getPdMana() {
        return PdMana;
    }

    public int getArmure() {
        return armure;
    }

    public int getResistanceMagic() {
        return resistanceMagic;
    }

    public boolean isEstVivant() {
        return estVivant;
    }

    public float getPdvMax() {
        return pdvMax;
    }

    public void setPdVie(float pdVie) {
        this.PdVie = pdVie;
    }

    public void setPdMana(int pdMana) {
        PdMana = pdMana;
    }

    public void setArmure(int armure) {
        this.armure = armure;
    }

    public void setResistanceMagic(int resistanceMagic) {
        this.resistanceMagic = resistanceMagic;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEstVivant(boolean vie) {this.estVivant = vie; }

}