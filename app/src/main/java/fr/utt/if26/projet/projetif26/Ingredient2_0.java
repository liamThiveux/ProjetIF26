package fr.utt.if26.projet.projetif26;

/**
 * Created by liamo on 21/11/2017.
 */

public class Ingredient2_0 {
    public int id;
    public String nomIngredient;

    public Ingredient2_0(String nomIngredient) {
        this.nomIngredient = nomIngredient;
    }

    public Ingredient2_0(int id, String nomIngredient){
        this.id = id;
        this.nomIngredient = nomIngredient;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomIngredient() {
        return nomIngredient;
    }

    public void setNomIngredient(String nomIngredient) {
        this.nomIngredient = nomIngredient;
    }

    @Override
    public String toString() {
        return "Ingredient2_0{" +
                "id=" + id +
                ", nomIngredient='" + nomIngredient + '\'' +
                '}';
    }
}
