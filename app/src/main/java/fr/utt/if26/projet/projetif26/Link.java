package fr.utt.if26.projet.projetif26;

/**
 * Created by liamo on 06/12/2017.
 */

public class Link {
    public int idRecette;
    public int idIngredient;

    public Link(int idRecette, int idIngredient){
        this.idRecette = idRecette;
        this.idIngredient = idIngredient;
    }

    public int getIdRecette() {
        return idRecette;
    }

    public void setIdRecette(int idRecette) {
        this.idRecette = idRecette;
    }

    public int getIdIngredient() {
        return idIngredient;
    }

    public void setIdIngredient(int idIngredient) {
        this.idIngredient = idIngredient;
    }

    @Override
    public String toString() {
        return "Link{" +
                "idRecette=" + idRecette +
                ", idIngredient=" + idIngredient +
                '}';
    }
}
