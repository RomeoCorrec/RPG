package ElementsJeu;

public class Weapon extends Item{

    private String name;
    private int degats;
    private int degatsMagique;

    public int getDegats() {
        return degats;
    }

    public int getDegatsMagique() {
        return degatsMagique;
    }

    public Weapon(String name, int degats, int degatMagique) {
        super(name);
        this.degats = degats;
        this.degatsMagique = degatMagique;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDegats(int degats) {
        this.degats = degats;
    }

    public void setDegatsMagique(int degatsMagique) {
        this.degatsMagique = degatsMagique;
    }
}
