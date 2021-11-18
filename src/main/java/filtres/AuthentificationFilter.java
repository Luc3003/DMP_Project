package filtres;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/* Mapping pour le filtre*/
/* Chaque requete commençant pas /prive/ passera par ce filtre et le code présent dans doFilter sera executé */
@WebFilter("/role/*")
public class AuthentificationFilter implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    

  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    /* On va transformer l'objet request qui est du type ServletRequest en objet de type HttpServletRequest (Cast)*/
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    /* Récupération de l'attribut utilisateurConnecte dans la session */
    String role = (String) httpRequest.getSession().getAttribute("role");
    if (role == null || "".equals(role)) { /* Si pas d'utilisateur dans la session on refuse l'acces à la partie privée */
      System.out.println("Il faut un role pour être connecté pour accéder à cette page !");
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
