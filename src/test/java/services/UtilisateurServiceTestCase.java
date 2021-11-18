package services;

import dao.UtilisateurDao;
import dao.impl.UtilisateurDaoImpl;
import entities.Utilisateur;
import exceptions.EmailAlreadyExistException;
import exceptions.EmailFormatIncorrectException;
import exceptions.EmailNotFoundException;
import exceptions.UnexistedRoleException;
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
public class UtilisateurServiceTestCase {

    @Mock
    private UtilisateurDao utilisateurDao = new UtilisateurDaoImpl();

    @InjectMocks
    private UtilisateurService utilisateurService = new UtilisateurService();


    @Test
    public void shouldCreateUtilisateur() throws EmailAlreadyExistException, EmailFormatIncorrectException {
        //GIVEN
        String datestring = "1943-12-25";
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(datestring, dateFormat);
        Utilisateur utilisateur = new Utilisateur("lala@gmail.com","123456789","Medecin");

        //WHEN
        utilisateurService.addUtilisateur(utilisateur);

        //THEN
        Mockito.verify(utilisateurDao,times(1)).creerUtilisateur(utilisateur);
    }


    @Test(expected = EmailAlreadyExistException.class)
    public void shouldReturnEmailAlreadyExistException() throws EmailFormatIncorrectException, EmailAlreadyExistException {
        //GIVEN
        String datestring = "1943-12-25";
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(datestring, dateFormat);
        Utilisateur utilisateur = new Utilisateur("lala@gmail.com","123456789","Medecin");

        Mockito.when(utilisateurDao.EmailExist(utilisateur.getId_mail())).thenThrow(EmailAlreadyExistException.class);


        //WHEN
        utilisateurService.addUtilisateur(utilisateur);

        //THEN
        Assert.fail();
    }

    @Test(expected = EmailFormatIncorrectException.class)
    public void shouldReturnEmailFormatIncorrectEception() throws EmailFormatIncorrectException, EmailAlreadyExistException {
        //GIVEN
        String datestring = "1943-12-25";
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(datestring, dateFormat);
        Utilisateur utilisateur = new Utilisateur("lala@gmail.com","123456789","Medecin");
        Mockito.when(utilisateurDao.emailIsCorrect(utilisateur.getId_mail())).thenThrow(EmailFormatIncorrectException.class);

        //WHEN
        utilisateurService.addUtilisateur(utilisateur);

        //THEN
        fail();
    }

    @Test
    public void shouldReturnRoleByEmail() throws UnexistedRoleException, EmailFormatIncorrectException {
        //GIVEN
        String email="test@gmail.com";

        //WHEN
        utilisateurService.getRoleByEmail(email);

        //THEN
        Mockito.verify(utilisateurDao,Mockito.times(1)).getRoleUser(email);
    }
    @Test(expected = UnexistedRoleException.class)
    public void shouldReturnEmailFormatIncorrectException() throws UnexistedRoleException, EmailFormatIncorrectException {
        //GIVEN
        String email="test@gmail.com";
        Mockito.when(utilisateurDao.getRoleUser(email)).thenThrow(UnexistedRoleException.class);

        //WHEN
        utilisateurService.getRoleByEmail(email);

        //THEN
        fail();
    }
    @Test(expected = EmailFormatIncorrectException.class)
    public void shouldReturnUnexistedRoleException() throws UnexistedRoleException, EmailFormatIncorrectException {
        //GIVEN
        String email="test@gmail.com";
        Mockito.when(utilisateurDao.getRoleUser(email)).thenThrow(EmailFormatIncorrectException.class);
        //WHEN
        utilisateurService.getRoleByEmail(email);

        //THEN
        fail();
    }

    @Test
    public void shouldReturnMdpByEmail() throws EmailFormatIncorrectException, EmailNotFoundException {
        //GIVEN
        String email="test@gmail.com";

        //WHEN
        utilisateurService.getPasswordByEmail(email);

        //THEN
        Mockito.verify(utilisateurDao,Mockito.times(1)).getUserMdp(email);
    }
    @Test(expected = EmailNotFoundException.class)
    public void shouldReturnEmailNotFoundException() throws EmailNotFoundException, EmailFormatIncorrectException {
        //GIVEN
        String email="test@gmail.com";
        Mockito.when(utilisateurDao.getUserMdp(email)).thenThrow(EmailNotFoundException.class);

        //WHEN
        utilisateurService.getPasswordByEmail(email);

        //THEN
        fail();
    }
    @Test(expected = EmailFormatIncorrectException.class)
    public void shouldReturnMdpEmailFormatIncorrectException() throws EmailFormatIncorrectException, EmailNotFoundException {
        //GIVEN
        String email="test@gmail.com";
        Mockito.when(utilisateurDao.getUserMdp(email)).thenThrow(EmailFormatIncorrectException.class);
        //WHEN
        utilisateurService.getPasswordByEmail(email);

        //THEN
        fail();
    }


}
