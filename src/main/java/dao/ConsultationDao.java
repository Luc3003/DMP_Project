package dao;

import entities.Consultation;

import java.util.Date;

public interface ConsultationDao {

    //Lister toutes les consultations d'un patient :
    public void listConsultations(int id_patient);

    //Créer une consultation :
    public void creerConsultation(Consultation consultation);
}
