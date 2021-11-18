package servlet;

import dao.PatientDao;
import dao.UtilisateurDao;
import dao.impl.PatientDaoImpl;
import dao.impl.UtilisateurDaoImpl;
import entities.Patient;
import entities.Personne;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/user/role/patientDossier")
public class ServletPatientDossier extends GenericServlet {

    UtilisateurDao utilisateurDao = new UtilisateurDaoImpl();
    PatientDao patientDao = new PatientDaoImpl();

    String role;
    String user;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("role",role);
        req.getSession().setAttribute("user",user);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());

        role = (String) req.getSession().getAttribute("role");
        user = (String) req.getSession().getAttribute("user");
        context.setVariable("dossiers",getDossiers(user));
        System.out.println(getDossiers(user));

        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        templateEngine.process("patientDossier", context, resp.getWriter());
    }

    private List<String> getDossiers(String email){
        return utilisateurDao.getContenuDossier(patientDao.getId(email));
    }
}
