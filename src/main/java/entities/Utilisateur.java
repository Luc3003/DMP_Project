package entities;

public class Utilisateur {
    String id_mail;
    String mot_de_passe;
    String role;

    public String getId_mail() {
        return id_mail;
    }

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public String getRole() {
        return role;
    }

    public Utilisateur(String id_mail, String mot_de_passe, String role) {
        this.id_mail = id_mail;
        this.mot_de_passe = mot_de_passe;
        this.role=role;
    }
}
