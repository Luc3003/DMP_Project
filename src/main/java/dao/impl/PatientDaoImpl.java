package dao.impl;

import dao.PatientDao;
import entities.Patient;
import exceptions.EmailAlreadyExistException;
import exceptions.EmailFormatIncorrectException;
import exceptions.PatientNotExistException;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class PatientDaoImpl implements PatientDao {

    @Override
    public List<Patient> listPatient() {
        List<Patient> result = new ArrayList<>();

        try {
            DataSource dataSource = DataSourceProvider.getDataSource();
            try (Connection connection = dataSource.getConnection();
                 Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery("SELECT * FROM patient ORDER BY nom;")) {
                while (resultSet.next()) {
                    String nom = resultSet.getString("nom");
                    String prenom = resultSet.getString("prenom");
                    String releaseDateAsString = resultSet.getString("naissance");
                    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate dateborn = LocalDate.parse(releaseDateAsString, dateFormat);
                    String num_secu = resultSet.getString("num_secu");
                    String adresse = resultSet.getString("adresse");
                    String email = resultSet.getString("email");
                    String telephone = resultSet.getString("tel");
                    Patient patient = new Patient(nom,prenom,dateborn,adresse,email,telephone,num_secu);
                    result.add(patient);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public Patient getPatient(int id) throws PatientNotExistException {
        try {
            DataSource dataSource = DataSourceProvider.getDataSource();
            try (Connection connection = dataSource.getConnection();
                 PreparedStatement statement = connection.prepareStatement("SELECT * FROM patient WHERE id=?;")) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        String nom = resultSet.getString("nom");
                        String prenom = resultSet.getString("prenom");
                        String releaseDateAsString = resultSet.getString("naissance");
                        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        LocalDate dateborn = LocalDate.parse(releaseDateAsString, dateFormat);
                        String num = resultSet.getString("num_secu");
                        String email = resultSet.getString("email");
                        String adresse = resultSet.getString("adresse");
                        String telephone = resultSet.getString("tel");
                        return new Patient(nom,prenom,dateborn,adresse,email,telephone,num);

                    }else throw new PatientNotExistException();
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public void creerPatient(Patient patient) {
        PatientDao patientDao = new PatientDaoImpl();
        try {
            DataSource dataSource = DataSourceProvider.getDataSource();
            try (Connection connection = dataSource.getConnection();
                 PreparedStatement statement = connection.prepareStatement("INSERT INTO patient (nom,prenom,naissance,num_secu,adresse,email,tel)" +
                         "VALUE (?,?,?,?,?,?,?);")) {
                statement.setString(1, patient.getNom());
                statement.setString(2, patient.getPrenom());
                statement.setDate(3, Date.valueOf(patient.getDate_de_naissance()));
                statement.setString(4, patient.getNumero_secu());
                statement.setString(5, patient.getAdresse());
                statement.setString(6, patient.getAdresse_mail());
                statement.setString(7, patient.getNumero_telephone());

                try (ResultSet resultSet = statement.executeQuery()) {
                    }
                }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public int getId(String email) {
        int id=-1;
        try {
            DataSource dataSource = DataSourceProvider.getDataSource();
            try (Connection connection = dataSource.getConnection();
                 PreparedStatement statement = connection.prepareStatement("SELECT id FROM `patient` WHERE `email`=?;")) {
                statement.setString(1, email);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        id = resultSet.getInt("id");
                        return id;
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return id;
    }

    @Override
    public boolean EmailExist(String email){
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
        } catch (SQLException | EmailAlreadyExistException throwables) {
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
