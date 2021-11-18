package services;

import dao.PatientDao;
import dao.impl.PatientDaoImpl;
import entities.Patient;
import exceptions.EmailAlreadyExistException;
import exceptions.EmailFormatIncorrectException;
import exceptions.PatientNotExistException;

public class PatientService {

    PatientDao patientDao = new PatientDaoImpl();

    public void addPatient(Patient patient) throws EmailAlreadyExistException, EmailFormatIncorrectException {
        if(patientDao.emailIsCorrect(patient.getAdresse_mail())||!patientDao.EmailExist(patient.getAdresse_mail())){
            patientDao.creerPatient(patient);
        }
    }

    public Patient getPatientById(int id) throws PatientNotExistException {
        return patientDao.getPatient(id);
    }

}
