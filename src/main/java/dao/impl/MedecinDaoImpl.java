package dao.impl;

import dao.MedecinDao;
import entities.Medecin;
import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MedecinDaoImpl implements MedecinDao {

    @Override
    public List<Medecin> listMedecin() {
        List<Medecin> result = new ArrayList<>();

        try {
            DataSource dataSource = DataSourceProvider.getDataSource();
            try (Connection connection = dataSource.getConnection();
                 Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery("SELECT * FROM medecin ORDER BY nom;")) {
                while (resultSet.next()) {
                    String nom = resultSet.getString("nom");
                    String prenom = resultSet.getString("prenom");
                    String adresse_mail = resultSet.getString("email");
                    String telephone = resultSet.getString("tel");
                    String specialite = resultSet.getString("specialite");
                    Medecin servant = new Medecin(nom,prenom,adresse_mail,telephone,specialite);
                    result.add(servant);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public Medecin getMedecin(int id) throws SQLException {
        try {
            DataSource dataSource = DataSourceProvider.getDataSource();
            try (Connection connection = dataSource.getConnection();
                 PreparedStatement statement = connection.prepareStatement("SELECT * FROM medecin WHERE id=?;")) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        String nom = resultSet.getString("nom");
                        String prenom = resultSet.getString("prenom");
                        String adresse_mail = resultSet.getString("email");
                        String telephone = resultSet.getString("tel");
                        String specialite = resultSet.getString("specialite");
                        return new Medecin(nom,prenom,adresse_mail,telephone,specialite);

                    }

                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public void creerMedecin(Medecin medecin) {
        try {
            DataSource dataSource = DataSourceProvider.getDataSource();
            try (Connection connection = dataSource.getConnection();
                 PreparedStatement statement = connection.prepareStatement("INSERT INTO medecin (nom,prenom,email,tel,specialite)" +
                         "VALUE (?,?,?,?,?);",Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, medecin.getNom());
                statement.setString(2, medecin.getPrenom());
                statement.setString(3, medecin.getAdresse_mail());
                statement.setString(4, medecin.getNumero_telephone());
                statement.setString(5, medecin.getSpecialite());
                ResultSet ids = statement.getGeneratedKeys();

                try (ResultSet resultSet = statement.executeQuery()) {
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
