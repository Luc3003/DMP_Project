package servlet;

import dao.UtilisateurDao;
import dao.impl.UtilisateurDaoImpl;
import exceptions.EmailFormatIncorrectException;
import exceptions.EmailNotFoundException;
import exceptions.UnexistedRoleException;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import services.UtilisateurService;
import utils.MotDePasseUtils;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/connexion")
public class ConnexionServlet extends GenericServlet{

    UtilisateurService utilisateurService = new UtilisateurService();

    String username;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());

        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        templateEngine.process("connexion", context, resp.getWriter());
        context.setVariable("user",username);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //recupe mdp et id formulaire
        username = req.getParameter("username");
        String password = req.getParameter("password");

        try {
            if(connexionAcordee(username,password)) {
                String role = utilisateurService.getRoleByEmail(username);
                req.getSession().setAttribute("role",role);
                req.getSession().setAttribute("user",username);
                System.out.println(role+username);
                switch (role) {
                    case "Medecin":
                        resp.sendRedirect(String.format("/user/role/medecin?user="+username));
                        break;
                    case "Patient":
                        resp.sendRedirect(String.format("/user/role/patient?user="+username));
                        break;
                }
            }
        } catch (EmailNotFoundException | EmailFormatIncorrectException | UnexistedRoleException e) {
            e.printStackTrace();
        }

    }

    private boolean connexionAcordee(String id, String mdp) throws EmailNotFoundException, EmailFormatIncorrectException {
        boolean accord=false;
        //recup liste de tous les mdp id
        String mdp_encode = utilisateurService.getPasswordByEmail(id);
        if(utilisateurService.getPasswordByEmail(id)!=null && MotDePasseUtils.validerMotDePasse(mdp,mdp_encode)){
            accord=true;
        }

        return accord;
    }
}
