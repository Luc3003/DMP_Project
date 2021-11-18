package entities;

import java.time.LocalDate;

public class Consultation {
    private LocalDate date;
    private int medecin;
    private int patient;
    private String commentaire;

    public Consultation(LocalDate date, int medecin, int patient, String commentaire){
        this.date=date;
        this.medecin=medecin;
        this.patient=patient;
        this.commentaire=commentaire;
    }

    public LocalDate getDate(){return date;}

    public int getMedecin(){return medecin;}

    public String getCommentaire(){return commentaire;}

    public int getPatient(){return patient;}

}
