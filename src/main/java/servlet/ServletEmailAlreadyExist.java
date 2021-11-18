package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.UtilisateurDao;
import dao.impl.UtilisateurDaoImpl;
import exceptions.EmailAlreadyExistException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/creercompte/emailAlreadyExist")
public class ServletEmailAlreadyExist extends HttpServlet {

    UtilisateurDao utilisateurDao = new UtilisateurDaoImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        boolean emailExistant= false;
        try {
            emailExistant = utilisateurDao.EmailExist(email);
        } catch (EmailAlreadyExistException e) {
            e.printStackTrace();
        }
        if(emailExistant){
            ObjectMapper mapper = new ObjectMapper();
            String jsonEmail = mapper.writeValueAsString(false);
            resp.getWriter().print(jsonEmail);
        }else{
            ObjectMapper mapper = new ObjectMapper();
            String jsonEmail = mapper.writeValueAsString(true);
            resp.getWriter().print(jsonEmail);
        }
    }
}
