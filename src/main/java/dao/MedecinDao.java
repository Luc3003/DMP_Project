package dao;

import entities.Medecin;

import java.sql.SQLException;
import java.util.List;

public interface MedecinDao {
    public List<Medecin> listMedecin();

    public Medecin getMedecin(int id) throws SQLException;

    public void creerMedecin(Medecin medecin);

}
