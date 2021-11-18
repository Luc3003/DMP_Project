package servlet;


import entities.Patient;
import entities.Utilisateur;
import exceptions.EmailAlreadyExistException;
import exceptions.EmailFormatIncorrectException;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import services.PatientService;
import services.UtilisateurService;
import utils.MotDePasseUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet("/creercompte")
public class CreerCompteServlet extends GenericServlet {

    private PatientService patientService = new PatientService();
    private UtilisateurService utilisateurService = new UtilisateurService();

    WebContext context;
    TemplateEngine templateEngine;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        context = new WebContext(req, resp, req.getServletContext());
        templateEngine = createTemplateEngine(req.getServletContext());
        templateEngine.process("creercompte", context, resp.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nom = req.getParameter("nom");
        String prenom = req.getParameter("prenom");
        String email = req.getParameter("email");
        String num_tel = req.getParameter("telephone");
        String password = req.getParameter("password");
        String date = req.getParameter("dateborn");
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String num_secu = req.getParameter("num_secu");
        String adresse = req.getParameter("adresse");

        boolean hasNotEmptyField = isNotNull(nom) && isNotNull(prenom) && isNotNull(email) && isNotNull(num_tel) && isNotNull(password)
                && isNotNull(date) && isNotNull(adresse) && isNotNull(num_secu);
        if (hasNotEmptyField) {
            LocalDate dateborn = LocalDate.parse(date, dateFormat);
            if (dateborn.isBefore(LocalDate.now())) {
                String password_encode = MotDePasseUtils.genererMotDePasse(password);
                try {
                    patientService.addPatient(new Patient(nom, prenom, dateborn, adresse, email, num_tel, num_secu));
                    utilisateurService.addUtilisateur(new Utilisateur(email, password_encode, "Patient"));
                    resp.sendRedirect(String.format("connexion"));
                } catch (EmailAlreadyExistException |EmailFormatIncorrectException e) {}
            }
        }
    }

    private boolean isNotNull(String string) {
        return !string.equals("");
    }

}