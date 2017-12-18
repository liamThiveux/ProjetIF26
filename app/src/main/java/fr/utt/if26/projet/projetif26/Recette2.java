package fr.utt.if26.projet.projetif26;

/**
 * Created by liamo on 19/11/2017.
 */

public class Recette2 {
    public int id;
    public String titre;
    public String photo;
    public String etapes;

    public Recette2(int id, String titre,String photo, String etapes){

        this.id = id;
        this.titre = titre;
        this.photo = photo;
        this.etapes = etapes;
    }

    public Recette2(String titre,String photo, String etapes){
        this.titre = titre;
        this.photo = photo;
        this.etapes = etapes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getEtapes(){
        return etapes;
    }

    public void setEtapes(String etapes){
        this.etapes = etapes;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Recette{" +
                "titre='" + titre + '\'' +
                ", photo='" + photo + '\'' +
                ", etapes='" + etapes + '\'' +
                '}';
    }
}
