package dao;

import entities.Patient;
import exceptions.EmailAlreadyExistException;
import exceptions.EmailFormatIncorrectException;
import exceptions.PatientNotExistException;

import java.util.List;

public interface PatientDao {

    public List<Patient> listPatient();

    public Patient getPatient(int id) throws PatientNotExistException;

    public void creerPatient(Patient patient);

    public int getId(String email);

    public boolean EmailExist(String email) throws EmailAlreadyExistException;

    public boolean emailIsCorrect(String email) throws EmailFormatIncorrectException;
}
