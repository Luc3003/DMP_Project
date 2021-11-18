package dao.impl;

import dao.ConsultationDao;
import entities.Consultation;
import entities.Patient;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ConsultationDaoImpl implements ConsultationDao {
    @Override
    public void listConsultations(int id_patient) {
        List<Consultation> result = new ArrayList<>();

        try {
            DataSource dataSource = DataSourceProvider.getDataSource();
            try (Connection connection = dataSource.getConnection();
                 PreparedStatement statement = connection.prepareStatement("SELECT * FROM consultation WHERE consultation.id_patient = patient.id AND patient.id=? ORDER BY date DESC")) {
                //Requete qui demande toutes les consultations d'un patient ordonné du plus récent au plus ancien
                statement.setInt(1, id_patient);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        int id_medecin = resultSet.getInt("id_medecin");
                        String releaseDateAsString = resultSet.getString("date");
                        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                        LocalDate date = LocalDate.parse(releaseDateAsString, dateFormat);
                        String commentaire = resultSet.getString("commentaire");
                        Consultation consultation = new Consultation(date, id_medecin, id_patient, commentaire);
                        result.add(consultation);
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void creerConsultation(Consultation consultation) {
        ConsultationDao consultationDao = new ConsultationDaoImpl();

        try {
            DataSource dataSource = DataSourceProvider.getDataSource();
            try(Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement("INSERT INTO consultation (id_patient,id_medecin,date,commentaire)" + "VALUE (?,?,?,?);")) {

                statement.setInt(1, consultation.getPatient());
                statement.setInt(2, consultation.getMedecin());
                statement.setDate(3, Date.valueOf(consultation.getDate()));
                statement.setString(4, consultation.getCommentaire());

                try (ResultSet resultSet = statement.executeQuery()) {}
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
