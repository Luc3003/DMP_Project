package entities;

import java.time.LocalDate;

public class Patient extends Personne{

    LocalDate date_de_naissance;
    String adresse;
    String numero_secu;

    public Patient(String nom, String prenom, LocalDate date_de_naissance, String adresse, String adresse_mail, String num_tel, String num_secu) {
        super(nom, prenom, adresse_mail, num_tel);
        this.date_de_naissance=date_de_naissance;
        this.adresse=adresse;
        this.numero_secu=num_secu;
    }


    public String getNumero_secu() {
        return numero_secu;
    }
    public LocalDate getDate_de_naissance() {
        return date_de_naissance;
    }
    public String getAdresse() {
        return adresse;
    }
}
