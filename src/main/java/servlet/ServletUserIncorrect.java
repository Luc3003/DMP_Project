package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.UtilisateurDao;
import dao.impl.DataSourceProvider;
import dao.impl.UtilisateurDaoImpl;
import exceptions.EmailFormatIncorrectException;
import exceptions.EmailNotFoundException;
import services.UtilisateurService;
import utils.MotDePasseUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/connexion/Incorrect")
public class ServletUserIncorrect extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user = req.getParameter("username");
        String pwd = req.getParameter("password");
        try {
            if(!connexionAcordee(user,pwd)){
                ObjectMapper mapper = new ObjectMapper();
                String jsonEmail = mapper.writeValueAsString(true);
                resp.getWriter().print(jsonEmail);
            }else{
                ObjectMapper mapper = new ObjectMapper();
                String jsonEmail = mapper.writeValueAsString(false);
                resp.getWriter().print(jsonEmail);
            }
        } catch (EmailNotFoundException | EmailFormatIncorrectException e) {
            e.printStackTrace();
        }
    }

    private boolean connexionAcordee(String id, String mdp) throws EmailNotFoundException, EmailFormatIncorrectException {
        boolean accord=false;
        //recup liste de tous les mdp id
        UtilisateurService utilisateurService = new UtilisateurService();
        String mdp_encode = utilisateurService.getPasswordByEmail(id);
        if(mdp!=null && MotDePasseUtils.validerMotDePasse(mdp,mdp_encode)){
            accord=true;
        }

        return accord;
    }
}
