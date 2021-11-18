package services;


import dao.impl.PatientDaoImpl;
import dao.PatientDao;
import entities.Patient;
import exceptions.EmailAlreadyExistException;
import exceptions.EmailFormatIncorrectException;
import exceptions.PatientNotExistException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class PatientServiceTestCase {

    @Mock
    private PatientDao patientDao = new PatientDaoImpl();

    @InjectMocks
    private PatientService patientService = new PatientService();


    @Test
    public void shouldCreatePatient() throws EmailAlreadyExistException, EmailFormatIncorrectException {
        //GIVEN
        String datestring = "1943-12-25";
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(datestring, dateFormat);
        Patient patient = new Patient("Dupont","George",date,"10 Rue blabla","lal@gmail.com","258469","10030");

        //WHEN
        patientService.addPatient(patient);

        //THEN
        Mockito.verify(patientDao,times(1)).creerPatient(patient);
    }

    @Test(expected = EmailAlreadyExistException.class)
    public void shouldReturnEmailAlreadyExistException() throws EmailFormatIncorrectException, EmailAlreadyExistException {
        //GIVEN
        String datestring = "1943-12-25";
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(datestring, dateFormat);
        Patient patient = new Patient("Dupont","George",date,"10 Rue blabla","laail.com","258469","10030");
        Mockito.when(patientDao.EmailExist(patient.getAdresse_mail())).thenThrow(EmailAlreadyExistException.class);


        //WHEN
        patientService.addPatient(patient);

        //THEN
        Assert.fail();
    }

    @Test(expected = EmailFormatIncorrectException.class)
    public void shouldReturnEmailFormatIncorrectEception() throws EmailFormatIncorrectException, EmailAlreadyExistException {
        //GIVEN
        String datestring = "1943-12-25";
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(datestring, dateFormat);
        Patient patient = new Patient("Dupont","George",date,"10 Rue blabla","lal@gmail.com","258469","10030");
        Mockito.when(patientDao.emailIsCorrect(patient.getAdresse_mail())).thenThrow(EmailFormatIncorrectException.class);

        //WHEN
        patientService.addPatient(patient);

        //THEN
        fail();
    }

    @Test
    public void shouldReturnPatientById() throws PatientNotExistException {
        //GIVEN
        int id=5;

        //WHEN
        patientService.getPatientById(id);

        //THEN
        Mockito.verify(patientDao,Mockito.times(1)).getPatient(id);
    }

    @Test(expected = PatientNotExistException.class)
    public void shouldReturnPatientNotExist() throws PatientNotExistException {
        //GIVEN
        int id=5;
        Mockito.when(patientDao.getPatient(id)).thenThrow(PatientNotExistException.class);

        //WHEN
        patientService.getPatientById(id);

        //THEN
        fail();
    }

}

