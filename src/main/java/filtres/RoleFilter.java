package filtres;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/user/*")
public class RoleFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        /* On va transformer l'objet request qui est du type ServletRequest en objet de type HttpServletRequest (Cast)*/
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        /* Récupération de l'attribut utilisateurConnecte dans la session */
        String user = (String) httpRequest.getSession().getAttribute("user");
        if (user == null || "".equals(user)) { /* Si pas d'utilisateur dans la session on refuse l'acces à la partie privée */
            System.out.println("Il faut être connecté pour accéder à cette page !");
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.sendRedirect("/connexion");
            return;
        }
        /* Sinon on laisse le traitement se poursuivre */
        System.out.println("Vous avez passez le filtre prive vous allez être rediriger sur votre");
        chain.doFilter(request, response);

    }

    @Override
    public void destroy() {

    }

}
