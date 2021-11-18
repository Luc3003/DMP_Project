package services;

import dao.UtilisateurDao;
import dao.impl.UtilisateurDaoImpl;
import entities.Utilisateur;
import exceptions.EmailAlreadyExistException;
import exceptions.EmailFormatIncorrectException;
import exceptions.EmailNotFoundException;
import exceptions.UnexistedRoleException;

public class UtilisateurService {

    UtilisateurDao utilisateurDao = new UtilisateurDaoImpl();

    public void addUtilisateur(Utilisateur utilisateur) throws EmailAlreadyExistException, EmailFormatIncorrectException {
        if(utilisateurDao.emailIsCorrect(utilisateur.getId_mail())||!utilisateurDao.EmailExist(utilisateur.getId_mail())){
            utilisateurDao.creerUtilisateur(utilisateur);
        }
    }

    public String getRoleByEmail(String email) throws UnexistedRoleException, EmailFormatIncorrectException {
        return utilisateurDao.getRoleUser(email);
    }

    public String getPasswordByEmail(String email) throws EmailFormatIncorrectException, EmailNotFoundException {
        return utilisateurDao.getUserMdp(email);

    }

}
