package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/role/deconnexion")
public class DeconnexionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /* On supprime l'utilisateur connecte de la session */
        req.getSession().removeAttribute("user");
        /* On redirige vers la page de connexion */
        resp.sendRedirect("connexion");
    }
}