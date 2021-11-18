package entities;

import java.time.LocalDate;

public abstract class Personne {

    String nom;
    String prenom;
    String adresse_mail;
    String numero_telephone;


    public Personne(String nom, String prenom, String adresse_mail, String num_tel) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse_mail = adresse_mail;
        this.numero_telephone=num_tel;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getAdresse_mail() {
        return adresse_mail;
    }

    public String getNumero_telephone() {
        return numero_telephone;
    }
}
