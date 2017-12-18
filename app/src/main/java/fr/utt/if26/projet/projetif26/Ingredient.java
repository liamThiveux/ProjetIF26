package fr.utt.if26.projet.projetif26;

/**
 * Created by liamo on 21/11/2017.
 */

public class Ingredient {
    public String nomIngredient;
    public int quantiteG;
    public int quantiteNbr;

    public Ingredient(String nomIngredient, int quantiteG, int quantiteNbr) {
        this.nomIngredient = nomIngredient;
        this.quantiteG = quantiteG;
        this.quantiteNbr = quantiteNbr;
    }

    public String getNomIngredient() {
        return nomIngredient;
    }

    public void setNomIngredient(String nomIngredient) {
        this.nomIngredient = nomIngredient;
    }

    public int getQuantiteNbr() {
        return quantiteNbr;
    }

    public void setQuantiteNbr(int quantiteNbr) {
        this.quantiteNbr = quantiteNbr;
    }

    public int getQuantiteG() {
        return quantiteG;
    }

    public void setQuantiteG(int quantiteG) {
        this.quantiteG = quantiteG;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "nomIngredient='" + nomIngredient + '\'' +
                ", quantiteG=" + quantiteG +
                ", quantiteNbr=" + quantiteNbr +
                '}';
    }
}
