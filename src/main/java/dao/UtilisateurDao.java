package dao;

import entities.Utilisateur;
import exceptions.EmailAlreadyExistException;
import exceptions.EmailFormatIncorrectException;
import exceptions.EmailNotFoundException;
import exceptions.UnexistedRoleException;

import java.util.List;

public interface UtilisateurDao {

    public void creerUtilisateur(Utilisateur utilisateur) throws EmailAlreadyExistException;

    public List<Utilisateur> getListUtilisateur();

    public String getUserMdp(String email) throws EmailNotFoundException, EmailFormatIncorrectException;

    public String getRoleUser(String email) throws UnexistedRoleException, EmailFormatIncorrectException;

    public List<String> getContenuDossier(int id);

    public boolean EmailExist(String email) throws EmailAlreadyExistException;

    public boolean emailIsCorrect(String email) throws EmailFormatIncorrectException;
}
