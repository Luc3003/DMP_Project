package dao.impl;

import dao.UtilisateurDao;
import entities.Utilisateur;
import exceptions.EmailAlreadyExistException;
import exceptions.EmailFormatIncorrectException;
import exceptions.EmailNotFoundException;
import exceptions.UnexistedRoleException;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class UtilisateurDaoImpl implements UtilisateurDao {

    @Override
    public void creerUtilisateur(Utilisateur utilisateur) throws EmailAlreadyExistException {
        UtilisateurDao utilisateurDao = new UtilisateurDaoImpl();
        try {
            DataSource dataSource = DataSourceProvider.getDataSource();
            try (Connection connection = dataSource.getConnection();
                 PreparedStatement statement = connection.prepareStatement("INSERT INTO utilisateur (id_mail,mdp,role)" +
                         "VALUE (?,?,?);")) {
                statement.setString(1, utilisateur.getId_mail());
                statement.setString(2, utilisateur.getMot_de_passe());
                statement.setString(3, utilisateur.getRole());
                try (ResultSet resultSet = statement.executeQuery()) {
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<Utilisateur> getListUtilisateur() {
        List<Utilisateur> result = new ArrayList<>();

        try {
            DataSource dataSource = DataSourceProvider.getDataSource();
            try (Connection connection = dataSource.getConnection();
                 Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery("SELECT * FROM utilisateur ORDER BY id;")) {
                while (resultSet.next()) {
                    String id = resultSet.getString("id_mail");
                    String mdp_encode = resultSet.getString("mdp");
                    String role = resultSet.getString("role");
                    Utilisateur utilisateur = new Utilisateur(id, mdp_encode, role);
                    result.add(utilisateur);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public String getUserMdp(String email) throws EmailNotFoundException, EmailFormatIncorrectException {
        if(!emailIsCorrect(email)) throw new EmailFormatIncorrectException();
        String mdp_encode="";
        try {
            DataSource dataSource = DataSourceProvider.getDataSource();
            try (Connection connection = dataSource.getConnection();
                 PreparedStatement statement = connection.prepareStatement("SELECT mdp FROM utilisateur WHERE id_mail=? ORDER BY id;")) {
                statement.setString(1, email);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        mdp_encode = resultSet.getString("mdp");
                    }else throw new EmailNotFoundException();

                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return mdp_encode;
    }

    @Override
    public String getRoleUser(String email) throws UnexistedRoleException, EmailFormatIncorrectException {
        boolean existingRole;
        if(!emailIsCorrect(email)) throw new EmailFormatIncorrectException();
        String role="";
        try {
            DataSource dataSource = DataSourceProvider.getDataSource();
            try (Connection connection = dataSource.getConnection();
                 PreparedStatement statement = connection.prepareStatement("SELECT role FROM utilisateur WHERE id_mail=? ORDER BY id;")) {
                statement.setString(1, email);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        role = resultSet.getString("role");
                    }

                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        existingRole=role.equals("Patient")||role.equals("Medecin")||role.equals("Admin");
        if(!existingRole) throw new UnexistedRoleException();
        return role;
    }

    @Override
    public List<String> getContenuDossier(int id) {
        List<String> dossiers = new ArrayList<>();
        try {
            DataSource dataSource = DataSourceProvider.getDataSource();
            try (Connection connection = dataSource.getConnection();
                 PreparedStatement statement = connection.prepareStatement("SELECT `contenu` FROM `dossiers` WHERE `id_patient`=?;")) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        dossiers.add(resultSet.getString("contenu"));
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return dossiers;
    }
    @Override
    public boolean EmailExist(String email) throws EmailAlreadyExistException {
        boolean AlreadyExist = false;
        try {
            DataSource dataSource = DataSourceProvider.getDataSource();
            try (Connection connection = dataSource.getConnection();
                 PreparedStatement statement = connection.prepareStatement("SELECT id_mail FROM utilisateur WHERE id_mail=? ORDER BY id;")) {
                statement.setString(1, email);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {AlreadyExist=true;}else throw new EmailAlreadyExistException();
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return AlreadyExist;
    }

    @Override
    public boolean emailIsCorrect(String email) throws EmailFormatIncorrectException {
        boolean correct = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE).matcher(email).find();
        if(!correct) throw new EmailFormatIncorrectException();
        return correct;
    }

}
