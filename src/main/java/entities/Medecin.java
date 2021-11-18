package entities;

import java.time.LocalDate;

public class Medecin extends Personne{

    private String specialite;

    public Medecin(String nom, String prenom, String adresse_mail, String num_tel,String sepcialite) {
        super(nom, prenom, adresse_mail, num_tel);
        this.specialite=sepcialite;
    }


    public String getSpecialite() {
        return specialite;
    }
}
